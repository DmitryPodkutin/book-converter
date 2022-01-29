package com.gmail.podkutin.dmitry.bookconverter.model.buyerBook;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class BuyersDetails {

    @XmlAttribute(name = "НомПП")
    private String number;

    @XmlAttribute(name = "НомерСчФ")
    private String invoiceNumber;

    @XmlAttribute(name = "ДатаСчФ")
    private String dateInvoice;

    @XmlAttribute(name = "ДатаПринУчет")
    private String registrationDate;

    @XmlAttribute(name = "НаимПрод")
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

    @XmlAttribute(name = "СтПокСчФВал")
    private String valueOfGoodsIncludingTaxVal;

    @XmlElement(name = "ВтчСтоимПок")
    private Including including;

    @XmlElement(name = "КодВидОпер")
    private String codeTypeOfOperation = "01";

    @XmlElement(name = "ДатаОплСчФПрод")
    private String dateOfPayment;

    @XmlElement(name = "ДатаПринУчет")
    private String dateOfRegistration;

    @XmlElement(name = "СумНДСВыч")
    private TaxDeduction taxDeduction;

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class Including {
        @XmlAttribute(name = "СтТовБезНДС")
        private String costOfGoodsWithoutNDS;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class TaxDeduction {
        @XmlElement(name = "СумНДС")
        private String taxAmount;

        public TaxDeduction(String taxAmount) {
            this.taxAmount = taxAmount;
        }
    }
}