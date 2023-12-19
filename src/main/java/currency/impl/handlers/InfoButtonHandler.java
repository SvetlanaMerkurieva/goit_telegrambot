package currency.impl.handlers;

import currency.*;
import currency.impl.CurrencyRatePrettierImpl1;
import currency.impl.CurrencyServicePrivateBankImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InfoButtonHandler implements Handlers {
    private final CurrencyService currencyService;
    private final CurrencyRatePrettier currencyRatePrettier;

    public InfoButtonHandler() {
        currencyService = new CurrencyServicePrivateBankImpl();
        //currencyService = new CurrencyServiceMonoBankImpl();
        currencyRatePrettier = new CurrencyRatePrettierImpl1();
    }

    @Override
    public SendMessage sendMessage(Update update) {
        String prettyRate = getRate(String.valueOf(Currency.USD));
        SendMessage message = new SendMessage();
        message.setText("Курс в Приватбанку: " + Currency.USD + "/" + Currency.UAH + "\n" + prettyRate);
        //message.setText("Курс в Монобанку: USD/UAH " + "\n" + prettyRate);
        message.setChatId(update.getCallbackQuery().getMessage().getChatId());
        InlineKeyboardButton infoButton = InlineKeyboardButton.builder().text(Buttons.INFO.get()).callbackData(Buttons.INFO.get()).build();
        InlineKeyboardButton settingsButton = InlineKeyboardButton.builder().text(Buttons.SETTINGS.get()).callbackData(Buttons.SETTINGS.get()).build();
        InlineKeyboardMarkup ikm = InlineKeyboardMarkup.builder().keyboard(Collections.singletonList(Arrays.asList(infoButton, settingsButton))).build();
        message.setReplyMarkup(ikm);

        return message;
    }

    private String getRate(String ccy) {
        Currency currency = Currency.valueOf(ccy);
        return currencyRatePrettier.pretty(currencyService.getRateBuy(currency), currencyService.getRateSale(currency), currency);
    }
}
