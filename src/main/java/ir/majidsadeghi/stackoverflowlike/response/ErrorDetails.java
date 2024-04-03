package ir.majidsadeghi.stackoverflowlike.response;

import lombok.Data;
import lombok.Getter;

import java.util.Date;


@Data
@Getter
public class ErrorDetails {
    private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
