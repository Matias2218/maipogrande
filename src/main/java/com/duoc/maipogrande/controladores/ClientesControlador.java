package com.duoc.maipogrande.controladores;

import com.duoc.maipogrande.modelos.Cliente;
import com.duoc.maipogrande.modelos.Productor;
import com.duoc.maipogrande.modelos.Transportista;
import com.duoc.maipogrande.servicios.ClienteServicio;
import com.duoc.maipogrande.servicios.ProductorServicio;
import com.duoc.maipogrande.servicios.TransportistaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClientesControlador {

    @Autowired
    ClienteServicio clienteServicio;
    @Autowired
    ProductorServicio productorServicio;
    @Autowired
    TransportistaServicio transportistaServicio;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        Cliente cliente = clienteServicio.buscarClientePorEmail("maldo1514@gmail.co");
        Productor productor = productorServicio.buscarProductorPorEmail("panchito@gmail.com");
        Transportista transportista = transportistaServicio.buscarTransportistaPorEmail("reload_13@live.cl");
        model.addAttribute("cliente", cliente);
        model.addAttribute("productor", productor);
        model.addAttribute("transportista", transportista);
        return "index";
    }
}
