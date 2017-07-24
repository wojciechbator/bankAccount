package bank.core.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class GetMoneyCommand {
    @TargetAggregateIdentifier
    private String accountId;
    private int amount;
}
