package bank;

import bank.account.Account;
import bank.core.commands.CreateAccountCommand;
import bank.core.commands.GetMoneyCommand;
import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        Configuration config = DefaultConfigurer.defaultConfiguration()
                .configureAggregate(Account.class)
                .configureEmbeddedEventStore(store -> new InMemoryEventStorageEngine())
                .buildConfiguration();
        config.start();
        config.commandBus().dispatch(asCommandMessage(new CreateAccountCommand("1", 5000)));
        config.commandBus().dispatch(asCommandMessage(new GetMoneyCommand("1", 2000)));

        SpringApplication.run(Application.class, args);
    }
}
