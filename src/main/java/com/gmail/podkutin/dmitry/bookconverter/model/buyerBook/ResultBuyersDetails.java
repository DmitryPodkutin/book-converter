package com.gmail.podkutin.dmitry.bookconverter.model.buyerBook;

import com.gmail.podkutin.dmitry.bookconverter.util.NdsUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class ResultBuyersDetails {

    @XmlAttribute(name = "НомПП")
    private String number;

    @XmlAttribute(name = "НомерСчФ")
    private String invoiceNumber;

    @XmlAttribute(name = "ДатаСчФ")
    private String dateInvoice;

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

    @XmlAttribute(name = "СтПокСчФВал")
    private String valueOfGoodsIncludingTaxVal;

    @XmlElement(name = "КодВидОпер")
    private String codeTypeOfOperation = "01";

    @XmlElement(name = "ДатаПринУчет")
    private String dateOfRegistration;

    @XmlElement(name = "СумНДСВыч")
    private BuyersDetails.TaxDeduction taxDeduction;

    public ResultBuyersDetails(BuyersDetails buyersDetails) {
        this.number = buyersDetails.getNumber();
        this.invoiceNumber = buyersDetails.getInvoiceNumber();
        this.dateInvoice = buyersDetails.getDateInvoice();
        this.buyersName = buyersDetails.getBuyersName();
        if (buyersDetails.getKPP() != null) {
            this.INN = buyersDetails.getINN();
        } else {
            this.INNFL = buyersDetails.getINNFL();
        }
        this.KPP = buyersDetails.getKPP();
        this.codeOfOKV = buyersDetails.getCodeOfOKV();
        this.valueOfGoodsIncludingTaxVal = buyersDetails.getValueOfGoodsIncludingTax();
        this.codeTypeOfOperation = buyersDetails.getCodeTypeOfOperation();
        this.dateOfRegistration = buyersDetails.getRegistrationDate();
        this.taxDeduction = new BuyersDetails.TaxDeduction(NdsUtil.getTotalNDSExcludingGoodsWithoutNDS(buyersDetails.getValueOfGoodsIncludingTax(), buyersDetails.getIncluding()));
    }
}