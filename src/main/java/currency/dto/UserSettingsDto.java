package currency.dto;



import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class UserSettingsDto {
    private String bank;
    private List<String> currencies;
    private int signsAfterPoint;
    private String time;

}
