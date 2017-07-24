package bank.core.events;

public class MoneyTakenFromAccountEvent {
    private String accountId;
    private int amount;
    private int balance;

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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public MoneyTakenFromAccountEvent(String accountId, int amount, int balance) {
        this.accountId = accountId;
        this.amount = amount;
        this.balance = balance;
    }
}
