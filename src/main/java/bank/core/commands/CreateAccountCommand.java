package bank.core.commands;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateAccountCommand {
    private String accountId;
    private int overdrawLimit;
}
