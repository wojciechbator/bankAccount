package bank.account;

import bank.account.exceptions.AvailableMoneyExceededException;
import bank.core.commands.CreateAccountCommand;
import bank.core.commands.GetMoneyCommand;
import bank.core.events.AccountCreatedEvent;
import bank.core.events.MoneyTakenFromAccountEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
    private FixtureConfiguration<Account> fixture;

    @Before
    public void init() throws Exception {
        fixture = new AggregateTestFixture<>(Account.class);
    }

    @Test
    public void testCreateAccount() throws Exception {
        fixture.givenNoPriorActivity()
                .when(new CreateAccountCommand("1", 5000))
                .expectEvents(new AccountCreatedEvent("1", 5000));
    }

    @Test
    public void testWithdrawSensibleAmountOfMoney() {
        fixture.given(new AccountCreatedEvent("1", 5000))
                .when(new GetMoneyCommand("1", 2000))
                .expectEvents(new MoneyTakenFromAccountEvent("1", 2000, -2000));
    }

    @Test
    public void testWithdrawRidiculousAmountOfMoney() {
        fixture.given(new AccountCreatedEvent("1", 2000))
                .when(new GetMoneyCommand("1", 5000))
                .expectNoEvents()
                .expectException(AvailableMoneyExceededException.class);
    }


    @Test
    public void testWithdrawMoneyTwiceProperly() {
        fixture.given(new AccountCreatedEvent("1", 5000),
                new MoneyTakenFromAccountEvent("1", 2000, -2000))
                .when(new GetMoneyCommand("1", 3000))
                .expectEvents(new MoneyTakenFromAccountEvent("1", 3000, -5000));
    }

    @Test
    public void testWithdrawMoneyTwiceFailure() {
        fixture.given(new AccountCreatedEvent("1", 5000),
                      new MoneyTakenFromAccountEvent("1", 4000, -4000))
                .when(new GetMoneyCommand("1", 1001))
                .expectNoEvents()
                .expectException(AvailableMoneyExceededException.class);
    }
}