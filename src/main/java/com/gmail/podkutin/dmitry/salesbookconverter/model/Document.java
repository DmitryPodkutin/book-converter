package com.gmail.podkutin.dmitry.salesbookconverter.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class Document {

    @XmlElement(name = "СвПродав")
    private SellerInformation sellerInformation;

    @XmlElement(name = "СвКнПрод")
    private SalesBookDetails salesBookDetails;

    @XmlAttribute(name = "КНД")
    private String attribute;

    @XmlElement(name = "СвПродаж")
    private List<SalesDetails> salesDetailsList;

    @XmlElement(name = "Подписант")
    private Signer signer;

    public Document(SellerInformation sellerInformation, SalesBookDetails salesBookDetails, String attribute, Signer signer) {
        this.sellerInformation = sellerInformation;
        this.salesBookDetails = salesBookDetails;
        this.attribute = attribute;
        this.signer = signer;
    }
}
