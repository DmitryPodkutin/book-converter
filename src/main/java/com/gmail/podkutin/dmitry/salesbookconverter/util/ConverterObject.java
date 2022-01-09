package com.gmail.podkutin.dmitry.salesbookconverter.util;

import com.gmail.podkutin.dmitry.salesbookconverter.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.gmail.podkutin.dmitry.salesbookconverter.util.NdsUtil.getNds;
import static com.gmail.podkutin.dmitry.salesbookconverter.util.NdsUtil.getTotalWithoutNds;

public class ConverterObject {

    @Autowired
    private SalesBook salesBook;

    public SalesBook convertSalesBookFor1C(SalesBook salesBookIncoming) {
        final Document document = salesBookIncoming.getDocument();
        final Total total = document.getSalesBookDetails().getTotal();
        final String totalWithNds = total.getTotalWithNds();

        total.setTotalNds(getNds(totalWithNds));
        total.setCostOfGoodsWithoutNds(getTotalWithoutNds(totalWithNds));
        document.setSalesDetailsList(getConvertedSalesDetailsList(document));
        total.setTotalWithNds(null);
        salesBook.setDocument(document);
        return salesBook;
    }

    private static List<SalesDetails> getConvertedSalesDetailsList(Document document) {
        final AtomicInteger counter = new AtomicInteger(0);
        List<SalesDetails> salesDetailsList = new ArrayList<>();
        for (SalesDetails salesDetail : document.getSalesDetailsList()) {
            SalesDetails salesDetails = new SalesDetails(String.valueOf(counter.incrementAndGet())
                    , salesDetail.getInvoiceNumber()
                    , salesDetail.getDateInvoice()
                    , salesDetail.getBuyersName()
                    , salesDetail.getKPP() != null ? salesDetail.getINN() : salesDetail.getINNFL()
                    , salesDetail.getKPP()
                    , salesDetail.getCodeOfOKV()
                    , salesDetail.getValueOfGoodsIncludingTax()
                    , getTotalWithoutNds(salesDetail.getValueOfGoodsIncludingTax())
                    , getNds(salesDetail.getValueOfGoodsIncludingTax()));
            salesDetailsList.add(salesDetails);
        }
        return salesDetailsList;
    }
}
