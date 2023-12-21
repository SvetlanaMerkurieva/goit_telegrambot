package currency.impl.handlers;

import currency.*;
import currency.dto.UserSettingsDto;
import currency.impl.*;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InfoButtonHandler2 {
    private CurrencyService currencyService;
    private CurrencyRatePrettier currencyRatePrettier;


    public SendMessage sendMessage(Long id, UserSettingsDto userSettings) {
        String userBank = userSettings.getBank();
        String userSignsAfterPoint = userSettings.getSignsAfterPoint();
        List<Currency> userCurrencies = userSettings.getCurrencies();
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
            message.setChatId(id);
            message.
        }
        return message;
    }

    private String getRate(String ccy) {
        Currency currency = Currency.valueOf(ccy);
        return currencyRatePrettier.pretty(currencyService.getRateBuy(currency), currencyService.getRateSale(currency), currency);
    }
}
