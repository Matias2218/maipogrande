package com.duoc.maipogrande.controladores;
import com.duoc.maipogrande.modelos.Productor;
import com.duoc.maipogrande.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


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
    @GetMapping(value = "/productos")
    public String paginaDeProductos(Model model, HttpSession session) {
        Long id = ((Productor)session.getAttribute("productor")).getIdProd();
        model.addAttribute("productos",productoServicio.buscarProductosPorId(id));
        return "productos";
    }
}
