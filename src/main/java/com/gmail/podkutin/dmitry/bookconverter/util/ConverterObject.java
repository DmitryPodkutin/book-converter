package com.gmail.podkutin.dmitry.bookconverter.util;

import com.gmail.podkutin.dmitry.bookconverter.model.buyerBook.BuyerBookDetails;
import com.gmail.podkutin.dmitry.bookconverter.model.buyerBook.BuyerTotal;
import com.gmail.podkutin.dmitry.bookconverter.model.buyerBook.BuyersDetails;
import com.gmail.podkutin.dmitry.bookconverter.model.buyerBook.ResultBuyersDetails;
import com.gmail.podkutin.dmitry.bookconverter.model.buyerBook.incoming.IncomingBuyerBook;
import com.gmail.podkutin.dmitry.bookconverter.model.buyerBook.incoming.IncomingBuyerDocument;
import com.gmail.podkutin.dmitry.bookconverter.model.buyerBook.result.ResultBuyerBook;
import com.gmail.podkutin.dmitry.bookconverter.model.buyerBook.result.ResultBuyerDocument;
import com.gmail.podkutin.dmitry.bookconverter.model.salesBook.SalesBook;
import com.gmail.podkutin.dmitry.bookconverter.model.salesBook.SalesDetails;
import com.gmail.podkutin.dmitry.bookconverter.model.salesBook.SalesDocument;
import com.gmail.podkutin.dmitry.bookconverter.model.salesBook.SalesTotal;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static com.gmail.podkutin.dmitry.bookconverter.util.NdsUtil.getNds;
import static com.gmail.podkutin.dmitry.bookconverter.util.NdsUtil.getTotalWithoutNds;

public class ConverterObject {

    public SalesBook convertSalesBookForTaxpayer(SalesBook salesBookIncoming) {
        final SalesDocument salesDocument = salesBookIncoming.getSalesDocument();
        final String totalWithNds = salesDocument.getSalesBookDetails().getSalesTotal().getTotalWithNds();
        salesDocument.getSalesBookDetails().setSalesTotal(new SalesTotal(getTotalWithoutNds(totalWithNds), getNds(totalWithNds)));
        salesDocument.setSalesDetailsList(getConvertedSalesDetailsList(salesDocument));
        return new SalesBook(salesDocument);
    }

    public ResultBuyerBook convertBuyerBookForTaxpayer(IncomingBuyerBook buyerBookIncoming) {
        final IncomingBuyerDocument document = buyerBookIncoming.getDocument();
        final BuyerBookDetails buyerBookDetails = document.getBuyerBookDetails();
        final BuyerTotal total = buyerBookDetails.getTotal();
        buyerBookDetails.setTotal(new BuyerTotal(NdsUtil.getTotalNDSExcludingGoodsWithoutNDS(total.getTotalWithNds(), total.getIncludingTotal().getValueOfGoodsExcludingTax())));
        return new ResultBuyerBook(new ResultBuyerDocument(document, getConvertedBuyersDetailsList(document.getBuyersDetailsList())));
    }

    private List<ResultBuyersDetails> getConvertedBuyersDetailsList(List<BuyersDetails> buyersDetails) { //https://javaee.github.io/jaxb-v2/doc/user-guide/ch03.html#annotating-your-classes-mapping-interfaces
       return buyersDetails.stream().peek(details -> {
            if (details.getBuyersName().equals("ИП Подкутин Дмитрий Геннадьевич")) {
                details.setCodeTypeOfOperation("22");
            }
        }).map(ResultBuyersDetails::new).collect(Collectors.toList());
    }

    private List<SalesDetails> getConvertedSalesDetailsList(SalesDocument salesDocument) {
        final AtomicInteger counter = new AtomicInteger(0);
        List<SalesDetails> resultList = new LinkedList<>();
        for (SalesDetails salesDetail : salesDocument.getSalesDetailsList()) {
            salesDetail.setNumber(String.valueOf(counter.incrementAndGet()));
            salesDetail.setValueOfGoodsIncludingTaxRub(salesDetail.getValueOfGoodsIncludingTax());
            salesDetail.setValueOfGoodsExcludingTax(getTotalWithoutNds(String.valueOf(salesDetail.getValueOfGoodsIncludingTax())));
            salesDetail.setTaxAmount(getNds(salesDetail.getValueOfGoodsIncludingTax()));
            salesDetail.setValueOfGoodsIncludingTax(null);
            resultList.add(salesDetail);
        }
        return resultList;
    }
}
