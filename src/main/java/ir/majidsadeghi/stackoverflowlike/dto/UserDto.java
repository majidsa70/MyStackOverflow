package ir.majidsadeghi.stackoverflowlike.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link ir.majidsadeghi.stackoverflowlike.entity.User}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String mobile;
    private Instant createdAt;
    private Instant updatedAt;
}