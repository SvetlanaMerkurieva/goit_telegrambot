package currency.telegram;

import currency.*;
import currency.Currency;

import currency.dto.UserSettingsDto;
import currency.impl.handlers.*;
import currency.telegram.command.StartCommand;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

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
        Map<Long, UserSettingsDto> users = new HashMap<>();
        Long userId = update.getCallbackQuery().getMessage().getChatId();
        List<Currency> userCurrencies = new ArrayList<>();
        UserSettingsDto userSettings = new UserSettingsDto(Buttons.BANK1.get(), userCurrencies, Buttons.SIGNS1.get(), Buttons.NOTIME.get());
        users.put(userId, userSettings);

        Set<Long> keys = users.keySet();
        if (keys.contains(userId)) {
            userSettings = users.get(userId);
        }
        System.out.println(users);
        SendMessage  message = new SendMessage();

        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();

            if (data.equals(Buttons.INFO.get())) {
                message = new InfoButtonHandler().sendMessage(userId);
            }
            if (data.equals(Buttons.SETTINGS.get())) {
                message = new SettingsButtonHandler().sendMessage(userId);
            }
            if (data.equals(Buttons.BANK.get())) {
                message = new BankButtonHandler().sendMessage(userId);
            }
            if (data.equals(Buttons.BANK1.get())) {
                message = new ChoiceBank1ButtonHandler().sendMessage(userId);
                userSettings.setBank(data);
            }
            if (data.equals(Buttons.BANK2.get())) {
                message = new ChoiceBank2ButtonHandler().sendMessage(userId);
                userSettings.setBank(data);
            }
            if (data.equals(Buttons.BANK3.get())) {
                message = new ChoiceBank3ButtonHandler().sendMessage(userId);
                userSettings.setBank(data);
            }
            if (data.equals(Buttons.CURRENCY.get())) {
                message = new CurrencyButtonHandler().sendMessage(userId);
            }
            if (data.equals(String.valueOf(Currency.USD))) {
                message = new ChoiceCurrencyButtonUSDHandler().sendMessage(userId);
                userCurrencies.add(Currency.USD);
            }
            System.out.println(users.get(userId));
            if (data.equals(String.valueOf(Currency.EUR))) {
                if (userCurrencies.size() != 0) {
                message = new ChoiceCurrencyButtonEURHandler().sendMessage(userId);
                } else {
                message = new ChoiceCurrencyButtonUSDAndEURHandler().sendMessage(userId);
                }
                userCurrencies.add(Currency.EUR);
            }
            System.out.println(users.get(userId));
            if (data.equals(Buttons.NUMSIGNS.get())) {
                message = new NumSignsButtonHandler().sendMessage(userId);
            }
            if (data.equals(Buttons.SIGNS1.get())) {
                message = new ChoiceNumSigns1ButtonHandler().sendMessage(userId);
                userSettings.setSignsAfterPoint(data);

            }
            if (data.equals(Buttons.SIGNS2.get())) {
                message = new NumSignsButtonHandler().sendMessage(userId);
                userSettings.setSignsAfterPoint(data);
            }
            if (data.equals(Buttons.SIGNS3.get())) {
                message = new NumSignsButtonHandler().sendMessage(userId);
                userSettings.setSignsAfterPoint(data);
            }
            if (data.equals(Buttons.TIME.get())) {
                message = new TimeButtonHandler().sendMessage(userId);
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
