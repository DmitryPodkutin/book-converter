package com.gmail.podkutin.dmitry.bookconverter.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@Data
public class FIO {
    @XmlAttribute(name = "Фамилия")
    private String surname;

    @XmlAttribute(name = "Имя")
    private String name;

    @XmlAttribute(name = "Отчество")
    private String patronymic;
}
