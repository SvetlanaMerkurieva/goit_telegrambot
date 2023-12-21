package currency.impl.handlers;

import currency.Buttons;
import currency.Handlers;
import currency.Icons;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SettingsButtonHandler implements Handlers {
    @Override
    public SendMessage sendMessage(Long userId) {
        SendMessage message = new SendMessage();
        message.setText("Налаштування:");
        message.setChatId(userId);

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();
        InlineKeyboardButton bankButton = InlineKeyboardButton.builder().text(Buttons.BANK.get()).callbackData(Buttons.BANK.get()).build();
        InlineKeyboardButton currencyButton = InlineKeyboardButton.builder().text(Buttons.CURRENCY.get()).callbackData(Buttons.CURRENCY.get()).build();

        rowInline1.add(bankButton);
        rowInline1.add(currencyButton);

        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        InlineKeyboardButton numSignsButton = InlineKeyboardButton.builder().text(Buttons.NUMSIGNS.get()).callbackData(Buttons.NUMSIGNS.get()).build();
        rowInline2.add(numSignsButton);

        List<InlineKeyboardButton> rowInline3 = new ArrayList<>();
        InlineKeyboardButton timeButton = InlineKeyboardButton.builder().text(Buttons.TIME.get()).callbackData(Buttons.TIME.get()).build();
        rowInline3.add(timeButton);

        InlineKeyboardMarkup ikm = new InlineKeyboardMarkup();
        rowsInline.add(rowInline1);
        rowsInline.add(rowInline2);
        rowsInline.add(rowInline3);

        ikm.setKeyboard(rowsInline);
        message.setReplyMarkup(ikm);

        return message;
    }
}
