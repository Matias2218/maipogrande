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
     * Metodo que controla la redireccion del inicio de sesion, utiliza protocolo HTTP GET
     * @param model interface que permite enviar elementos a la vista en tipo {@code java.util.Map}.
     * @param principal Contexto de la sesion que contiene al usuario y al rol
     * @param session Proporciona una manera de identificar un usuario o solicitud y alamacena la informacion
     * @param logout Anotacion que indica que parametro se solicito, en este caso, permite determinar si el usuario cerro sesion
     * @param error Anotacion que indica que parametro se solicitoo, en este caso, permite determinar si las credenciales del usuario son correctas
     * @return Permite la redirecion correspondiente a la vista, segun el flujo del algoritmo
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model,
                        Principal principal,
                        HttpSession session,
                        @RequestParam(value = "logout", required = false) String logout,
                        @RequestParam(value = "error", required = false) String error) {
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

    /**
     * Metodo que permite el redireccionamiento de pagina y ademas carga el tipo de unidad
     * @param model interface que permite enviar elementos a la vista en tipo {@code java.util.Map}.
     * @return Permite la redirecion correspondiente a la vista, segun el flujo del algoritmo
     */
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

    /**
     * Metodo que permite la creacion de solicitudes para el rol de Cliente Interno
     * @param solicitud Clase {@code com.duoc.maipogrande.modelos.Solicitud} proveniente de la vista CrearSolicitud
     * @param bindingResult Valida si existen errores en la clase {@code com.duoc.maipogrande.modelos.Solicitud}
     * @param session Proporciona una manera de identificar un usuario o solicitud y alamacena la informacion
     * @param nombresProductos Anotacion que indica que parametro se solicito, en este caso, nos permite almacenar el nombre de los productos
     * @param cantidadProductosString Anotacion que indica que parametro se solicito, en este caso, nos permite almacenar la cantidad de productos
     * @param unidadMasas Anotacion que indica que parametro se solicito, en este caso, nos permite almacenar el tipo de unidad de medida
     * @return  Permite la redirecion correspondiente a la ruta la cual contiene el metodo
     */
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

    /**
     * Metodo que permite la redireccion de pagina y ademas carga valores solicitados por el formulario (Paises y tipo de unidad)
     * @param model interface que permite enviar elementos a la vista en tipo {@code java.util.Map}.
     * @return Permite la redirecion correspondiente a la vista, segun el flujo del algoritmo
     */
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

    /**
     * Metodo que permite la creacion de solicitudes para el rol de Cliente Externo
     * @param solicitud Clase {@code com.duoc.maipogrande.modelos.Solicitud} proveniente de la vista CrearSolicitud
     * @param bindingResult Valida si existen errores en la clase {@code com.duoc.maipogrande.modelos.Solicitud}
     * @param session Proporciona una manera de identificar un usuario o solicitud y alamacena la informacion
     * @param nombresProductos Anotacion que indica que parametro se solicito, en este caso, nos permite almacenar el nombre de los productos
     * @param cantidadProductosString Anotacion que indica que parametro se solicito, en este caso, nos permite almacenar la cantidad de productos
     * @param unidadMasas Anotacion que indica que parametro se solicito, en este caso, nos permite almacenar el tipo de unidad de medida
     * @return  Permite la redirecion correspondiente a la ruta la cual contiene el metodo
     */
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
