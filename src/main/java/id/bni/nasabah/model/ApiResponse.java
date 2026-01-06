package id.bni.nasabah.model;

import id.bni.nasabah.constant.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

    private String responseCode;
    private String responseMessage;
    private LocalDateTime timestamp;
    private T data;

    // Method untuk SUKSES
    public static <T> ApiResponse<T> success(ResponseCode responseCode, T data) {
        return ApiResponse.<T>builder()
                .responseCode(responseCode.getCode())
                .responseMessage(responseCode.getMessage())
                .timestamp(LocalDateTime.now())
                .data(data)
                .build();
    }

    // Method untuk ERROR
    public static <T> ApiResponse<T> error(String code, String message) {
        return ApiResponse.<T>builder()
                .responseCode(code)
                .responseMessage(message)
                .timestamp(LocalDateTime.now())
                .data(null)
                .build();
    }
}