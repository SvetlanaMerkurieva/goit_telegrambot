package currency.telegram;

import currency.*;
import currency.impl.CurrencyRatePrettierImpl;
import currency.impl.CurrencyServiceMonoBankImpl;
import currency.impl.CurrencyServicePrivateBankImpl;
import currency.telegram.command.StartCommand;

import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CurrencyTelegramBot extends TelegramLongPollingCommandBot {
    private final CurrencyService currencyService;
    private final CurrencyRatePrettier currencyRatePrettier;

    public CurrencyTelegramBot() {
        currencyService = new CurrencyServicePrivateBankImpl();
        //currencyService = new CurrencyServiceMonoBankImpl();
        currencyRatePrettier = new CurrencyRatePrettierImpl();
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
        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            System.out.println(data);
            if (data.equals(Buttons.INFO.get())) {
                String prettyRate = getRate(String.valueOf(Currency.USD));
                SendMessage message = new SendMessage();
                message.setText("Курс в Приватбанку: " + Currency.USD + "/" + Currency.UAH + "\n" + prettyRate);
                //message.setText("Курс в Монобанку: USD/UAH " + "\n" + prettyRate);
                message.setChatId(update.getCallbackQuery().getMessage().getChatId());
                InlineKeyboardButton infoButton = InlineKeyboardButton.builder().text(Buttons.INFO.get()).callbackData(Buttons.INFO.get()).build();
                InlineKeyboardButton settingsButton = InlineKeyboardButton.builder().text(Buttons.SETTINGS.get()).callbackData(Buttons.SETTINGS.get()).build();
                InlineKeyboardMarkup ikm = InlineKeyboardMarkup.builder().keyboard(Collections.singletonList(Arrays.asList(infoButton, settingsButton))).build();
                message.setReplyMarkup(ikm);
                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    System.out.println("Щось пішло не так...");
                }
            }
            if (data.equals(Buttons.SETTINGS.get())) {
                SendMessage message = new SendMessage();
                message.setText("Оберіть налаштування:");
                message.setChatId(update.getCallbackQuery().getMessage().getChatId());

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

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    System.out.println("Щось пішло не так...");
                }

            }
            if (data.equals(Buttons.BANK.get())) {
                SendMessage message = new SendMessage();
                message.setText("Оберіть Банк:");
                message.setChatId(update.getCallbackQuery().getMessage().getChatId());

                InlineKeyboardButton bank1 = InlineKeyboardButton.builder().text(Buttons.BANK1.get()).callbackData(Buttons.BANK1.get()).build();
                InlineKeyboardButton bank2 = InlineKeyboardButton.builder().text(Buttons.BANK2.get()).callbackData(Buttons.BANK2.get()).build();
                InlineKeyboardButton bank3 = InlineKeyboardButton.builder().text(Buttons.BANK3.get()).callbackData(Buttons.BANK1.get()).build();
                InlineKeyboardMarkup ikm = InlineKeyboardMarkup.builder().keyboard(Collections.singletonList(Arrays.asList(bank1, bank2, bank3))).build();
                message.setReplyMarkup(ikm);

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    System.out.println("Щось пішло не так...");
                }
            }
            if (data.equals(Buttons.CURRENCY.get())) {
                SendMessage message = new SendMessage();
                message.setText("Оберіть Валюту:");
                message.setChatId(update.getCallbackQuery().getMessage().getChatId());

                InlineKeyboardButton usdButton = InlineKeyboardButton.builder().text(String.valueOf(Currency.USD)).callbackData(String.valueOf(Currency.USD)).build();
                InlineKeyboardButton eurButton = InlineKeyboardButton.builder().text(String.valueOf(Currency.EUR)).callbackData(String.valueOf(Currency.EUR)).build();
                InlineKeyboardMarkup ikm = InlineKeyboardMarkup.builder().keyboard(Collections.singletonList(Arrays.asList(usdButton, eurButton))).build();
                message.setReplyMarkup(ikm);

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    System.out.println("Щось пішло не так...");
                }
            }
            if (data.equals(Buttons.NUMSIGNS.get())) {
                SendMessage message = new SendMessage();
                message.setText("Оберіть Кількість знаків після коми:");
                message.setChatId(update.getCallbackQuery().getMessage().getChatId());

                InlineKeyboardButton sign1 = InlineKeyboardButton.builder().text(Buttons.SIGNS1.get()).callbackData(Buttons.SIGNS1.get()).build();
                InlineKeyboardButton sign2 = InlineKeyboardButton.builder().text(Buttons.SIGNS2.get()).callbackData(Buttons.SIGNS2.get()).build();
                InlineKeyboardButton sign3 = InlineKeyboardButton.builder().text(Buttons.SIGNS3.get()).callbackData(Buttons.SIGNS3.get()).build();
                InlineKeyboardMarkup ikm = InlineKeyboardMarkup.builder().keyboard(Collections.singletonList(Arrays.asList(sign1, sign2, sign3))).build();
                message.setReplyMarkup(ikm);

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    System.out.println("Щось пішло не так...");
                }
            }

            if (data.equals(Buttons.TIME.get())) {
                SendMessage message = new SendMessage();
                message.setText("Оберіть Час оповіщення:");
                message.setChatId(update.getCallbackQuery().getMessage().getChatId());

                List<KeyboardRow> keyboards = new ArrayList<>();
                KeyboardRow keyboardFirstRow = new KeyboardRow();
                keyboardFirstRow.add(new KeyboardButton(Buttons.TIME1.get()));
                keyboardFirstRow.add(new KeyboardButton(Buttons.TIME2.get()));
                keyboardFirstRow.add(new KeyboardButton(Buttons.TIME3.get()));

                KeyboardRow keyboardSecondRow = new KeyboardRow();
                keyboardSecondRow.add(new KeyboardButton(Buttons.TIME4.get()));
                keyboardSecondRow.add(new KeyboardButton(Buttons.TIME5.get()));
                keyboardSecondRow.add(new KeyboardButton(Buttons.TIME6.get()));

                KeyboardRow keyboardThirdRow = new KeyboardRow();
                keyboardThirdRow.add(new KeyboardButton(Buttons.TIME7.get()));
                keyboardThirdRow.add(new KeyboardButton(Buttons.TIME8.get()));
                keyboardThirdRow.add(new KeyboardButton(Buttons.TIME9.get()));

                KeyboardRow keyboardForthRow = new KeyboardRow();
                keyboardForthRow.add(new KeyboardButton(Buttons.TIME10.get()));
                keyboardForthRow.add(new KeyboardButton(Buttons.NOTIME.get()));

                keyboards.add(keyboardFirstRow);
                keyboards.add(keyboardSecondRow);
                keyboards.add(keyboardThirdRow);
                keyboards.add(keyboardForthRow);

                ReplyKeyboardMarkup rkm = new ReplyKeyboardMarkup();

                rkm.setKeyboard(keyboards);
                message.setReplyMarkup(rkm);

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    System.out.println("Щось пішло не так...");
                }
            }

        }
        if (update.hasMessage()) {
            String receivedText = update.getMessage().getText();
            List <String> buttonsTime = Arrays.asList(Buttons.TIME1.get(), Buttons.TIME2.get(), Buttons.TIME3.get(), Buttons.TIME4.get(), Buttons.TIME5.get(),Buttons.TIME6.get(), Buttons.TIME7.get(),Buttons.TIME8.get(),Buttons.TIME9.get(), Buttons.TIME10.get());
            SendMessage sm = new SendMessage();
            if (receivedText.equals(Buttons.NOTIME.get())) {
                sm.setText("Ви вимкнули час сповіщеннь, тому інформацію можете отримувати натискаючи кнопку Отримати інфо");
            }
            for (String button: buttonsTime) {
                if (receivedText.equals(button)) {
                    sm.setText("Ви обрали час надсилання повідомлень о " + receivedText);
                }
            }
            sm.setText("Ви написали: " + receivedText + " Цей бот не має можливості опрацьовувати текст. Оберіть, будь ласка, кнопку");
            sm.setChatId(update.getMessage().getChatId());

            try {
                execute(sm);
            } catch (TelegramApiException e) {
                System.out.println("Щось пішло не так...");
            }
        }
    }
    private String getRate (String ccy) {
        Currency currency = Currency.valueOf(ccy);
        System.out.println(ccy);
        return currencyRatePrettier.pretty(currencyService.getRateBuy(currency), currencyService.getRateSale(currency), currency);
    }

}
