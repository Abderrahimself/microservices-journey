package net.abderrahimself.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(
        name = "Accounts",
        description = "Accounts to hold account information of a customer",
        requiredProperties = {"accountNumber", "accountType", "branchAddress"}
)
public class AccountsDto {

    @Schema(
            description = "Account number (10 digits)",
            example = "1234567890"
    )
    @NotNull(message = "Account number is required")
    @Digits(integer = 10, fraction = 0, message = "Account number must be exactly 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Type of the account",
            example = "Savings"
    )
    @NotNull(message = "Account type is required")
    private String accountType;

    @Schema(
            description = "Branch address of the account",
            example = "123 Main Street, New York"
    )
    @NotNull(message = "Branch address is required")
    private String branchAddress;
}
