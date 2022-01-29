package com.gmail.podkutin.dmitry.bookconverter.model.salesBook;

import com.gmail.podkutin.dmitry.bookconverter.model.InformationAboutOrganization;
import com.gmail.podkutin.dmitry.bookconverter.model.Signer;
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
public class SalesDocument {

    @XmlElement(name = "СвПродав")
    private InformationAboutOrganization informationAboutOrganization;

    @XmlElement(name = "СвКнПрод")
    private SalesBookDetails salesBookDetails;

    @XmlAttribute(name = "КНД")
    private String attribute;

    @XmlElement(name = "СвПродаж")
    private List<SalesDetails> salesDetailsList;

    @XmlElement(name = "Подписант")
    private Signer signer;

    public SalesDocument(InformationAboutOrganization informationAboutOrganization, SalesBookDetails salesBookDetails, String attribute, Signer signer) {
        this.informationAboutOrganization = informationAboutOrganization;
        this.salesBookDetails = salesBookDetails;
        this.attribute = attribute;
        this.signer = signer;
    }

    public SalesDocument(InformationAboutOrganization informationAboutOrganization) {
        this.informationAboutOrganization = informationAboutOrganization;
    }
}
