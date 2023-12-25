package currency.dto;



import currency.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
public class UserSettingsDto {
    private String bank;
    private Set<Currency> currencies;
    private String signsAfterPoint;
    private String time;

}
