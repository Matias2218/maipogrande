package com.duoc.maipogrande.controladores;
import com.duoc.maipogrande.modelos.Producto;
import com.duoc.maipogrande.modelos.Productor;
import com.duoc.maipogrande.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
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
    @GetMapping(value = "/editarProducto")
    public String paginaEditarProducto(){
        return "editarProducto";
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
    @PostMapping(value = "/productos")
    public String añadirProducto(@RequestParam(name = "txtNombre") String nombre,
                                 @RequestParam(name = "txtPrecio") Integer precio,
                                 @RequestParam(name = "txtStock") Integer stock,
                                 @RequestParam(name = "txtCalidad") Integer calidad,
                                 @RequestParam(name = "fileImagen") MultipartFile imagen,
                                 @RequestParam(name = "tipo") Character tipo)
    {
        return "redirect:productos";
    }
}
