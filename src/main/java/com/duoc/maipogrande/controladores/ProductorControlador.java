package com.duoc.maipogrande.controladores;

import com.duoc.maipogrande.modelos.*;
import com.duoc.maipogrande.paginador.Pagina;
import com.duoc.maipogrande.servicios.ProductoServicio;
import com.duoc.maipogrande.servicios.ProductorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.reverseOrder;


@Controller
public class ProductorControlador {

    @Autowired
    ProductoServicio productoServicio;
    @Autowired
    ProductorServicio productorServicio;


    private static final List<String> EXTENSIONES = Arrays.asList("image/png", "image/jpeg", "image/jpg");
    private static final Long MAXIMO_PESO_IMAGEN = 83886080L;

    /**
     * Metodo que permite la redireccion a la pagina principal del productor
     * @param model interface que permite enviar elementos a la vista en tipo {@code java.util.Map}.
     * @param p Anotacion que indica que parametro se solicito, en este caso, el valor de pagina
     * @param session Proporciona una manera de identificar un usuario o solicitud y alamacena la informacion
     * @return Permite la redirecion correspondiente a la vista, segun el flujo del algoritmo
     */
    @Secured("ROLE_PRODUCTOR")
    @GetMapping(value = "/productor")
    public String paginaPrincipalProductor(Model model,
                                           @RequestParam(name = "pagina", required = false, defaultValue = "0")String p,
                                           HttpSession session) {
        Integer pagina = 0;
        Integer paginaActual = 0;
        if (p != null) {
            try {
                pagina = Integer.parseInt(p);
                pagina--;
                if(pagina < 0)
                {
                    pagina= 0;
                }
                paginaActual = pagina;
                pagina =  pagina * 4;
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        List<Venta> ventas = productorServicio.ventasParaSubasta(pagina);
        List<Venta> ventasActivas = productorServicio.ventasActivasProductor(((Productor)session.getAttribute("productor")).getIdProd());
        int totalPaginas = productorServicio.contarVentasSubasta();
        Pagina paginador = new Pagina((short) totalPaginas,(short)(paginaActual+1));
        model.addAttribute("ventas",ventas);
        session.setAttribute("ventasActivas",ventasActivas);
        model.addAttribute("paginador",paginador);
        model.addAttribute("paginaActual",(paginaActual==0)?1: paginaActual+1);
        return "productor";
    }

    /**
     * Metodo que permite la redireccion a la pagina editar del producto
     * @param idString Parametro vinculado a una plantilla URI, si es de tipo map, este trae todos sus elementos
     * @param model interface que permite enviar elementos a la vista en tipo {@code java.util.Map}.
     * @return Permite la redirecion correspondiente a la vista, segun el flujo del algoritmo
     */
    @Secured("ROLE_PRODUCTOR")
    @GetMapping(value = "/subasta/{id}")
    public String paginaEditarProducto(@PathVariable(name = "id") String idString,
                                       HttpSession session,
                                       Model model)
    {
        Integer id = null;
        try {
            id = Integer.parseInt(idString);
        }
        catch (NumberFormatException e)
        {
            return "redirect:/productor";
        }
        Venta venta = productorServicio.buscarVentaPorIdParaSubasta(id);
        String pais = venta
                .getSolicitud()
                .obtenerPaises()
                .entrySet().stream()
                .filter(x -> venta.getSolicitud().getPaisDestinoSol().equals(x.getKey()))
                .map(x -> x.getValue())
                .collect(Collectors.joining());

        List<OfertaProducto> ofertaProductos = venta.getOfertaProductos()
                .stream()
                .sorted(Comparator.comparing(OfertaProducto::getPrecioOferta)
                        .thenComparing(reverseOrder(Comparator.comparing
                                (ofertaProducto -> ofertaProducto.getProducto().getCalidadProdu()))))
                .collect(Collectors.toList());
        Map<Long,Long> idProdsSolicitados = ofertaProductos
                .stream()
                .distinct()
                .collect(Collectors.toMap(OfertaProducto::getIdOferp,ofertaProducto -> ofertaProducto.getProductoSolicitado().getIdProdS()));
                /*.map(ofertaProducto -> ofertaProducto.getProductoSolicitado().getIdProdS())
                .sorted(Long::compareTo)
                .distinct()
                .toArray(Long[]::new);*/
        if(venta == null)
        {
            return "redirect:/productor";
        }
        venta.setOfertaProductos(ofertaProductos);
        session.setAttribute("venta",venta);
        model.addAttribute("pais",pais);
        model.addAttribute("idProdsSolicitados",idProdsSolicitados);
        return "subastaProductor";
    }

    @Secured("ROLE_PRODUCTOR")
    @PostMapping(value = "/subasta")
    public String subastarProducto(@RequestParam(name = "cmbCancelar[]") String[] idProdsTexto,
                                   @RequestParam(name = "precioOfertar[]") String[] precioOfertarTexto,
                                   @RequestParam(name = "idProds[]") String[] idProductosSolictadosTexto,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes)
    {
        Long[] idProds;
        Long[] idProdSolicitados;
        Integer[] precioOferta;
        Venta venta;
        try {
             idProds = Stream.of(idProdsTexto)
                       .map(Long::parseLong)
                       .toArray(Long[]::new);
             precioOferta = Stream.of(precioOfertarTexto)
                     .map(Integer::parseInt)
                     .toArray(Integer[]::new);
            idProdSolicitados = Stream.of(idProductosSolictadosTexto)
                     .map(Long::parseLong)
                     .toArray(Long[]::new);
        }
        catch (Exception e)
        {
            return "redirect:/subasta/"+((Venta)session.getAttribute("venta")).getIdVenta();
        }
        venta = (Venta) session.getAttribute("venta");
        Long idProductor = ((Productor) session.getAttribute("productor")).getIdProd();
        List<OfertaProducto> ofertaProductos = new ArrayList<>();
        for (int i = 0; i < precioOferta.length ; i++) {
            OfertaProducto ofertaProducto = new OfertaProducto();
            ofertaProducto.setUnidadMasaOferta("KG");
            ofertaProducto.setPrecioOferta(precioOferta[i]);
            ofertaProducto.setPaisOrigen("Chile");
            ofertaProducto.setProducto(new Producto());
            ofertaProducto.getProducto().setIdProdu(idProds[i]);
            ofertaProducto.setVenta(new Venta());
            ofertaProducto.getVenta().setIdVenta(((Venta)session.getAttribute("venta")).getIdVenta());
            for (OfertaProducto producto:
                 venta.getOfertaProductos()) {
                if(producto.getProducto().getProductor().getIdProd().equals(idProductor)
                        && producto.getPrecioOferta() <= ofertaProducto.getPrecioOferta()
                        && producto.getProducto().getIdProdu().equals(ofertaProducto.getProducto().getIdProdu() )
                        && producto.getProductoSolicitado().getIdProdS().equals(idProdSolicitados[i])
                        && producto.getVenta().getIdVenta().equals(((Venta)session.getAttribute("venta")).getIdVenta()))
                {
                    return "redirect:/subasta/"+((Venta)session.getAttribute("venta")).getIdVenta();
                }
            }
            ofertaProductos.add(ofertaProducto);

        }
        for (int i = 0; i <ofertaProductos.size() ; i++) {
             productorServicio.crearOfertaProducto(ofertaProductos.get(i),idProdSolicitados[i]);
        }
        redirectAttributes.addFlashAttribute("alerta",(ofertaProductos.size()==1)?"Oferta ingresada correctamente a la venta":"Ofertas ingresadas correctamente a la venta");
        return "redirect:/productor";
    }




    /**
     * Metodo que permite la redireccion a la correspondiente pagina y ademas carga el tipo de unidad de medida
     * @param model interface que permite enviar elementos a la vista en tipo {@code java.util.Map}.
     * @return Permite la redirecion correspondiente a la vista, segun el flujo del algoritmo
     */
    @Secured("ROLE_PRODUCTOR")
    @GetMapping(value = "/añadirProducto")
    public String paginaAñadirProducto(Model model) {
        Producto producto = new Producto();
        Map<String,String> unidadesDeMasa = new HashMap<String, String>() {{
            put("KG", "Kilogramos");
            put("T", "Toneladas");
        }};
        model.addAttribute("unidadesDeMasa",unidadesDeMasa);
        model.addAttribute("producto", producto);
        return "añadirProducto";
    }

    /**
     * Metodo que permite la visualizacion de los productos y los pagina
     * @param model interface que permite enviar elementos a la vista en tipo {@code java.util.Map}.
     * @param session Proporciona una manera de identificar un usuario o solicitud y alamacena la informacion
     * @param i Anotacion que indica que parametro se solicito, en este caso, la creacion de una variable para contador
     * @param txtBuscar Anotacion que indica que parametro se solicito, en este caso, el valor de txtBuscar
     * @return Permite la redirecion correspondiente a la vista, segun el flujo del algoritmo
     */
    @Secured("ROLE_PRODUCTOR")
    @GetMapping(value = "/productos")
    public String paginaDeProductos(Model model,
                                    HttpSession session,
                                    @RequestParam(name = "pagina", required = false) String i,
                                    @RequestParam(name = "txtBuscar", required = false) String txtBuscar) {
        if (session.getAttribute("productor") == null) {
            return "redirect:/";
        }
        short pagina = 0;
        short paginaActual = 0;
        if (i != null) {
            try {
                pagina = Short.parseShort(i);
                pagina--;
                if(pagina < 0)
                {
                    pagina= 0;
                }
                paginaActual = pagina;
                pagina = (short) (pagina * 4);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        ArrayList<String> imagenes = new ArrayList<>();
        List<Producto> productos;
        List<LocalDate> fechas = new ArrayList<>();
        Long id = ((Productor) session.getAttribute("productor")).getIdProd();
        double totalPaginas = 0;
        if(txtBuscar == "" || txtBuscar == null)
        {
            productos = productoServicio.buscarProductosPorId(id,pagina);
            productos.forEach(producto -> producto.setUnidadMasaProdu(StringUtils.capitalize(producto.getUnidadMasaProdu().toLowerCase())));
            totalPaginas = Math.ceil((double)productoServicio.contarProductos(id)/ (double)4);
            totalPaginas = (totalPaginas == 0) ? 1 : totalPaginas;
        }
        else {
            productos =  productoServicio.buscarProductosPorNombre(txtBuscar.toLowerCase(), id, pagina);
            productos.forEach(producto -> producto.setUnidadMasaProdu(StringUtils.capitalize(producto.getUnidadMasaProdu().toLowerCase())));
            totalPaginas =  (short) (productoServicio.contarProductosConFiltro(id, txtBuscar, pagina)/4);
            totalPaginas = (totalPaginas == 0) ? 1 : totalPaginas;
        }
        Pagina paginador = new Pagina((short) totalPaginas,(short) (paginaActual+1));
        productos.forEach(produ -> {
            imagenes.add(Producto.convertirImagen(produ.getImagenProdu()));
            fechas.add(produ.getFechaIngresoProdu().toLocalDate());
        });
        model.addAttribute("paginaActual",(paginaActual==0)?1: paginaActual+1);
        model.addAttribute("paginador",paginador);
        model.addAttribute("productos", productos);
        model.addAttribute("fechas", fechas);
        model.addAttribute("imagenes", imagenes);
        return"productos";
}

    /**
     * Metodo que redirecciona a la pagina correspondiente y ademas almacena los datos para la edicion del producto mas adelante
     * @param id Parametro de metodo que debe estar vinculado a una plantilla URI, si esta es de tipo MAP se obtienen todos sus valores
     * @param model interface que permite enviar elementos a la vista en tipo {@code java.util.Map}.
     * @param session Proporciona una manera de identificar un usuario o solicitud y alamacena la informacion
     * @return Permite la redirecion correspondiente a la vista, segun el flujo del algoritmo
     */
    @Secured("ROLE_PRODUCTOR")
    @GetMapping(value = "/productos/{id}")
    public String paginaEditarProducto(@PathVariable(value = "id") Long id,
                                       Model model,
                                       HttpSession session) {
        Producto producto = productoServicio.buscarProductosPorIdProducto(id);
        String imagen = Producto.convertirImagen(producto.getImagenProdu());
        Map<String,String> unidadesDeMasa = new HashMap<String, String>() {{
            put("KG", "Kilogramos");
            put("T", "Toneladas");
        }};
        model.addAttribute("unidadesDeMasa",unidadesDeMasa);
        model.addAttribute("imagen", imagen);
        model.addAttribute("producto", producto);
        session.setAttribute("idProducto", producto.getIdProdu());
        session.setAttribute("imagenAnterior", producto.getImagenProdu());
        return "editarProducto";
    }

    /**
     * Metodo que permite la eliminacion de productos por parte del productor
     * @param id Anotacion que indica que parametro se solicito, en este caso, el valor de idProdu
     * @param attributes Manda informacion a la vista, cuando esta es redirigida mediante una ruta que va hacia un metodo
     * @return Permite la redirecion correspondiente a la vista, la cual ejecuta el metodo correspondiente
     */
    @Secured("ROLE_PRODUCTOR")
    @PostMapping(value = "/eliminarProducto")
    public String eliminarProducto(@RequestParam(name = "idProdu") Long id,
                                   RedirectAttributes attributes) {
        productoServicio.eliminarProducto(id);
        attributes.addFlashAttribute("alerta", "Producto eliminado correctamente");
        return "redirect:productos";
    }

    /**
     * Metodo que permite el agregar productos por parte del productor
     * @param producto Clase {@code com.duoc.maipogrande.modelos.Producto} proveniente de la vista añadirProducto
     * @param bindingResult Valida si existen errores en la clase {@code com.duoc.maipogrande.modelos.Producto}
     * @param imagen Anotacion que indica que parametro se solicito, en este caso, el valor de imagen
     * @param session Proporciona una manera de identificar un usuario o solicitud y alamacena la informacion
     * @param attributes Manda informacion a la vista, cuando esta es redirigida mediante una ruta que va hacia un metodo
     * @return Permite la redirecion correspondiente a la vista, segun el flujo del algoritmo
     * @throws IOException Control de excepciones
     * @throws SQLException Control de excepciones, servidor
     */
    @Secured("ROLE_PRODUCTOR")
    @PostMapping(value = "/productos")
    public String añadirProducto(@Valid @ModelAttribute("producto") Producto producto,
                                 BindingResult bindingResult,
                                 @RequestParam(name = "fileImagen", required = false) MultipartFile imagen,
                                 HttpSession session,
                                 RedirectAttributes attributes) throws IOException, SQLException {
        if (!EXTENSIONES.contains(imagen.getContentType()) || imagen.getSize() > MAXIMO_PESO_IMAGEN || bindingResult.hasErrors()) {
            String url = URLEncoder.encode("añadirProducto", "UTF-8");
            return "redirect:" + url;
        }
        byte[] bytes = imagen.getBytes();
        Blob blob = new SerialBlob(bytes);
        productoServicio.crearProducto(producto, blob, ((Productor) session.getAttribute("productor")).getIdProd());
        attributes.addFlashAttribute("alerta", "Producto creado correctamente");
        return "redirect:productos";
    }

    /**
     * Metodo que permite editar el producto por parte del productor
     * @param producto Clase {@code com.duoc.maipogrande.modelos.Producto} proveniente de la vista añadirProducto
     * @param bindingResult Valida si existen errores en la clase {@code com.duoc.maipogrande.modelos.Producto}
     * @param imagen Anotacion que indica que parametro se solicito, en este caso, el valor de imagen
     * @param session Proporciona una manera de identificar un usuario o solicitud y alamacena la informacion
     * @param attributes Manda informacion a la vista, cuando esta es redirigida mediante una ruta que va hacia un metodo
     * @return Permite la redirecion correspondiente a la vista, segun el flujo del algoritmo
     * @throws IOException Control de excepciones
     * @throws SQLException Control de excepciones, servidor
     */
    @Secured("ROLE_PRODUCTOR")
    @PostMapping(value = "/editarProducto")
    public String editarProducto(@Valid @ModelAttribute("producto") Producto producto,
                                 BindingResult bindingResult,
                                 @RequestParam(name = "fileImagen", required = false) MultipartFile imagen,
                                 HttpSession session,
                                 RedirectAttributes attributes) throws IOException, SQLException {

        if ((!EXTENSIONES.contains(imagen.getContentType()) && !imagen.isEmpty()) || imagen.getSize() > MAXIMO_PESO_IMAGEN || bindingResult.hasErrors()) {
            return "redirect:/productos/" + session.getAttribute("idProducto");
        }
        byte[] bytes = (!imagen.isEmpty()) ? imagen.getBytes() : (byte[]) session.getAttribute("imagenAnterior");
        Blob blob = new SerialBlob(bytes);
        producto.setIdProdu((Long) session.getAttribute("idProducto"));
        productoServicio.actualizarProducto(producto, blob);
        attributes.addFlashAttribute("alerta", "Producto editado correctamente");
        return "redirect:productos";
    }

    @Secured("ROLE_PRODUCTOR")
    @PostMapping(value = "/productosDisponibles", produces = "application/json")
    public ResponseEntity<?> productosDisponibles(@RequestBody HashMap<String,String> idProductoDisponibleMap,
                                                  HttpSession httpSession)
    {
        Map<String,Object> result = new HashMap<>();
        Long idProductoDisponible;
        Long idProductor;
        List<Producto> productos;
        try{
            idProductoDisponible = Long.parseLong(idProductoDisponibleMap.get("idProductoSolicitado"));
            idProductor = ((Productor)httpSession.getAttribute("productor")).getIdProd();
        }catch (NumberFormatException e)
        {
            result.put("fail","fail");
            return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
        }
        productos = productoServicio.productosDisponibles(idProductoDisponible,idProductor,((Venta)httpSession.getAttribute("venta")).getTipoVenta());
        result.put("productos",productos);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}