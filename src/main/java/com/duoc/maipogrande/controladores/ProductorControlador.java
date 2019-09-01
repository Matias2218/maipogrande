package com.duoc.maipogrande.controladores;
import com.duoc.maipogrande.modelos.Producto;
import com.duoc.maipogrande.modelos.Productor;
import com.duoc.maipogrande.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ProductorControlador {

    @Autowired
    ProductoServicio productoServicio;

    @Secured("ROLE_PRODUCTOR")
    @GetMapping(value = "/productor")
    public String paginaPrincipalProductor() {
        return "productor";
    }
    @Secured("ROLE_PRODUCTOR")
    @GetMapping(value = "/añadirProducto")
    public String paginaAñadirProducto(){
        return "añadirProducto";
    }

    @Secured("ROLE_PRODUCTOR")
    @GetMapping(value = "/productos")
    public String paginaDeProductos(Model model, HttpSession session) {
        Long id = ((Productor)session.getAttribute("productor")).getIdProd();
        ArrayList<String> imagenes= new ArrayList<>();
        List<Producto> productos = productoServicio.buscarProductosPorId(id);
        productos.stream().forEach(produ -> imagenes.add(Producto.convertirImagen(produ.getImagenProdu())));
        model.addAttribute("productos" , productos);
        model.addAttribute("imagenes",imagenes);
        return "productos";
    }
    @Secured("ROLE_PRODUCTOR")
    @GetMapping(value = "/productos/{id}")
    public String paginaEditarProducto(@PathVariable(value = "id")Long id,
                                       Model model,
                                       HttpSession session)
    {
        Producto producto = productoServicio.buscarProductosPorIdProducto(id);
        String imagen = Producto.convertirImagen(producto.getImagenProdu());
        model.addAttribute("imagen",imagen);
        session.setAttribute("producto",producto);
        return "editarProducto";
    }
    @Secured("ROLE_PRODUCTOR")
    @PostMapping(value = "/eliminarProducto")
    public String eliminarProducto(@RequestParam(name = "idProdu") Long id){
        productoServicio.eliminarProducto(id);
        return "redirect:productos";
    }
    @Secured("ROLE_PRODUCTOR")
    @PostMapping(value = "/productos")
    public String añadirProducto(@RequestParam(name = "txtNombre",required = false) String nombre,
                                 @RequestParam(name = "txtPrecio",required = false) Integer precio,
                                 @RequestParam(name = "txtStock",required = false) Integer stock,
                                 @RequestParam(name = "txtCalidad",required = false) Integer calidad,
                                 @RequestParam(name = "fileImagen",required = false) MultipartFile imagen,
                                 @RequestParam(name = "tipo",required = false) Character tipo,
                                 HttpSession session) throws IOException, SQLException {

        byte[] bytes = imagen.getBytes();
        Blob blob = new SerialBlob(bytes);
        productoServicio.crearProducto(nombre,precio,blob,stock,tipo,calidad.byteValue(), LocalDateTime.now(),((Productor)session.getAttribute("productor")).getIdProd());
        return "redirect:productos";
    }
    @Secured("ROLE_PRODUCTOR")
    @PostMapping(value = "/editarProducto")
    public String editarProducto(@RequestParam(name = "txtNombre",required = false) String nombre,
                                 @RequestParam(name = "txtPrecio",required = false) Integer precio,
                                 @RequestParam(name = "txtStock",required = false) Integer stock,
                                 @RequestParam(name = "txtCalidad",required = false) Integer calidad,
                                 @RequestParam(name = "fileImagen",required = false) MultipartFile imagen,
                                 @RequestParam(name = "tipo",required = false) Character tipo,
                                 HttpSession session) throws IOException, SQLException {
            byte[] bytes = (!imagen.isEmpty())?imagen.getBytes():((Producto)session.getAttribute("producto")).getImagenProdu();
            Blob blob = new SerialBlob(bytes);
            productoServicio.actualizarProducto(((Producto)session.getAttribute("producto")).getIdProdu(),nombre,precio,blob,stock,tipo,calidad.byteValue());
        return "redirect:productos";
    }
}