package id.bni.nasabah.model;

import id.bni.nasabah.constant.ResponseCode;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {

    private String responseCode;
    private String responseMessage;
    private LocalDateTime timestamp;
    private T data;

    // SUCCESS Response
    public static <T> ApiResponse<T> success(ResponseCode responseCode, T data) {
        return ApiResponse.<T>builder()
                .responseCode(responseCode.getCode())
                .responseMessage(responseCode.getMessage())
                .timestamp(LocalDateTime.now())
                .data(data)
                .build();
    }

    // ERROR Response
    public static <T> ApiResponse<T> error(String code, String message) {
        return ApiResponse.<T>builder()
                .responseCode(code)
                .responseMessage(message)
                .timestamp(LocalDateTime.now())
                .data(null)
                .build();
    }

    public static <T> ApiResponse<T> pagination(ResponseCode responseCode, T data) {
        return ApiResponse.<T>builder()
                .responseCode(responseCode.getCode())
                .responseMessage(responseCode.getMessage())
                .timestamp(LocalDateTime.now())
                .data(data)
                .build();
    }
}