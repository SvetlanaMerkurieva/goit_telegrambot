package currency.impl.handlers;

import currency.Buttons;
import currency.CurrencyRatePrettier;
import currency.CurrencyService;
import currency.Handlers;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MessageHandler {
    public SendMessage sendMessage(Update update) {
        String receivedText = update.getMessage().getText();
        String text = "";
        List<String> buttonsTime = Arrays.asList(Buttons.TIME1.get(), Buttons.TIME2.get(), Buttons.TIME3.get(), Buttons.TIME4.get(), Buttons.TIME5.get(),Buttons.TIME6.get(), Buttons.TIME7.get(),Buttons.TIME8.get(),Buttons.TIME9.get(), Buttons.TIME10.get());
        SendMessage message = new SendMessage();
        if (receivedText.equals(Buttons.NOTIME.get())) {
            text = "Ви вимкнули час сповіщеннь, тому інформацію можете отримувати натискаючи кнопку Курс валют на зараз";
        }
        for (String button: buttonsTime) {
            if (receivedText.equals(button)) {
                text = "Ви обрали час надсилання повідомлень о " + receivedText;
            }
        }
        if (text.isEmpty()) {
            text = "Ви написали: " + receivedText + " Цей бот не має можливості опрацьовувати текст. Оберіть, будь ласка, кнопку";
        }
        message.setText(text);
        message.setChatId(update.getMessage().getChatId());

        InlineKeyboardButton further = InlineKeyboardButton.builder().text(Buttons.RESALT.get()).callbackData(Buttons.RESALT.get()).build();
        InlineKeyboardMarkup ikm = InlineKeyboardMarkup.builder().keyboard(Collections.singletonList(Arrays.asList(further))).build();
        message.setReplyMarkup(ikm);

        return message;
    }
}
