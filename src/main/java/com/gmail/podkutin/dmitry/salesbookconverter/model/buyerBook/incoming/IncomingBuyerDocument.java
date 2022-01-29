package com.gmail.podkutin.dmitry.salesbookconverter.model.buyerBook.incoming;

import com.gmail.podkutin.dmitry.salesbookconverter.model.InformationAboutOrganization;
import com.gmail.podkutin.dmitry.salesbookconverter.model.Signer;
import com.gmail.podkutin.dmitry.salesbookconverter.model.buyerBook.BuyerBookDetails;
import com.gmail.podkutin.dmitry.salesbookconverter.model.buyerBook.BuyersDetails;
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
public class IncomingBuyerDocument {

    @XmlElement(name = "СвПокуп")
    private InformationAboutOrganization informationAboutOrganization;

    @XmlElement(name = "СвКнПок")
    private BuyerBookDetails buyerBookDetails;

    @XmlAttribute(name = "КНД")
    private String attribute;

    @XmlElement(name = "СвПокупка")
    private List<BuyersDetails> buyersDetailsList;

    @XmlElement(name = "Подписант")
    private Signer signer;
}
