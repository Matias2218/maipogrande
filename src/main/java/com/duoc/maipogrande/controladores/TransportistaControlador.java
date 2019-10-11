package com.duoc.maipogrande.controladores;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TransportistaControlador {
	
	    @Secured("ROLE_TRANSPORTISTA")
	    @RequestMapping(value = "/transportista", method = RequestMethod.GET)
	    public String paginaPrincipalTransportista() {
	        return "transportista";
	    }
	    
	    @Secured("ROLE_TRANSPORTISTA")
	    @RequestMapping(value = "/subastaTransportista", method = RequestMethod.GET)
	    public String paginaSubastaTransporista() {
	        return "subastaTransportista";
	    }

}
