package currency;

import com.vdurmont.emoji.EmojiParser;

public enum Icons {
    CHECK(":white_check_mark:");

    private final String value;
    public String get() {
        return EmojiParser.parseToUnicode(value);
    }
    Icons (String value) {
        this.value = value;
    }
}
