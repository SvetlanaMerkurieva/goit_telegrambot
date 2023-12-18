package currency;

import currency.Currency;

public interface CurrencyService {
    double getRateBuy(Currency ccy);
    double getRateSale(Currency ccy);
}
