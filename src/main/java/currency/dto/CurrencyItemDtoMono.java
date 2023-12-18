package currency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CurrencyItemDtoMono {
    private int currencyCodeA;
    private int currencyCodeB;
    private double rateBuy;
    private double rateSell;
    private double rateCross;
    private int date;
}
