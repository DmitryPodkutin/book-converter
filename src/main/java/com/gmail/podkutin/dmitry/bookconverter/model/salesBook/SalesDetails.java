package com.gmail.podkutin.dmitry.bookconverter.model.salesBook;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import static com.gmail.podkutin.dmitry.bookconverter.util.NdsUtil.getNds;
import static com.gmail.podkutin.dmitry.bookconverter.util.NdsUtil.getTotalWithoutNds;

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

    public SalesDetails(SalesDetails details) {
        this.number = details.getNumber();
        this.invoiceNumber = details.getInvoiceNumber();
        this.dateInvoice = details.getDateInvoice();
        this.buyersName = details.buyersName;
        if (details.getKPP() != null) {
            this.INN = details.getINN();
        } else {
            this.INNFL = details.getINNFL();
        }
        this.KPP = details.getKPP();
        this.codeOfOKV = details.codeOfOKV;
        this.valueOfGoodsIncludingTaxRub = details.getValueOfGoodsIncludingTax();
        this.valueOfGoodsExcludingTax = getTotalWithoutNds(String.valueOf(details.getValueOfGoodsIncludingTax()));
        this.taxAmount = getNds(details.getValueOfGoodsIncludingTax());
        this.valueOfGoodsIncludingTax = null;
    }
}