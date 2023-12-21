package currency.impl.handlers;

import currency.Buttons;
import currency.Handlers;
import currency.Icons;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;

public class ChoiceNumSigns3ButtonHandler implements Handlers {
    @Override
    public SendMessage sendMessage(Long userId) {
        SendMessage message = new SendMessage();
        message.setText("Ви обрали " + Buttons.SIGNS3.get() + " знака після коми:");
        message.setChatId(userId);

        InlineKeyboardButton sign1 = InlineKeyboardButton.builder().text(Buttons.SIGNS1.get()).callbackData(Buttons.SIGNS1.get()).build();
        InlineKeyboardButton sign2 = InlineKeyboardButton.builder().text(Buttons.SIGNS2.get()).callbackData(Buttons.SIGNS2.get()).build();
        InlineKeyboardButton sign3 = InlineKeyboardButton.builder().text(Icons.CHECK.get() + Buttons.SIGNS3.get()).callbackData(Buttons.SIGNS3.get()).build();
        InlineKeyboardMarkup ikm = InlineKeyboardMarkup.builder().keyboard(Collections.singletonList(Arrays.asList(sign1, sign2, sign3))).build();
        message.setReplyMarkup(ikm);
        return message;
    }
}

