package com.duoc.maipogrande.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClientesControlador {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("saludo", "Hola mundo Maldo Weco te");
        return "index";
    }
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index2(Model model){
        model.addAttribute("saludo", "Hola mundo 2");
        return "index";
    }
}
