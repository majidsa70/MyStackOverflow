package ir.majidsadeghi.stackoverflowlike.dto;

import ir.majidsadeghi.stackoverflowlike.entity.AnswerApproveStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * DTO for {@link ir.majidsadeghi.stackoverflowlike.entity.Answer}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    private Long id;
    private String answerText;
    private AnswerApproveStatus approveStatus;
    private UserDto user;
    private Instant createdAt;
}