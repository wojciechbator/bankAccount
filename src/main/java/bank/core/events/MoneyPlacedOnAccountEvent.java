package bank.core.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MoneyPlacedOnAccountEvent {
    private String accountId;
    private int amount;
    private int balance;
}
