package bank.core.events;

public class AccountCreatedEvent {
    private String accountId;
    private int overdrawLimit;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getOverdrawLimit() {
        return overdrawLimit;
    }

    public void setOverdrawLimit(int overdrawLimit) {
        this.overdrawLimit = overdrawLimit;
    }

    public AccountCreatedEvent(String accountId, int overdrawLimit) {
        this.accountId = accountId;
        this.overdrawLimit = overdrawLimit;
    }
}
