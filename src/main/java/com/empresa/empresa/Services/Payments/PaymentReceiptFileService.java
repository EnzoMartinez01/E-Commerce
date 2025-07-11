package com.empresa.empresa.Services.Payments;

import com.empresa.empresa.Dto.Payments.PaymentReceiptDto;
import com.empresa.empresa.Models.Authentication.Users;
import com.empresa.empresa.Models.Cart.CartItems;
import com.empresa.empresa.Models.Payments.Payment;
import com.empresa.empresa.Models.Products.Products;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
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

        // Configuración del documento
        Document document = new Document(PageSize.A4, 50, 50, 50, 50); // Márgenes personalizados
        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Fuentes personalizadas
        Font titleFont = new Font(Font.HELVETICA, 22, Font.BOLD, new Color(0, 51, 102));
        Font headerFont = new Font(Font.HELVETICA, 14, Font.BOLD, new Color(0, 51, 102));
        Font subFont = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font normalFont = new Font(Font.HELVETICA, 12);
        Font boldFont = new Font(Font.HELVETICA, 12, Font.BOLD);
        Font footerFont = new Font(Font.HELVETICA, 10, Font.ITALIC, Color.GRAY);

        // Logo o encabezado (opcional)
        Paragraph header = new Paragraph("MI EMPRESA", headerFont);
        header.setAlignment(Element.ALIGN_CENTER);
        header.setSpacingAfter(5);
        document.add(header);

        Paragraph subHeader = new Paragraph("Sistema de Pagos", new Font(Font.HELVETICA, 10, Font.NORMAL, Color.GRAY));
        subHeader.setAlignment(Element.ALIGN_CENTER);
        subHeader.setSpacingAfter(20);
        document.add(subHeader);

        // Título del comprobante
        Paragraph title = new Paragraph("COMPROBANTE DE PAGO", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(30);
        document.add(title);

        // Línea decorativa
        Paragraph line = new Paragraph();
        line.add(new Chunk(new LineSeparator(1, 100, Color.LIGHT_GRAY, Element.ALIGN_CENTER, -1)));
        line.setSpacingAfter(20);
        document.add(line);

        Users user = payment.getCart().getUsers();

        // Información del pago
        PdfPTable infoTable = new PdfPTable(2);
        infoTable.setWidthPercentage(100);
        infoTable.setSpacingBefore(10f);
        infoTable.setSpacingAfter(20f);
        infoTable.setWidths(new float[]{1, 3});

        addInfoCell(infoTable, "N° de Comprobante:", payment.getReference(), boldFont, normalFont);
        addInfoCell(infoTable, "Fecha de Pago:", LocalDate.now().toString(), boldFont, normalFont);
        addInfoCell(infoTable, "Cliente:", user.getFullname(), boldFont, normalFont);
        addInfoCell(infoTable, "Email:", user.getEmail(), boldFont, normalFont);
        addInfoCell(infoTable, "Método de Pago:", "Tarjeta de Crédito", boldFont, normalFont); // Ajustar según tu sistema

        document.add(infoTable);

        // Tabla de productos
        PdfPTable table = new PdfPTable(6); // Cambiado a 5 columnas
        table.setWidthPercentage(100);
        table.setSpacingBefore(20f);
        table.setSpacingAfter(20f);
        table.setWidths(new float[]{4, 2, 2, 2, 2, 2});

        addCellHeader(table, "Producto");
        addCellHeader(table, "Cantidad");
        addCellHeader(table, "Precio Unitario");
        addCellHeader(table, "Descuento");
        addCellHeader(table, "Precio Descuento");
        addCellHeader(table, "Subtotal");

        double subtotal = 0;
        for (CartItems item : payment.getCart().getCartItems()) {
            Products product = item.getProduct();
            int quantity = item.getQuantity();
            double price = product.getPrice();
            int discount = 0;
            double finalPrice = price;

            if (product.getIsOffer() != null) {
                discount = product.getOfferDescount();
                finalPrice = product.getPriceOffer();
            } else {
                finalPrice = price;
            }

            double itemSubtotal = quantity * finalPrice;
            subtotal += itemSubtotal;

            PdfPCell cell;

            cell = new PdfPCell(new Phrase(product.getProductName(), normalFont));
            cell.setPadding(5);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(String.valueOf(quantity), normalFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(5);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("S/ " + String.format("%.2f", price), normalFont));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPadding(5);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(discount > 0 ? discount + "%" : "-", normalFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(5);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("S/ " + String.format("%.2f", finalPrice), normalFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setPadding(5);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(
                    finalPrice > 0 ? "S/ " + String.format("%.2f", finalPrice) : "-",
                    normalFont
            ));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setPadding(5);
            table.addCell(cell);
        }

        document.add(table);

        // Cálculo de impuestos
        double igv = subtotal * 0.18;
        double total = subtotal + igv;

        // Tabla de totales
        PdfPTable totalTable = new PdfPTable(2);
        totalTable.setWidthPercentage(50);
        totalTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalTable.setSpacingBefore(10f);
        totalTable.setWidths(new float[]{2, 2});

        // Subtotal
        addTotalRow(totalTable, "Subtotal:", "S/ " + String.format("%.2f", subtotal), normalFont);

        // IGV
        addTotalRow(totalTable, "IGV (18%):", "S/ " + String.format("%.2f", igv), normalFont);

        // Total
        addTotalRow(totalTable, "TOTAL:", "S/ " + String.format("%.2f", total), new Font(Font.HELVETICA, 14, Font.BOLD));

        document.add(totalTable);

        // Mensaje de agradecimiento
        Paragraph thanks = new Paragraph("¡Gracias por su compra!", new Font(Font.HELVETICA, 14, Font.BOLD, new Color(0, 51, 102)));
        thanks.setAlignment(Element.ALIGN_CENTER);
        thanks.setSpacingBefore(30);
        document.add(thanks);

        // Pie de página
        Paragraph footer = new Paragraph();
        footer.add(new Chunk(new LineSeparator(0.5f, 100, Color.LIGHT_GRAY, Element.ALIGN_CENTER, -1)));
        footer.add(new Phrase("\nEste comprobante es válido como documento de pago\n", footerFont));
        footer.add(new Phrase("Mi Empresa S.A.C. - RUC: 12345678901\n", footerFont));
        footer.add(new Phrase("Av. Principal 123 - Lima, Perú | Teléfono: (01) 123-4567", footerFont));
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setSpacingBefore(20);
        document.add(footer);

        document.close();
        return outputStream.toByteArray();
    }

    private void addCellHeader(PdfPTable table, String text) {
        PdfPCell cell = new PdfPCell(new Phrase(text, new Font(Font.HELVETICA, 12, Font.BOLD, Color.WHITE)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(new Color(0, 51, 102)); // Azul oscuro
        cell.setPadding(8);
        cell.setBorderWidth(1);
        cell.setBorderColor(Color.WHITE);
        table.addCell(cell);
    }

    private void addInfoCell(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
        labelCell.setBorder(Rectangle.NO_BORDER);
        labelCell.setPadding(5);
        table.addCell(labelCell);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
        valueCell.setBorder(Rectangle.NO_BORDER);
        valueCell.setPadding(5);
        table.addCell(valueCell);
    }

    private void addTotalRow(PdfPTable table, String label, String value, Font font) {
        PdfPCell labelCell = new PdfPCell(new Phrase(label, font));
        labelCell.setBorder(Rectangle.NO_BORDER);
        labelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        labelCell.setPadding(5);
        table.addCell(labelCell);

        PdfPCell valueCell = new PdfPCell(new Phrase(value, font));
        valueCell.setBorder(Rectangle.NO_BORDER);
        valueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        valueCell.setPadding(5);
        table.addCell(valueCell);
    }
}
