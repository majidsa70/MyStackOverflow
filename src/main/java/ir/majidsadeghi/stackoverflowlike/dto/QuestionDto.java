package ir.majidsadeghi.stackoverflowlike.dto;

import ir.majidsadeghi.stackoverflowlike.entity.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link ir.majidsadeghi.stackoverflowlike.entity.Question}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    private Long id;
    private String questionText;
    private UserDto user;
    private Set<AnswerDto> answers;
    private Instant createdAt;
}