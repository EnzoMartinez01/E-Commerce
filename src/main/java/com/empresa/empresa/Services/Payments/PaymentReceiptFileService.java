package com.empresa.empresa.Services.Payments;

import com.empresa.empresa.Dto.Payments.PaymentReceiptDto;
import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Models.Cart.CartItems;
import com.empresa.empresa.Models.Payments.Payment;
import com.empresa.empresa.Models.Products.Products;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentReceiptFileService {

    // Generate XML file for payment receipt
    public String generatePaymentReceiptXml(Payment payment) throws JAXBException {
        PaymentReceiptDto dto = new PaymentReceiptDto();
        dto.setName(payment.getCart().getUsers().getFullname());
        dto.setReference(payment.getReference());
        dto.setTotal(BigDecimal.valueOf(payment.getAmount()));
        dto.setPaymentDate(payment.getPaymentDate());
        dto.setStatus(payment.getStatus().name());
        dto.setPaymentMethod(payment.getPaymentMethod().getName());

        List<PaymentReceiptDto.ProductDto> productos = payment.getCart().getCartItems().stream().map(item -> {
            PaymentReceiptDto.ProductDto p = new PaymentReceiptDto.ProductDto();
            p.setNameProduct(item.getProduct().getProductName());
            p.setQuantity(item.getQuantity());
            return p;
        }).collect(Collectors.toList());

        dto.setProducts(productos);

        StringWriter sw = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(PaymentReceiptDto.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(dto, sw);

        return sw.toString();
    }

    // Generate PDF file for payment receipt
    public byte[] generatePdf(Payment payment) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);
        document.open();

        Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
        Font subFont = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font normalFont = new Font(Font.HELVETICA, 12);
        Font boldFont = new Font(Font.HELVETICA, 12, Font.BOLD);

        Paragraph title = new Paragraph("Comprobante de Pago", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20);
        document.add(title);

        Users user = payment.getCart().getUsers();

        Paragraph info = new Paragraph();
        info.add(new Chunk("Cliente: ", boldFont));
        info.add(new Chunk(user.getFullname() + "\n", normalFont));
        info.add(new Chunk("Email: ", boldFont));
        info.add(new Chunk(user.getEmail() + "\n", normalFont));
        info.add(new Chunk("Referencia: ", boldFont));
        info.add(new Chunk(payment.getReference() + "\n", normalFont));
        info.add(new Chunk("Fecha de Pago: ", boldFont));
        info.add(new Chunk(LocalDate.now().toString() + "\n", normalFont));
        info.setSpacingAfter(20);
        document.add(info);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        table.setWidths(new float[]{4, 2, 2, 2});

        addCellHeader(table, "Producto");
        addCellHeader(table, "Cantidad");
        addCellHeader(table, "Precio Unitario");
        addCellHeader(table, "Subtotal");

        double total = 0;
        for (CartItems item : payment.getCart().getCartItems()) {
            Products product = item.getProduct();
            int quantity = item.getQuantity();
            double price = product.getPrice();
            double subtotal = quantity * price;
            total += subtotal;

            table.addCell(new PdfPCell(new Phrase(product.getProductName(), normalFont)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(quantity), normalFont)));
            table.addCell(new PdfPCell(new Phrase("S/ " + String.format("%.2f", price), normalFont)));
            table.addCell(new PdfPCell(new Phrase("S/ " + String.format("%.2f", subtotal), normalFont)));
        }

        document.add(table);

        Paragraph totalParagraph = new Paragraph("Total pagado: S/ " + String.format("%.2f", total), subFont);
        totalParagraph.setAlignment(Element.ALIGN_RIGHT);
        totalParagraph.setSpacingBefore(10);
        document.add(totalParagraph);

        Paragraph footer = new Paragraph("Gracias por su compra", boldFont);
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setSpacingBefore(30);
        document.add(footer);

        document.close();
        return outputStream.toByteArray();
    }

    private void addCellHeader(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, new Font(Font.HELVETICA, 12, Font.BOLD)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        table.addCell(cell);
    }

}
