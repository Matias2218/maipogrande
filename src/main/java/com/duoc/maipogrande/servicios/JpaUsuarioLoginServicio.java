package com.duoc.maipogrande.servicios;

import com.duoc.maipogrande.modelos.Cliente;
import com.duoc.maipogrande.modelos.Producto;
import com.duoc.maipogrande.modelos.Productor;
import com.duoc.maipogrande.modelos.Transportista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JpaUsuarioLoginServicio implements UserDetailsService {

    @Autowired
    ClienteServicio clienteServicio;
    @Autowired
    ProductorServicio productorServicio;
    @Autowired
    TransportistaServicio transportistaServicio;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<GrantedAuthority> authorities = new ArrayList<>();
        Cliente cliente = clienteServicio.buscarClientePorEmail(s);
        Productor productor = productorServicio.buscarProductorPorEmail(s);
        Transportista transportista = transportistaServicio.buscarTransportistaPorEmail(s);
        if (cliente != null && productor == null && transportista == null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_CLIENTE"));
            return new User(cliente.getIdCli().toString(), cliente.getContraseñaCli(), true, true, true, true, authorities);
        } else if (cliente == null && productor != null && transportista == null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_PRODUCTOR"));
            return new User(productor.getIdProd().toString(), productor.getContraseñaProd(), true, true, true, true, authorities);
        } else {
            authorities.add(new SimpleGrantedAuthority("ROLE_TRANSPORTISTA"));
            return new User(transportista.getIdTran().toString(), transportista.getContrasñaTran(), true, true, true, true, authorities);
        }

    }
}
