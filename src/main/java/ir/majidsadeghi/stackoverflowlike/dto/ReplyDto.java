package ir.majidsadeghi.stackoverflowlike.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link ir.majidsadeghi.stackoverflowlike.entity.Reply}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {
    Long id;
    String text;
    UserDto user;
    Instant createdAt;
}