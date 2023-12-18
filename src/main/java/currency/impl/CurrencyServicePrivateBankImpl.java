package currency.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import currency.Currency;
import currency.dto.CurrencyItemDtoPrivat;
import currency.CurrencyService;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class CurrencyServicePrivateBankImpl implements CurrencyService {
    String url = "https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=5";
    String response;

    {
        try {
            response = Jsoup.connect(url).ignoreContentType(true).get().body().text();
        } catch (IOException e) {
            System.out.println("Помилка під час запиту валюти");
        }
    }
    Type type = TypeToken.getParameterized(List.class, CurrencyItemDtoPrivat.class).getType();
    List<CurrencyItemDtoPrivat> list = new Gson().fromJson(response, type);

    String strBuy = "";
    String strSale = "";
    @Override
    public double getRateBuy(Currency ccy) {
         strBuy = list.stream()
                .filter(c -> c.getCcy() == ccy)
                .filter(c -> c.getBase_ccy() == Currency.UAH)
                .map(CurrencyItemDtoPrivat::getBuy)
                .findFirst()
                .orElseThrow();

        return Double.parseDouble(strBuy);
    }

    @Override
    public double getRateSale(Currency ccy) {
        strSale = list.stream()
                .filter(c -> c.getCcy() == ccy)
                .filter(c -> c.getBase_ccy() == Currency.UAH)
                .map(CurrencyItemDtoPrivat::getSale)
                .findFirst()
                .orElseThrow();
        return Double.parseDouble(strSale);
    }
}
