package net.abderrahimself.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Error response object carrying details about an error",
        requiredProperties = {"apiPath", "errorCode", "errorMessage", "errorTime"}
)
public class ErrorResponseDto {

    @Schema(
            description = "Path of the API that caused the error",
            example = "/api/update"
    )
    private String apiPath;

    @Schema(
            description = "HTTP status code of the error",
            example = "NOT_FOUND"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error message describing the issue",
            example = "Accounts not found with accountNumber : '1642131433'"
    )
    private String errorMessage;

    @Schema(
            description = "Timestamp when the error occurred",
            example = "2025-07-09T19:48:11.02278075"
    )
    private LocalDateTime errorTime;
}
