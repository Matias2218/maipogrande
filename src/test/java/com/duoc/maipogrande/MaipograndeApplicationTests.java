package com.duoc.maipogrande;

import com.duoc.maipogrande.servicios.ClienteServicio;
import com.duoc.maipogrande.servicios.PdfServicio;
import com.duoc.maipogrande.servicios.ProductorServicio;
import com.duoc.maipogrande.servicios.TransportistaServicio;
import com.itextpdf.text.DocumentException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MaipograndeApplicationTests {

    @Autowired
    ClienteServicio clienteServicio;
    @Autowired
    ProductorServicio productorServicio;
    @Autowired
    TransportistaServicio transportistaServicio;
    @Autowired
    PdfServicio pdfServicio;

    @Test
    public void contextLoads() throws IOException, DocumentException {
    }

}
