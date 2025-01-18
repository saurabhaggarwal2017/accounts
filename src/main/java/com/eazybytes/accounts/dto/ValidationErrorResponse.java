package com.eazybytes.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Schema(
        name = "ValidationErrorResponse",
        description = "Schema to hold validation error response information"
)
@Data
@AllArgsConstructor
public class ValidationErrorResponse {
    @Schema(
            description = "API path invoked by client"
    )
    private String apiPath;
    @Schema(
            description = "number of errors occurred."
    )
    private int numberOfError;
    @Schema(
            description = "Error code representing the error happened"
    )
    private String errorCode;
    @Schema(
            description = "Error message representing the error happened"
    )
    private String errorMessage;
    @Schema(
            description = "errors representing all fields error."
    )
    private Map<String, String> errors;
    @Schema(
            description = "Time representing when the error happened"
    )
    private LocalDateTime errorTime;
}
