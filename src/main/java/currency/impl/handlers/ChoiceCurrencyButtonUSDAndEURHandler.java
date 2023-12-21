package currency.impl.handlers;

import currency.Currency;
import currency.Handlers;
import currency.Icons;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;

public class ChoiceCurrencyButtonUSDAndEURHandler implements Handlers {
    @Override
    public SendMessage sendMessage(Long userId) {
        SendMessage message = new SendMessage();
        message.setText("Ви обрали: " + Currency.USD + " та " + Currency.EUR);
        message.setChatId(userId);

        InlineKeyboardButton usdButton = InlineKeyboardButton.builder().text(Icons.CHECK.get() + String.valueOf(Currency.USD)).callbackData(String.valueOf(Currency.USD)).build();
        InlineKeyboardButton eurButton = InlineKeyboardButton.builder().text(Icons.CHECK.get() + String.valueOf(Currency.EUR)).callbackData(String.valueOf(Currency.EUR)).build();
        InlineKeyboardMarkup ikm = InlineKeyboardMarkup.builder().keyboard(Collections.singletonList(Arrays.asList(usdButton, eurButton))).build();
        message.setReplyMarkup(ikm);
        return message;
    }
}
