package ir.majidsadeghi.stackoverflowlike.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAnswerDto(
        @NotBlank
        @Size(min = 3, max = 50)
        String content
) {
}
