package com.gmail.podkutin.dmitry.salesbookconverter.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class Requisites {
    @XmlAttribute(name = "ИННФЛ")
    private String INNFL;
    @XmlAttribute(name = "СвГосРегИП")
    private static String registrationInformation;
    @XmlElement(name = "ФИОИП")
    private FIO fio;

    public Requisites(String INNFL, String registrationInformation, FIO fio) {
        this.INNFL = INNFL;
        Requisites.registrationInformation = registrationInformation;
        this.fio = fio;
    }
}
