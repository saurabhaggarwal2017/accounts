package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.*;
import com.eazybytes.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Account controller Rest APIs",
        description = "All basic crud operation like create, read, update, delete."
)
@Validated
@RequestMapping(value = "/api/v1/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@RestController
@RefreshScope
public class AccountController {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

//    @Value("${build.version}")
    private String version;

    @Autowired
    private Environment environment;
    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;


    @Operation(
            method = "POST",
            description = "REST API to create new Customer &  Account.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "HTTP Status CREATED"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "HTTP Status BAD_REQUEST",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "HTTP Status BAD_REQUEST for validation failed.",
                            content = @Content(
                                    schema = @Schema(implementation = ValidationErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status INTERNAL_SERVER_ERROR",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @PostMapping
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(HttpStatus.CREATED.toString(), "Account successfully created!"));
    }

    @Operation(
            method = "GET",
            description = "REST API to get Customer &  Account details through mobile number.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status OK"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "HTTP Status BAD_REQUEST",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status INTERNAL_SERVER_ERROR",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @GetMapping
    public ResponseEntity<CustomerDto> getAccountDetails(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            @RequestParam("mobileNumber") String mobileNumber) {
        CustomerDto response = accountService.getAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(
            method = "PUT",
            description = "REST API to update Customer &  Account details through mobile number.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status OK"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "HTTP Status BAD_REQUEST",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "HTTP Status BAD_REQUEST validation failed",
                            content = @Content(
                                    schema = @Schema(implementation = ValidationErrorResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status INTERNAL_SERVER_ERROR",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @PutMapping()
    public ResponseEntity<ResponseDto> updateAccountDetails(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            @RequestParam("mobileNumber") String mobileNumber,
            @Valid @RequestBody CustomerUpdateDto customerUpdateDto) {
        accountService.updateAccount(mobileNumber, customerUpdateDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.toString(), "update customer details successfully!"));
    }

    @Operation(
            method = "DELETE",
            description = "REST API to delete Customer &  Account details through mobile number.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "HTTP Status OK"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "HTTP Status BAD_REQUEST",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "HTTP Status INTERNAL_SERVER_ERROR",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            }
    )
    @DeleteMapping()
    public ResponseEntity<ResponseDto> deleteAccount(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
            @RequestParam("mobileNumber") String mobileNumber) {
        accountService.deleteAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(HttpStatus.OK.toString(), "Delete customer successfully!"));
    }

    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("build.version"));
    }

    @GetMapping("/java-version")
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("java.home"));
    }

    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(accountsContactInfoDto);
    }

}

