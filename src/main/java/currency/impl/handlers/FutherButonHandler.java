package currency.impl.handlers;

import currency.Buttons;
import currency.Handlers;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FutherButonHandler implements Handlers {
    @Override
    public SendMessage sendMessage(Long userId) {
        SendMessage message = new SendMessage();
        message.setText("Оберіть ще Валюту за потреби або натисніть Далі:");
        message.setChatId(userId);

        InlineKeyboardButton further = InlineKeyboardButton.builder().text(Buttons.FURTHER.get()).callbackData(Buttons.FURTHER.get()).build();
        InlineKeyboardMarkup ikm = InlineKeyboardMarkup.builder().keyboard(Collections.singletonList(Arrays.asList(further))).build();
        message.setReplyMarkup(ikm);


        return message;
    }

}
