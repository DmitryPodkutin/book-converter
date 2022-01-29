package com.gmail.podkutin.dmitry.bookconverter.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class InformationAboutOrganization {

    @XmlElement(name = "СведИП")
    private Requisites requisites;

    public InformationAboutOrganization(Requisites requisites) {
        this.requisites = requisites;
    }
}