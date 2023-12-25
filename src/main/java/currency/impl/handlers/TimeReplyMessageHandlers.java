package currency.impl.handlers;

import currency.Buttons;

public class TimeReplyMessageHandlers {
    public String getTimeReply (String replyText) {
        String text = "";
        switch (replyText) {
            case "09-00":
                text = Buttons.TIME1.get();
                break;
            case "10-00":
                text = Buttons.TIME2.get();
                break;
            case "11-00":
                text = Buttons.TIME3.get();
                break;
            case "12-00":
                text = Buttons.TIME4.get();
                break;
            case "13-00":
                text = Buttons.TIME5.get();
                break;
            case "14-00":
                text = Buttons.TIME6.get();
                break;
            case "15-00":
                text = Buttons.TIME7.get();
                break;
            case "16-00":
                text = Buttons.TIME8.get();
                break;
            case "17-00":
                text = Buttons.TIME9.get();
                break;
            case "18-00":
                text = Buttons.TIME10.get();
                break;
            case "Вимкнути повідомлення":
                text = Buttons.NOTIME.get();
                break;
        }
        return text;
    }
}
