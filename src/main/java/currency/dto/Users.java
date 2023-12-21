package currency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class Users {
    private Long userId;
    private UserSettingsDto userSettings;
    Map<Long, UserSettingsDto> users = new HashMap<>();

    public UserSettingsDto userSettings() {
        return null;
    }
}
