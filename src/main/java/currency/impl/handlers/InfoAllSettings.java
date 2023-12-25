package currency.impl.handlers;

import currency.Buttons;
import currency.Currency;
import currency.dto.UserSettingsDto;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

public class InfoAllSettings {
    public SendMessage sendMessage(Long userId, UserSettingsDto userSettings) {
        String userBank = userSettings.getBank();
        String userSignsAfterPoint = userSettings.getSignsAfterPoint();
        Set<Currency> userCurrencies = userSettings.getCurrencies();
        String userTime = userSettings.getTime();

        SendMessage message = new SendMessage();
        message.setText("Ваші Налаштування:" + "\n" + "Банк: " + userBank + "\n" + "Валюта: " + userCurrencies + "\n" + "Кількість знаків після коми: " + userSignsAfterPoint + "\n" + "Час оповіщеня: " + userTime + "\n" + "Якщо буде потрібно змінити налаштування, введіть команду /start");
        message.setChatId(userId);
        InlineKeyboardButton infoNow = InlineKeyboardButton.builder().text(Buttons.INFONOW.get()).callbackData(Buttons.INFONOW.get()).build();
        InlineKeyboardMarkup ikm = InlineKeyboardMarkup.builder().keyboard(Collections.singletonList(Arrays.asList(infoNow))).build();
        message.setReplyMarkup(ikm);

        return message;


    }
}
