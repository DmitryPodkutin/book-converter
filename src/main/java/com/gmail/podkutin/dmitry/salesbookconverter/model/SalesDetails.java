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
public class SalesDetails {

    @XmlAttribute(name = "НомПП")
    private String number;

    @XmlAttribute(name = "ДатаСчФ")
    private String dateInvoice;

    @XmlAttribute(name = "НомерСчФ")
    private String invoiceNumber;

    @XmlAttribute(name = "НаимПок")
    private String buyersName;

    @XmlAttribute(name = "ИННЮЛ")
    private String INN;

    @XmlAttribute(name = "ИННФЛ")
    private String INNFL;

    @XmlAttribute(name = "КПП")
    private String KPP;

    @XmlAttribute(name = "КодОКВ")
    private String codeOfOKV = "643";

    @XmlAttribute(name = "СтТовУчНалВсего")
    private String valueOfGoodsIncludingTax;

    @XmlAttribute(name = "СтТовУчНалРубКоп")
    private String valueOfGoodsIncludingTaxRub;

    @XmlAttribute(name = "СтТовРубКоп20")
    private String valueOfGoodsExcludingTax;

    @XmlAttribute(name = "СумНДСРубКоп20")
    private String taxAmount;

    @XmlElement(name = "КодВидОпер")
    private final String codeTypeOfOperation = "01";

    public SalesDetails(String number, String invoiceNumber, String dateInvoice, String buyersName, String INN, String KPP,
                        String codeOfOKV, String valueOfGoodsIncludingTaxRub, String valueOfGoodsExcludingTaxRub, String taxAmount) {
        this.number = number;
        this.invoiceNumber = invoiceNumber;
        this.dateInvoice = dateInvoice;
        this.buyersName = buyersName;
        if (KPP != null) {
            this.INN = INN;
        } else {
            this.INNFL = INN;
        }
        this.KPP = KPP;
        this.codeOfOKV = codeOfOKV;
        this.valueOfGoodsIncludingTaxRub = valueOfGoodsIncludingTaxRub;
        this.valueOfGoodsExcludingTax = valueOfGoodsExcludingTaxRub;
        this.taxAmount = taxAmount;
    }
}