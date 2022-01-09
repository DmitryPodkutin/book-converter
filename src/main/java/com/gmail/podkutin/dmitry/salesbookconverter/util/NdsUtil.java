package com.gmail.podkutin.dmitry.salesbookconverter.util;
import java.text.DecimalFormat;

public class NdsUtil {

    private static final DecimalFormat df = new DecimalFormat("###.##");

    public static String getNds(String totalWithNds) {
        float total = Float.parseFloat(totalWithNds);
        return String.valueOf(df.format(total * 20 / 120)).replace(',','.');
    }

    public static String getTotalWithoutNds(String totalWithNds) {
        float total = Float.parseFloat(totalWithNds);
        return String.valueOf(df.format(total / 1.20)).replace(',','.');
    }
}
