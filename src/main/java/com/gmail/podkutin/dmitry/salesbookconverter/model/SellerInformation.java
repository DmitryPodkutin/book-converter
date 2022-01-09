package com.gmail.podkutin.dmitry.salesbookconverter.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class SellerInformation {

    @XmlElement(name = "СведИП")
    private Requisites requisites;

    public SellerInformation(Requisites requisites) {
        this.requisites = requisites;
    }
}