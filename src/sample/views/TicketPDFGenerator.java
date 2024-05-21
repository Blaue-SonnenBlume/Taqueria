/*package sample.views;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class TicketPDFGenerator {

    public void generarTicketPDF(String nombreCliente, String detalleOrden, double total) {
        Document documento = new Document();

        try {
            PdfWriter.getInstance(documento, new FileOutputStream("ticket.pdf"));

            documento.open();
            documento.add(new Paragraph("Ticket de compra"));
            documento.add(new Paragraph("Cliente: " + nombreCliente));
            documento.add(new Paragraph("Detalle de la orden:"));
            documento.add(new Paragraph(detalleOrden));
            documento.add(new Paragraph("Total a pagar: $" + total));

            documento.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}*/