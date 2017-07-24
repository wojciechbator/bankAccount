package bank.account;


import bank.account.exceptions.AvailableMoneyExceededException;
import bank.core.commands.CreateAccountCommand;
import bank.core.commands.GetMoneyCommand;
import bank.core.events.AccountCreatedEvent;
import bank.core.events.MoneyTakenFromAccountEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

public class Account {
    private final Log log = LogFactory.getLog(Account.class);

    @AggregateIdentifier
    private String accountId;
    private int balance;
    private int overdraftLimit;

    @CommandHandler
    public Account(CreateAccountCommand command) {
        apply(new AccountCreatedEvent(command.getAccountId(), command.getOverdrawLimit()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.accountId = event.getAccountId();
        this.overdraftLimit = event.getOverdrawLimit();
    }

    @CommandHandler
    public void on(GetMoneyCommand command) throws AvailableMoneyExceededException {
        if (this.balance + this.overdraftLimit >= command.getAmount()) {
            apply(new MoneyTakenFromAccountEvent(command.getAccountId(), command.getAmount(), this.balance - command.getAmount()));
        }
        else {
            throw new AvailableMoneyExceededException();
        }
    }

    @EventSourcingHandler
    public void on(MoneyTakenFromAccountEvent event) {
        this.balance = event.getBalance();
    }

    public Account() {
    }
}
