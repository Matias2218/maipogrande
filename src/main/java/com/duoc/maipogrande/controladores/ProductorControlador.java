package com.duoc.maipogrande.controladores;

import com.duoc.maipogrande.modelos.Producto;
import com.duoc.maipogrande.modelos.Productor;
import com.duoc.maipogrande.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class ProductorControlador {

    @Autowired
    ProductoServicio productoServicio;

    private static final List<String> EXTENSIONES = Arrays.asList("image/png", "image/jpeg", "image/jpg");
    private static final Long MAXIMO_PESO_IMAGEN = 83886080L;

    @Secured("ROLE_PRODUCTOR")
    @GetMapping(value = "/productor")
    public String paginaPrincipalProductor() {
        return "productor";
    }

    //Añadir Producto GET
    @Secured("ROLE_PRODUCTOR")
    @GetMapping(value = "/añadirProducto")
    public String paginaAñadirProducto(Model model) {
        Producto producto = new Producto();
        model.addAttribute("producto", producto);
        return "añadirProducto";
    }

    //Productos GET
    @Secured("ROLE_PRODUCTOR")
    @GetMapping(value = "/productos")
    public String paginaDeProductos(Model model,
                                    HttpSession session,
                                    @RequestParam(name = "pagina", required = false) String i,
                                    @RequestParam(name = "txtBuscar", required = false) String txtBuscar) {
        short pagina = 0;
        if (i != null) {
            try {
                pagina = Short.parseShort(i);
                pagina--;
                pagina = (short) (pagina * 4);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        ArrayList<String> imagenes = new ArrayList<>();
        List<LocalDate> fechas = new ArrayList<>();
        if (session.getAttribute("productor") == null) {
            return "redirect:/";
        }
        Long id = ((Productor) session.getAttribute("productor")).getIdProd();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Producto> productos = (txtBuscar == null)?productoServicio.buscarProductosPorId(id,pagina) : productoServicio.buscarProductosPorNombre(txtBuscar.toLowerCase(), id, pagina);

        productos.stream().forEach(produ -> {
            imagenes.add(Producto.convertirImagen(produ.getImagenProdu()));
            fechas.add(produ.getFechaIngresoProdu().toLocalDate());
        });
        model.addAttribute("productos", productos);
        model.addAttribute("fechas", fechas);
        model.addAttribute("imagenes", imagenes);

        return"productos";
}

    //Editar Producto GET
    @Secured("ROLE_PRODUCTOR")
    @GetMapping(value = "/productos/{id}")
    public String paginaEditarProducto(@PathVariable(value = "id") Long id,
                                       Model model,
                                       HttpSession session) {
        Producto producto = productoServicio.buscarProductosPorIdProducto(id);
        String imagen = Producto.convertirImagen(producto.getImagenProdu());
        model.addAttribute("imagen", imagen);
        model.addAttribute("producto", producto);
        session.setAttribute("idProducto", producto.getIdProdu());
        session.setAttribute("imagenAnterior", producto.getImagenProdu());
        return "editarProducto";
    }

    //Eliminar Producto POST
    @Secured("ROLE_PRODUCTOR")
    @PostMapping(value = "/eliminarProducto")
    public String eliminarProducto(@RequestParam(name = "idProdu") Long id,
                                   RedirectAttributes attributes) {
        productoServicio.eliminarProducto(id);
        attributes.addFlashAttribute("alerta", "Producto eliminado correctamente");
        return "redirect:productos";
    }

    //Crear Producto POST
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

    //Editar Producto POST
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
}