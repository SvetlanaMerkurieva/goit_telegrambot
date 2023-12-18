package currency.dto;

import currency.Currency;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyItemDtoPrivat {
    private Currency ccy;
    private Currency base_ccy;
    private String buy;
    private String sale;
}
