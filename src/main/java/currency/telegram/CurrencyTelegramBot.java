package currency.telegram;

import currency.*;
import currency.dto.User;
import currency.dto.UserSettingsDto;
import currency.impl.handlers.*;
import currency.telegram.command.StartCommand;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyTelegramBot extends TelegramLongPollingCommandBot {

    public CurrencyTelegramBot() {
        register(new StartCommand());
    }

    @Override
    public String getBotUsername() {
        return LoginConstants.NAME;
    }
    @Override
    public String getBotToken() {
        return LoginConstants.TOKEN;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        SendMessage  message = new SendMessage();

        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();

            if (data.equals(Buttons.INFO.get())) {
                message = new InfoButtonHandler().sendMessage(update);
            }
            if (data.equals(Buttons.SETTINGS.get())) {
                message = new SettingsButtonHandler().sendMessage(update);
            }
            if (data.equals(Buttons.BANK.get())) {
                message = new BankButtonHandler().sendMessage(update);
            }
            if (data.equals(Buttons.CURRENCY.get())) {
                message = new CurrencyButtonHandler().sendMessage(update);
            }
            if (data.equals(Buttons.NUMSIGNS.get())) {
                message = new NumSignsButtonHandler().sendMessage(update);

            }
            if (data.equals(Buttons.TIME.get())) {
                message = new TimeButtonHandler().sendMessage(update);
            }

            try {
                execute(message);
            } catch (TelegramApiException e) {
                System.out.println("Щось пішло не так...");
            }
        }
        if (update.hasMessage()) {
            message = new MessageHandler().sendMessage(update);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                System.out.println("Щось пішло не так...");
            }
        }
    }

}
