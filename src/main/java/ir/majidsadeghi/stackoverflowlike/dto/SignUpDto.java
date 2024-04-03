package ir.majidsadeghi.stackoverflowlike.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignUpDto(
        @NotBlank
        @Size(max = 20,min = 3,message = "not valid")
        String name,
        @NotBlank
        String mobile,
        @NotBlank
        String password,

        @NotNull
        long stockAmount
) {
}