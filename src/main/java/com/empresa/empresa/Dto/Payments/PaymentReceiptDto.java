package com.empresa.empresa.Dto.Payments;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "paymentReceipt")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class PaymentReceiptDto {
    private String name;
    private String reference;
    private BigDecimal total;
    private Date paymentDate;
    private String status;
    private String paymentMethod;

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private List<ProductDto> products;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class ProductDto {
        private String nameProduct;
        private int quantity;
    }
}
