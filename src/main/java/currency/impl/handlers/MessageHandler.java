package currency.impl.handlers;

import currency.Buttons;
import currency.CurrencyRatePrettier;
import currency.CurrencyService;
import currency.Handlers;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Arrays;
import java.util.List;

public class MessageHandler implements Handlers {
    @Override
    public SendMessage sendMessage(Update update) {
        String receivedText = update.getMessage().getText();
        String text = "";
        List<String> buttonsTime = Arrays.asList(Buttons.TIME1.get(), Buttons.TIME2.get(), Buttons.TIME3.get(), Buttons.TIME4.get(), Buttons.TIME5.get(),Buttons.TIME6.get(), Buttons.TIME7.get(),Buttons.TIME8.get(),Buttons.TIME9.get(), Buttons.TIME10.get());
        SendMessage message = new SendMessage();
        if (receivedText.equals(Buttons.NOTIME.get())) {
            text = "Ви вимкнули час сповіщеннь, тому інформацію можете отримувати натискаючи кнопку Отримати інфо";
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

        return message;
    }
}
