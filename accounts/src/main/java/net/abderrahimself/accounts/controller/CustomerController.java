package net.abderrahimself.accounts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import net.abderrahimself.accounts.dto.CustomerDetailsDto;
import net.abderrahimself.accounts.dto.ErrorResponseDto;
import net.abderrahimself.accounts.service.ICustomersService;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Customer Controller", description = "Customer Controller")
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CustomerController {

    private ICustomersService iCustomersService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CustomerController(ICustomersService iCustomersService) {
        this.iCustomersService = iCustomersService;
    }

    @Operation(
            summary = "Fetch Customer Details",
            description = "Fetch Customer Details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer details fetched successfully"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "An error occurred. Please try again or contact Dev team",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("abderrahimself-correlation-id") String correlationId,
                                                                        @RequestParam
                                                                       @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits") String mobileNumber

    ) {
        logger.debug("abderrahimself-correlation-id found", correlationId);
        CustomerDetailsDto customerDetailsDto = iCustomersService.fetchCustomerDetails(mobileNumber, correlationId);
        return ResponseEntity.status(HttpStatus.SC_OK).body(customerDetailsDto);
    }
}
