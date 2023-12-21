package currency.dto;



import currency.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class UserSettingsDto {
    private String bank;
    private List<Currency> currencies;
    private String signsAfterPoint;
    private String time;

}
