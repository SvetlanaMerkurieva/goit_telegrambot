package currency.telegram.command;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.Collections;

public class StartCommand extends BotCommand {
    public StartCommand() {
        super("start", "Start command");
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String text = "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют";
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(chat.getId());
        InlineKeyboardButton infoButton = InlineKeyboardButton.builder().text("Отримати інфо").callbackData("Отримати інфо").build();
        InlineKeyboardButton settingsButton = InlineKeyboardButton.builder().text("Налаштування").callbackData("Налаштування").build();
        InlineKeyboardMarkup ikm = InlineKeyboardMarkup.builder().keyboard(Collections.singletonList(Arrays.asList(infoButton, settingsButton))).build();
        message.setReplyMarkup(ikm);
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            System.out.println("Помилка!");
        }
    }
}
