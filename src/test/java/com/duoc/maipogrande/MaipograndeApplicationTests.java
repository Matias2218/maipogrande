package com.duoc.maipogrande;

import com.duoc.maipogrande.modelos.OfertaProducto;
import com.duoc.maipogrande.modelos.Venta;
import com.duoc.maipogrande.servicios.ClienteServicio;
import com.duoc.maipogrande.servicios.PdfServicio;
import com.duoc.maipogrande.servicios.ProductorServicio;
import com.duoc.maipogrande.servicios.TransportistaServicio;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    PdfPCell cell;
    PdfPTable table;
    Paragraph paragraph;
    Chunk chunk;
    Document document;
    Phrase phrase;
    @Test
    public void contextLoads() throws IOException, DocumentException {
        Venta venta = clienteServicio.traerVentaCliente(1L,1L);
        document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("nombrePdf.pdf"));
        document.open();
        Image image = Image.getInstance("src/main/resources/static/img/logo-maipo-bg.png");
        image.scaleAbsolute(120, 40);
        image.setAlignment(Element.ALIGN_RIGHT);
        BaseColor greyCell = new BaseColor(222,222,222);
        BaseFont bf = BaseFont.createFont(
                BaseFont.HELVETICA,
                BaseFont.CP1252,
                BaseFont.EMBEDDED);
        Font font = new Font(bf, 12);
        Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD);
        Font smallTitleFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.BOLD);
        chunk = new Chunk(String.format("VENTA N° %d ACEPTADA", venta.getIdVenta()), chapterFont);
        phrase = new Phrase();
        phrase.add(chunk);
        paragraph = new Paragraph();
        paragraph.add(phrase);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(20);
        document.add(image);
        document.add(paragraph);
        paragraph = new Paragraph();
        chunk = new Chunk("                Fecha de rechazo: ", smallTitleFont);
        phrase = new Phrase();
        phrase.add(chunk);
        paragraph.add(chunk);
        paragraph.add(new Phrase(String.valueOf(LocalDate.now()), font));
        document.add(paragraph);
        paragraph = new Paragraph();
        chunk = new Chunk("                Motivos del rechazo", smallTitleFont);
        phrase = new Phrase();
        phrase.add(chunk);
        paragraph.add(phrase);
        table = new PdfPTable(1);
        table.setSpacingBefore(4);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell = new PdfPCell(new Phrase("Muy mala las frutas y no llegaron", font));
        cell.setFixedHeight(35f);
        table.addCell(cell);
        document.add(paragraph);
        document.add(table);
        paragraph = new Paragraph();
        paragraph.add(new Phrase(Chunk.NEWLINE));
        paragraph.add(new Phrase(String.format("                N° de Venta: %d", venta.getIdVenta()), font));
        paragraph.add(new Phrase(Chunk.NEWLINE));
        paragraph.add(new Phrase(String.format("                Fecha de emisión: %s", venta.getSolicitud().getFechaEmisionSol().toString()), font));
        paragraph.add(new Phrase(Chunk.NEWLINE));
        paragraph.add(new Phrase(String.format("                Dirección de destino: %s", venta.getSolicitud().getDireccionDestinoSol()), font));
        paragraph.add(new Phrase(Chunk.NEWLINE));
        paragraph.add(new Phrase(String.format("                País de Origen: %s", venta.getSolicitud().getPaisDestinoSol()), font));
        paragraph.add(new Phrase(Chunk.NEWLINE));
        paragraph.add(new Phrase(String.format("                Descripción de la venta: %s", venta.getSolicitud().getDescripcionSol()), font));
        document.add(paragraph);
        paragraph = new Paragraph();
        paragraph.setSpacingBefore(4);
        chunk = new Chunk("                CLIENTE: ", smallTitleFont);
        phrase = new Phrase();
        phrase.add(chunk);
        paragraph.add(chunk);
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(new Phrase(String.format("                Rut: %s", venta.getSolicitud().getCliente().getRutCli()), font));
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(new Phrase(String.format("                Nombre: %s %s",  venta.getSolicitud().getCliente().getNombreCli(), venta.getSolicitud().getCliente().getApellidosCli()), font));
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(new Phrase(String.format("                Email: %s", venta.getSolicitud().getCliente().getEmailCli()), font));
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(new Phrase(String.format("                Teléfono: %s", venta.getSolicitud().getCliente().getTelefonoCli()), font));
        document.add(paragraph);
        table = new PdfPTable(2);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.setSpacingBefore(10);
        cell = new PdfPCell(new Phrase("Productos Solicitados",font));
        cell.setColspan(2);
        cell.setBackgroundColor(greyCell);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Nombre",font));
        cell.setBackgroundColor(greyCell);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Cantidad",font));
        cell.setBackgroundColor(greyCell);
        table.addCell(cell);
        venta.getSolicitud().getProductoSolicitados().forEach(productoSolicitado -> {
            cell = new PdfPCell(new Phrase(productoSolicitado.getNombreProdS(),font));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(String.format("%d %s",productoSolicitado.getCantidadProdS(),productoSolicitado.getUnidadProdS()),font));
            table.addCell(cell);
        });
        document.add(table);
        //Añadir parrafo proovedores, chequear si es el unico o hay mas que le corresopnda algun producto
        Long[] idsProductores = venta.getOfertaProductos().stream().map(ofertaProducto -> ofertaProducto.getProducto().getProductor().getIdProd())
                .distinct()
                .toArray(Long[]::new);
        Arrays.stream(idsProductores).forEach(l -> {
            OfertaProducto ofertaProductoFiltrada = venta.getOfertaProductos().stream()
                    .filter(ofertaProducto -> ofertaProducto.getProducto().getProductor().getIdProd().equals(l))
                    .findFirst().orElse(null);
            paragraph = new Paragraph();
            paragraph.setSpacingBefore(4);
            chunk = new Chunk("                PROVEEDORES: ", smallTitleFont);
            phrase = new Phrase();
            phrase.add(chunk);
            paragraph.add(chunk);
            paragraph.add(Chunk.NEWLINE);
            paragraph.add(new Phrase(String.format("                Rut: %s", ofertaProductoFiltrada.getProducto().getProductor().getRutProd()), font));
            paragraph.add(Chunk.NEWLINE);
            paragraph.add(new Phrase(String.format("                Nombre: %s %s", ofertaProductoFiltrada.getProducto().getProductor().getNombreProd(), ofertaProductoFiltrada.getProducto().getProductor().getApellidoProd()), font));
            paragraph.add(Chunk.NEWLINE);
            paragraph.add(new Phrase(String.format("                Email: %s", ofertaProductoFiltrada.getProducto().getProductor().getEmailProd()), font));
            paragraph.add(Chunk.NEWLINE);
            paragraph.add(new Phrase(String.format("                Teléfono: %s",ofertaProductoFiltrada.getProducto().getProductor().getTelefonoProd()), font));
            try {
                document.add(paragraph);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            List<OfertaProducto> ofertaProductoList = venta.getOfertaProductos()
                    .stream()
                    .filter(ofertaProducto -> ofertaProducto.getProducto().getProductor().getIdProd().equals(l))
                    .collect(Collectors.toList());
            table = new PdfPTable(4);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.setSpacingBefore(10);
            cell = new PdfPCell(new Phrase("Productos Ofrecidos",font));
            cell.setColspan(4);
            cell.setBackgroundColor(greyCell);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Nombre",font));
            cell.setBackgroundColor(greyCell);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Cantidad",font));
            cell.setBackgroundColor(greyCell);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Precio x KG",font));
            cell.setBackgroundColor(greyCell);
            table.addCell(cell);
            cell = new PdfPCell(new Phrase("Precio total",font));
            cell.setBackgroundColor(greyCell);
            table.addCell(cell);
           ofertaProductoList.forEach(ofertaProducto -> {
               cell = new PdfPCell(new Phrase(ofertaProducto.getProducto().getNombreProdu(),font));
               table.addCell(cell);
               cell = new PdfPCell(new Phrase(String.format("%d %s",ofertaProducto.getProductoSolicitado().getCantidadProdS(),ofertaProducto.getProductoSolicitado().getUnidadProdS()),font));
               table.addCell(cell);
               cell = new PdfPCell(new Phrase(String.format("%s %d","$",ofertaProducto.getStockOferta()),font));
               table.addCell(cell);
               Integer precioTotal = (ofertaProducto.getProductoSolicitado().equals("T")) ? (ofertaProducto.getProductoSolicitado().getCantidadProdS() * 1000) * ofertaProducto.getPrecioOferta() : ofertaProducto.getProductoSolicitado().getCantidadProdS() * ofertaProducto.getPrecioOferta();
               cell = new PdfPCell(new Phrase(String.format("%s %d","$",precioTotal),font));
               table.addCell(cell);
               try {
                   document.add(table);
               } catch (DocumentException e) {
                   e.printStackTrace();
               }
           });
        });


        //Transportistas
        paragraph = new Paragraph();
        paragraph.setSpacingBefore(4);
        chunk = new Chunk("                TRANSPORTISTA: ", smallTitleFont);
        phrase = new Phrase();
        phrase.add(chunk);
        paragraph.add(chunk);
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(new Phrase(String.format("                Rut: %s", "2121"), font));
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(new Phrase(String.format("                Nombre: %s", "Matias Maldonado"), font));
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(new Phrase(String.format("                Email: %s", "asfjka@gmail.com"), font));
        paragraph.add(Chunk.NEWLINE);
        paragraph.add(new Phrase(String.format("                Teléfono: %s", "2121"), font));
        document.add(paragraph);
        table = new PdfPTable(3);
        table.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.setSpacingBefore(10);
        cell = new PdfPCell(new Phrase("Transporte",font));
        cell.setColspan(3);
        cell.setBackgroundColor(greyCell);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("País de origen",font));
        cell.setBackgroundColor(greyCell);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Destino",font));
        cell.setBackgroundColor(greyCell);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Precio",font));
        cell.setBackgroundColor(greyCell);
        table.addCell(cell);
        document.add(table);
        document.close();
        writer.close();
    }


}
