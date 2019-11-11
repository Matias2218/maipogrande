package com.duoc.maipogrande.servicios;

import com.duoc.maipogrande.modelos.Venta;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class PdfServicio {
    private Logger logger = LoggerFactory.getLogger(PdfServicio.class);
    @Autowired
    AmazonS3Service amazonS3Service;
    @Async
    public String generarPdfRechazo(Venta venta, String motivo) {
        try {
            String nombrePdf = String.format("pdfRechazo%d%s.pdf",venta.getIdVenta(), UUID.randomUUID().toString());
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(nombrePdf));
            document.open();
            Image image = Image.getInstance("src/main/resources/static/img/logo-maipo-bg.png");
            image.scaleAbsolute(180, 60);
            image.setAlignment(Element.ALIGN_RIGHT);
            BaseFont bf = BaseFont.createFont(
                    BaseFont.HELVETICA,
                    BaseFont.CP1252,
                    BaseFont.EMBEDDED);
            Font font = new Font(bf, 12);
            Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD);
            Chunk chunk = new Chunk("Venta Rechazada", chapterFont);
            Phrase phrase = new Phrase();
            phrase.add(chunk);
            Paragraph paragraph = new Paragraph();
            paragraph.add(phrase);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setSpacingAfter(20);
            Paragraph paragraph2 = new Paragraph();
            paragraph2.add(new Phrase(Chunk.NEWLINE));
            paragraph2.add(new Phrase(String.format("                N° de Venta: %d",/*venta.getIdVenta()*/1),font));
            paragraph2.add(new Phrase(Chunk.NEWLINE));
            paragraph2.add(new Phrase(Chunk.NEWLINE));
            paragraph2.add(new Phrase(String.format("                Fecha de rechazo: %s", LocalDate.now()),font));
            paragraph2.add(new Phrase(Chunk.NEWLINE));
            paragraph2.add(new Phrase(Chunk.NEWLINE));
            paragraph2.add(new Phrase(String.format("                Motivo de rechazo: %s",motivo)));
            document.add(image);
            document.add(paragraph);
            document.add(paragraph2);
            document.close();
            writer.close();
            if(amazonS3Service.subirArchivoS3Bucket(nombrePdf))
            {
                File file = new File(nombrePdf);
                file.delete();
            }
            else
            {
                File file = new File(nombrePdf);
                if(file.canRead() && file.canExecute())
                {
                    file.delete();
                }
            }
            return nombrePdf;
        } catch (
                DocumentException | IOException e) {
            logger.error(e.getMessage());
            return null;
        }
    }


}
