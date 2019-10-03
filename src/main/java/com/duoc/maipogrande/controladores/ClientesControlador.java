package com.duoc.maipogrande.controladores;

import com.duoc.maipogrande.modelos.*;
import com.duoc.maipogrande.servicios.ClienteServicio;
import com.duoc.maipogrande.servicios.ProductorServicio;
import com.duoc.maipogrande.servicios.TransportistaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Controller
public class ClientesControlador {

    @Autowired
    ClienteServicio clienteServicio;
    @Autowired
    ProductorServicio productorServicio;
    @Autowired
    TransportistaServicio transportistaServicio;

    /**
     *
     * @param model
     * @param principal
     * @param session
     * @param logout
     * @param error
     * @param attributes
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model,
                        Principal principal,
                        HttpSession session,
                        @RequestParam(value = "logout", required = false) String logout,
                        @RequestParam(value = "error", required = false) String error,
                        RedirectAttributes attributes) {
        String mensaje;
        if (principal != null) {
            String rol;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            rol = authentication.getAuthorities().stream().map(o -> ((GrantedAuthority) o).getAuthority()).collect(Collectors.joining());

            switch (rol) {
                case "ROLE_CLIENTE_EXTERNO":
                    Cliente clienteExterno = clienteServicio.buscarClientePorId(Long.parseLong(principal.getName()));
                    session.setAttribute("clienteExterno", clienteExterno);
                    session.setAttribute("nombre",clienteExterno.getNombreCli());
                    return "redirect:clienteExterno";
                case "ROLE_CLIENTE_INTERNO":
                    Cliente clienteInterno = clienteServicio.buscarClientePorId(Long.parseLong(principal.getName()));
                    session.setAttribute("clienteInterno", clienteInterno);
                    return "redirect:clienteInterno";
                case "ROLE_PRODUCTOR":
                    Productor productor = productorServicio.buscarProdPorId(Long.parseLong(principal.getName()));
                    session.setAttribute("productor", productor);
                    session.setAttribute("nombre",productor.getNombreProd());
                    return "redirect:productor";
                case "ROLE_TRANSPORTISTA":
                    Transportista transportista = transportistaServicio.buscarTranPorId(Long.parseLong(principal.getName()));
                    session.setAttribute("transportista", transportista);
                    session.setAttribute("nombre",transportista.getNombreTran());
                    return "redirect:transportista";
            }
        }
        if (logout != null){
            mensaje = "Sesion cerrada correctamente";
            model.addAttribute("logout", mensaje);
        }
        if(error !=null)
        {
            mensaje = "Error en las credenciales";
            model.addAttribute("error", mensaje);
        }
        return "index";
    }

    @Secured("ROLE_CLIENTE_INTERNO")
    @RequestMapping(value = "/clienteInterno", method = RequestMethod.GET)
    public String paginaPrincipalClienteInterno() {
        return "clienteInterno";
    }
    @Secured("ROLE_CLIENTE_INTERNO")
    @GetMapping(value = "/clienteInterno/crearSolicitud")
    public String paginaAñadirSolcitudInterno(Model model)
    {
        Solicitud solicitud = new Solicitud();
        Map<String,String> tipoUnidadMasa = new HashMap<String,String>(){{
            put("KG", "Kilogramos");
            put("T", "Toneladas");
        }};
        model.addAttribute("tipoUnidadMasa",tipoUnidadMasa);
        model.addAttribute("solicitud",solicitud);
        return "añadirSolicitudClienteInterno";
    }
    @Secured("ROLE_CLIENTE_INTERNO")
    @PostMapping(value = "/clienteInterno/crearSolicitud")
    public String peticionPostAñadirSolicitudInterno(@Valid @ModelAttribute("solicitud") Solicitud solicitud,
                                              BindingResult bindingResult,
                                              HttpSession session,
                                              @RequestParam(name = "nombreproducto[]",required = false)String[] nombresProductos,
                                              @RequestParam(name = "cantidadproducto[]",required = false)String[] cantidadProductosString,
                                              @RequestParam(name = "unidadMasa[]", required = false)String[] unidadMasas)
    {
        if(bindingResult.hasErrors())
        {
            return "redirect:/clienteInterno/crearSolicitud";
        }
        Integer[] cantidadProductos;
        try {
            cantidadProductos = Stream.of(cantidadProductosString)
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            for (int i = 0; i < unidadMasas.length ; i++) {
                if(unidadMasas[i].trim().isEmpty() || nombresProductos[i].trim().isEmpty())
                {
                    return "redirect:/clienteInterno/crearSolicitud";
                }
            }
        }
        catch (NumberFormatException e)
        {
            return "redirect:/clienteInterno/crearSolicitud";
        }
        List<ProductoSolicitado> productoSolicitados = new ArrayList<>();
        IntStream.range(0,nombresProductos.length)
                .forEach(i -> {
                    productoSolicitados.add(new ProductoSolicitado(nombresProductos[i],unidadMasas[i], cantidadProductos[i]));
                });
        solicitud.setEstadoSol('E');
        solicitud.setPaisDestinoSol("CL");
        solicitud.setCliente(new Cliente());
        solicitud.getCliente().setIdCli(((Cliente)session.getAttribute("clienteInterno")).getIdCli());
        clienteServicio.crearSolicitud(solicitud);
        clienteServicio.crearProductosSolicitados(productoSolicitados);
        return "redirect:/clienteInterno";
    }
    @Secured("ROLE_CLIENTE_EXTERNO")
    @RequestMapping(value = "/clienteExterno", method = RequestMethod.GET)
    public String paginaPrincipalClienteExterno() {
        return "clienteExterno";
    }

    @Secured("ROLE_CLIENTE_EXTERNO")
    @GetMapping(value = "/clienteExterno/crearSolicitud")
    public String paginaAñadirSolcitud(Model model)
    {
        Solicitud solicitud = new Solicitud();
        Map<String,String> paises = solicitud.obtenerPaises();
        Map<String,String> tipoUnidadMasa = new HashMap<String,String>(){{
                put("KG", "Kilogramos");
                put("T", "Toneladas");
            }};
        model.addAttribute("paises",paises);
        model.addAttribute("tipoUnidadMasa",tipoUnidadMasa);
        model.addAttribute("solicitud",solicitud);
        return "añadirSolicitudClienteExterno";
    }
    @Secured("ROLE_CLIENTE_EXTERNO")
    @PostMapping(value = "/clienteExterno/crearSolicitud")
    public String peticionPostAñadirSolicitud(@Valid @ModelAttribute("solicitud") Solicitud solicitud,
                                              BindingResult bindingResult,
                                              HttpSession session,
                                              @RequestParam(name = "nombreproducto[]",required = false)String[] nombresProductos,
                                              @RequestParam(name = "cantidadproducto[]",required = false)String[] cantidadProductosString,
                                              @RequestParam(name = "unidadMasa[]", required = false)String[] unidadMasas)
    {
        if(bindingResult.hasErrors())
        {
            return "redirect:/clienteExterno/crearSolicitud";
        }
        Integer[] cantidadProductos;
        try {
            cantidadProductos = Stream.of(cantidadProductosString)
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            for (int i = 0; i < unidadMasas.length ; i++) {
                if(unidadMasas[i].trim().isEmpty() || nombresProductos[i].trim().isEmpty())
                {
                    return "redirect:/clienteExterno/crearSolicitud";
                }
            }
        }
        catch (NumberFormatException e)
        {
            return "redirect:/clienteExterno/crearSolicitud";
        }
        List<ProductoSolicitado> productoSolicitados = new ArrayList<>();
        IntStream.range(0,nombresProductos.length)
                .forEach(i -> {
                    productoSolicitados.add(new ProductoSolicitado(nombresProductos[i],unidadMasas[i], cantidadProductos[i]));
                });
                solicitud.setEstadoSol('E');
                solicitud.setCliente(new Cliente());
                solicitud.getCliente().setIdCli(((Cliente)session.getAttribute("clienteExterno")).getIdCli());
                clienteServicio.crearSolicitud(solicitud);
                clienteServicio.crearProductosSolicitados(productoSolicitados);
        return "redirect:/clienteExterno";
    }


    @Secured("ROLE_TRANSPORTISTA")
    @RequestMapping(value = "/transportista", method = RequestMethod.GET)
    public String paginaPrincipalTransportista() {
        return "transportista";
    }
}
