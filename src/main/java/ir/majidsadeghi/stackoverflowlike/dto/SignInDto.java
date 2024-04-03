package ir.majidsadeghi.stackoverflowlike.dto;

import jakarta.validation.constraints.NotBlank;

public record SignInDto(
        @NotBlank
        String mobile,
        @NotBlank
        String password) {
}