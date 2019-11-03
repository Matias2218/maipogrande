package com.duoc.maipogrande.controladores;

import com.duoc.maipogrande.modelos.OfertaTransportista;
import com.duoc.maipogrande.modelos.Transportista;
import com.duoc.maipogrande.modelos.Venta;
import com.duoc.maipogrande.paginador.Pagina;
import com.duoc.maipogrande.servicios.TransportistaServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TransportistaControlador {
    @Autowired
    private TransportistaServicio transportistaServicio;

    private Logger logger = LoggerFactory.getLogger(TransportistaControlador.class);

    @Secured("ROLE_TRANSPORTISTA")
    @RequestMapping(value = "/transportista", method = RequestMethod.GET)
    public String paginaPrincipalTransportista(Model model,
                                               @RequestParam(name = "pagina", required = false, defaultValue = "0") String p,
                                               HttpSession session) {
        Integer pagina = 0;
        Integer paginaActual = 0;
        if (p != null) {
            try {
                pagina = Integer.parseInt(p);
                pagina--;
                if (pagina < 0) {
                    pagina = 0;
                }
                paginaActual = pagina;
                pagina = pagina * 4;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        List<Venta> ventas = transportistaServicio.ventasParaSubastaTrans(pagina.shortValue());
        int totalPaginas = transportistaServicio.contarVentasSubasta();
        Pagina paginador = new Pagina((short) totalPaginas, (short) (paginaActual + 1));
        model.addAttribute("ventas", ventas);
        model.addAttribute("paginador", paginador);
        return "transportista";
    }

    @Secured("ROLE_TRANSPORTISTA")
    @RequestMapping(value = "/subastaTransportista/{id}", method = RequestMethod.GET)
    public String paginaSubastaTransporista(@PathVariable(name = "id") String idString,
                                            Model model,
                                            HttpSession session) {
        Long id = null;
        boolean esAptoParaOfertar = false;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            return "redirect:/transportista";
        }
        Venta venta = transportistaServicio.buscarVentaPorId(id);
        if (!venta.getOfertaProductos().isEmpty()) {
            venta.ordernarTop3Transportistas();
        }
        Transportista transportista = (Transportista) session.getAttribute("transportista");
        esAptoParaOfertar = venta.transportistaAptoParaOfertar(venta, transportista);
        String pais = venta
                .getSolicitud()
                .obtenerPaises()
                .entrySet().stream()
                .filter(x -> venta.getSolicitud().getPaisDestinoSol().equals(x.getKey()))
                .map(x -> x.getValue())
                .collect(Collectors.joining());
        session.setAttribute("venta", venta);
        model.addAttribute("esAptoParaOfertar", esAptoParaOfertar);
        model.addAttribute("pais", pais);
        return "subastaTransportista";
    }

    @Secured("ROLE_TRANSPORTISTA")
    @RequestMapping(value = "/subastaTransportista", method = RequestMethod.POST)
    public String crearOfertaTransportista(@RequestParam(name = "txtPrecio", required = false) String precioString,
                                           HttpSession session,
                                           @RequestHeader(value = "referer", required = false) String referrer,
                                           RedirectAttributes redirectAttributes) {
        Integer precio = null;
        try {
            precio = Integer.parseInt(precioString);
        } catch (NumberFormatException e) {
            return String.format("redirect:%s", referrer);
        }
        OfertaTransportista ofertaTransportista = new OfertaTransportista();
        ofertaTransportista.setPrecioOfertaOfert(precio);
        ofertaTransportista.getVenta().setIdVenta(((Venta) session.getAttribute("venta")).getIdVenta());
        ofertaTransportista.getTransportista().setIdTran(((Transportista) session.getAttribute("transportista")).getIdTran());
        transportistaServicio.crearOfertaTransportista(ofertaTransportista);
        redirectAttributes.addFlashAttribute("alerta", "Oferta ingresada con exito");
        return "redirect:/transportista";
    }
    @Secured("ROLE_TRANSPORTISTA")
    @RequestMapping(value = "/detalleVentaTransportista/{id}", method = RequestMethod.GET)
    public String paginaVentaProductor(Model model,
                                       @PathVariable(value = "id",required = false) String idString,
                                       HttpSession session)
    {
        Long idVenta;
        Long idTran;
        try
        {
            idVenta = Long.parseLong(idString);
        }
        catch (NumberFormatException e)
        {
            return "redirect:/transportista";
        }
        idTran = ((Transportista)session.getAttribute("transportista")).getIdTran();
        Venta venta = transportistaServicio.buscarVentaDetalleTran(idVenta,idTran);
        model.addAttribute(venta);
        return "detalleVentaTransportista";
    }

}
