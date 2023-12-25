package currency.impl.handlers;

import currency.Buttons;
import currency.Handlers;
import currency.Icons;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;

public class ChoiceBank1and3ButtonHandler implements Handlers {
    @Override
    public SendMessage sendMessage(Long userId) {
        SendMessage message = new SendMessage();
        message.setText("Ви обрали Банк: " + Buttons.BANK1.get());
        message.setChatId(userId);

        InlineKeyboardButton bank1 = InlineKeyboardButton.builder().text(Icons.CHECK.get() + " " + Buttons.BANK1.get()).callbackData(Buttons.BANK1.get()).build();
        InlineKeyboardButton bank2 = InlineKeyboardButton.builder().text(Buttons.BANK2.get()).callbackData(Buttons.BANK2.get()).build();
        InlineKeyboardButton bank3 = InlineKeyboardButton.builder().text(Icons.CHECK.get() + " " + Buttons.BANK3.get()).callbackData(Buttons.BANK3.get()).build();
        InlineKeyboardMarkup ikm = InlineKeyboardMarkup.builder().keyboard(Collections.singletonList(Arrays.asList(bank1, bank2, bank3))).build();
        message.setReplyMarkup(ikm);

        return message;

    }
}