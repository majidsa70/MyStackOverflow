package ir.majidsadeghi.stackoverflowlike.response;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class BaseResponse<T> {
    boolean success = false;
    @Nullable T data = null;
    @Nullable ErrorDetails error = null;

    @Nullable String message;

    public BaseResponse(boolean success, @Nullable T data, @Nullable ErrorDetails error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    public BaseResponse(boolean success, @Nullable T data, @Nullable ErrorDetails error, @Nullable String message) {
        this.success = success;
        this.data = data;
        this.error = error;
        this.message = message;
    }
}