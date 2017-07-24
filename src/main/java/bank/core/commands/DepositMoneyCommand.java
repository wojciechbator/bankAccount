package bank.core.commands;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DepositMoneyCommand {
    private String accountId;
    private int amount;
}
