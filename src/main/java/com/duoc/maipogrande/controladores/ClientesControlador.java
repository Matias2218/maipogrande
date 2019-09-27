package com.duoc.maipogrande.controladores;

import com.duoc.maipogrande.modelos.Cliente;
import com.duoc.maipogrande.modelos.Productor;
import com.duoc.maipogrande.modelos.Transportista;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
public class ClientesControlador {

    @Autowired
    ClienteServicio clienteServicio;
    @Autowired
    ProductorServicio productorServicio;
    @Autowired
    TransportistaServicio transportistaServicio;

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

    @Secured("ROLE_CLIENTE_EXTERNO")
    @RequestMapping(value = "/clienteExterno", method = RequestMethod.GET)
    public String paginaPrincipalClienteExterno() {
        return "clienteExterno";
    }

    @Secured("ROLE_CLIENTE_EXTERNO")
    @GetMapping(value = "/clienteExterno/crearSolicitud")
    public String paginaAñadirSolcitud()
    {
        return "añadirSolicitudClienteExterno";
    }

    @Secured("ROLE_TRANSPORTISTA")
    @RequestMapping(value = "/transportista", method = RequestMethod.GET)
    public String paginaPrincipalTransportista() {
        return "transportista";
    }
}
