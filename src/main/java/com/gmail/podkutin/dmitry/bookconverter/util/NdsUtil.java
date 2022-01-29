package com.gmail.podkutin.dmitry.bookconverter.util;

import com.gmail.podkutin.dmitry.bookconverter.model.buyerBook.BuyersDetails;

import java.text.DecimalFormat;

public class NdsUtil {

    private static final DecimalFormat df = new DecimalFormat("###.##");

    public static String getNds(String totalWithNds) {
        float total = Float.parseFloat(totalWithNds);
        return String.valueOf(df.format(total * 20 / 120)).replace(',', '.');
    }

    public static String getTotalWithoutNds(String totalWithNds) {
        float total = Float.parseFloat(totalWithNds);
        return String.valueOf(df.format(total / 1.20)).replace(',', '.');
    }

    public static String getTotalNDSExcludingGoodsWithoutNDS(String costOfGoodsIncludingTax, BuyersDetails.Including including) {
        float thisCostOfGoodsIncludingTax = Float.parseFloat(costOfGoodsIncludingTax);
        if (including == null) {
            return getNds(String.valueOf(thisCostOfGoodsIncludingTax - 0));
        }
        return getNds(String.valueOf(thisCostOfGoodsIncludingTax - Float.parseFloat(including.getCostOfGoodsWithoutNDS())));
    }

    public static String getTotalNDSExcludingGoodsWithoutNDS(String totalWithNds, String valueOfGoodsExcludingTax) {
        if (totalWithNds != null & valueOfGoodsExcludingTax != null) {
            float thisTotalWithNds = Float.parseFloat(totalWithNds);
            float thisValueOfGoodsExcludingTax = Float.parseFloat(valueOfGoodsExcludingTax);
            return getNds(String.valueOf(thisTotalWithNds - thisValueOfGoodsExcludingTax));
        }
        return null;
    }
}
