package currency.impl.handlers;

import currency.*;
import currency.dto.UserSettingsDto;
import currency.impl.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.ArrayList;
import java.util.List;

public class InfoInTimeButtonHandler2 {
    private CurrencyService currencyService;
    private CurrencyRatePrettier currencyRatePrettier;

    public SendMessage sendMessage(Long userId, UserSettingsDto userSettings) {
        String userBank = userSettings.getBank();
        String userSignsAfterPoint = userSettings.getSignsAfterPoint();
        List<Currency> userCurrencies = new ArrayList<>();
        String userTime = userSettings.getTime();
        if (userBank.equals(Buttons.BANK1.get())) {
            currencyService = new CurrencyServicePrivateBankImpl();
        }
        if (userBank.equals(Buttons.BANK2.get())) {
            currencyService = new CurrencyServiceMonoBankImpl();
        }
            /*if (userBank.equals(Buttons.BANK3.get())) {
                currencyService = new CurrencyServiceNBUImpl();
            }*/
        if (userSignsAfterPoint.equals(Buttons.SIGNS1.get())) {
            currencyRatePrettier = new CurrencyRatePrettierImpl1();
        }
        if (userSignsAfterPoint.equals(Buttons.SIGNS2.get())) {
            currencyRatePrettier = new CurrencyRatePrettierImpl2();
        }
        if (userSignsAfterPoint.equals(Buttons.SIGNS3.get())) {
            currencyRatePrettier = new CurrencyRatePrettierImpl3();
        }

        SendMessage message = new SendMessage();
        for (Currency userCurrency: userCurrencies) {
            String prettyRate = getRate(String.valueOf(userCurrency));
            message.setText("Курс в " + userBank + " " + userCurrency + "/" + Currency.UAH + "\n" + prettyRate);
            message.setChatId(userId);
        }
        return message;
    }

    private String getRate(String ccy) {
        Currency currency = Currency.valueOf(ccy);
        return currencyRatePrettier.pretty(currencyService.getRateBuy(currency), currencyService.getRateSale(currency), currency);
    }
}

