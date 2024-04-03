package ir.majidsadeghi.stackoverflowlike.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateQuestionDto(
        @NotBlank
        String content
) {
}
