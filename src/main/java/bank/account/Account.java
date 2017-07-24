package bank.account;


import bank.core.commands.CreateAccountCommand;
import bank.core.commands.GetMoneyCommand;
import bank.core.events.AccountCreatedEvent;
import bank.core.events.MoneyTakenFromAccountEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@NoArgsConstructor
public class Account {
    @AggregateIdentifier
    private String accountId;
    private int balance;

    @CommandHandler
    public Account(CreateAccountCommand command) {
        apply(new AccountCreatedEvent(command.getAccountId(), command.getOverdrawLimit()));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        this.accountId = event.getAccountId();
    }

    @CommandHandler
    public void on(GetMoneyCommand command) {

        apply(new MoneyTakenFromAccountEvent(command.getAccountId(), command.getAmount(), this.balance - command.getAmount()));
    }

    @EventSourcingHandler
    public void on(MoneyTakenFromAccountEvent event) {
        this.balance -= event.getBalance();
    }
}
