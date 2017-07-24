package bank.core.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountCreatedEvent {
    private String accountId;
    private int overdrawLimit;
}
