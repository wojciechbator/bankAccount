package bank.core.commands;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class GetMoneyCommand {
    @TargetAggregateIdentifier
    private String accountId;
    private int amount;

    public GetMoneyCommand(String accountId, int amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
