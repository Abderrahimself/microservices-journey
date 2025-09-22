package net.abderrahimself.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema for error response information"
)
public class ErrorResponseDto {
   @Schema(description = "The path of the API invoked when the error occurred")
    private String apiPath;
   @Schema(description = "The HTTP status code of the error")
    private HttpStatus errorCode;
   @Schema(description = "The error message")
    private String errorMessage;
   @Schema(description = "The time the error occurred")
    private LocalDateTime errorTime;
}
