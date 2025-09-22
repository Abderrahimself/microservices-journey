package net.abderrahimself.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold successful response ",
        requiredProperties = {"statusCode", "statusMessage"}
)
public class ResponseDto {

    @Schema(
            description = "Status code of the response",
            example = "201"
    )
    private String statusCode;

    @Schema(
            description = "Status message of the response",
            example = "Account created successfully"
    )
    private String statusMessage;
}
