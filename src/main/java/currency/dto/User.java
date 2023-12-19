package currency.dto;



import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private long userId;
    private int signsAfterPoint;
    private String bank;
    private List<String> currencies;
    private String time;
    
}
