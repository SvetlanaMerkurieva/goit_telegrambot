package currency.impl;

import currency.Currency;
import currency.CurrencyRatePrettier;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CurrencyRatePrettierImpl implements CurrencyRatePrettier {
    public static final String FORMAT_BUY = "Покупка: %s";
    public static final String FORMAT_SALE = "Продаж: %s";

    @Override
    public String pretty(double rateBuy, double rateSale, Currency ccy) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        return String.format(FORMAT_BUY, df.format(rateBuy)) + "\n" + String.format(FORMAT_SALE, df.format(rateSale));
    }

}
