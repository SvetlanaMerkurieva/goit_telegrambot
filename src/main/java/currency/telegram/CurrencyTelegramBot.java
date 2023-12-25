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
    Map<Long, UserSettingsDto> users = new HashMap<>();
    Set<Currency> userCurrencies = new HashSet<>();
    UserSettingsDto userSettings = new UserSettingsDto();

    @Override
    public void processNonCommandUpdate(Update update) {
        SendMessage  message = new SendMessage();
        SendMessage messageSet = new SendMessage();
        SendMessage messageFurther = new SendMessage();

        if (update.hasMessage()) {
            String replyText = update.getMessage().getText();
            message = new MessageHandler().sendMessage(update);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                System.out.println("Щось пішло не так...");
            }
            String textTime = new TimeReplyMessageHandlers().getTimeReply(replyText);
            userSettings.setTime(textTime);
        }
        Long userId = update.getCallbackQuery().getMessage().getChatId();

        Set<Long> keys = users.keySet();
        if (keys.contains(userId)) {
            userSettings = users.get(userId);
        }

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
                message =  new ChoiceBank1ButtonHandler().sendMessage(userId);
                userSettings.setBank(Buttons.BANK1.get());
                messageSet = new SettingsButtonHandler().sendMessage(userId);
            }
            if (data.equals(Buttons.BANK2.get())) {
                message =  new ChoiceBank2ButtonHandler().sendMessage(userId);
                userSettings.setBank(Buttons.BANK2.get());
                messageSet = new SettingsButtonHandler().sendMessage(userId);
            }
            if (data.equals(Buttons.BANK3.get())) {
                message =  new ChoiceBank3ButtonHandler().sendMessage(userId);
                userSettings.setBank(Buttons.BANK3.get());
                messageSet = new SettingsButtonHandler().sendMessage(userId);
            }
            if (data.equals(Buttons.CURRENCY.get())) {
                message = new CurrencyButtonHandler().sendMessage(userId);
            }
            if (data.equals(Buttons.FURTHER.get())) {
                messageSet = new SettingsButtonHandler().sendMessage(userId);
                try {
                    execute(messageSet);
                } catch (TelegramApiException e) {
                    System.out.println("Щось пішло не так...");
                }
            }
            if (data.equals(Buttons.RESALT.get())) {
                messageSet = new InfoAllSettings().sendMessage(userId, userSettings);
                try {
                    execute(messageSet);
                } catch (TelegramApiException e) {
                    System.out.println("Щось пішло не так...");
                }
            }
            if (data.equals(Buttons.INFONOW.get())) {
                message = new InfoInTimeButtonHandler2().sendMessage(userId, userSettings);
            }
            if (data.equals(String.valueOf(Currency.USD))) {
                if (userSettings.getCurrencies() == null) {
                    message = new ChoiceCurrencyButtonUSDHandler().sendMessage(userId);
                    messageFurther = new FutherButonHandler().sendMessage(userId);
                    try {
                        execute(messageFurther);
                    } catch (TelegramApiException e) {
                        System.out.println("Щось пішло не так...");
                    }
                } else {
                    message = new ChoiceCurrencyButtonUSDAndEURHandler().sendMessage(userId);
                    messageSet = new SettingsButtonHandlerWithBankCurrency().sendMessage(userId);
                }
                userCurrencies.add(Currency.USD);
                userSettings.setCurrencies(userCurrencies);
            }

            if (data.equals(String.valueOf(Currency.EUR))) {
                if (userSettings.getCurrencies() == null) {
                    message = new ChoiceCurrencyButtonEURHandler().sendMessage(userId);
                    messageFurther = new FutherButonHandler().sendMessage(userId);
                    try {
                        execute(messageFurther);
                    } catch (TelegramApiException e) {
                        System.out.println("Щось пішло не так...");
                    }
                } else {
                    message = new ChoiceCurrencyButtonUSDAndEURHandler().sendMessage(userId);
                    messageSet = new SettingsButtonHandler().sendMessage(userId);
                }
                userCurrencies.add(Currency.EUR);
                userSettings.setCurrencies(userCurrencies);
            }

            if (data.equals(Buttons.NUMSIGNS.get())) {
                message = new NumSignsButtonHandler().sendMessage(userId);
            }
            if (data.equals(Buttons.SIGNS1.get())) {
                message = new ChoiceNumSigns1ButtonHandler().sendMessage(userId);
                userSettings.setSignsAfterPoint(data);
                messageSet = new SettingsButtonHandler().sendMessage(userId);
            }
            if (data.equals(Buttons.SIGNS2.get())) {
                message = new ChoiceNumSigns2ButtonHandler().sendMessage(userId);
                userSettings.setSignsAfterPoint(data);
                messageSet = new SettingsButtonHandler().sendMessage(userId);
            }
            if (data.equals(Buttons.SIGNS3.get())) {
                message = new ChoiceNumSigns3ButtonHandler().sendMessage(userId);
                userSettings.setSignsAfterPoint(data);
                messageSet = new SettingsButtonHandler().sendMessage(userId);
            }
            if (data.equals(Buttons.TIME.get())) {
                message = new TimeButtonHandler().sendMessage(userId);
            }
            try {
                execute(message);
            } catch (TelegramApiException e) {
                System.out.println("Щось пішло не так...");
            }
            try {
                execute(messageSet);
            } catch (TelegramApiException e) {
                System.out.println("Щось пішло не так...");
            }
            System.out.println(users.get(userId));
        }
        users.put(userId, userSettings);
    }
}
