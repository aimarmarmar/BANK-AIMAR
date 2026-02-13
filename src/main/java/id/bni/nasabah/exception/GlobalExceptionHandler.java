package id.bni.nasabah.exception;

import id.bni.nasabah.constant.ResponseCode;
import id.bni.nasabah.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Object>> handleBusinessException(BusinessException ex
    ) {
        ApiResponse<Object> response = ApiResponse.error(
                ex.getResponseCode(),
                ex.getMessage()
        );
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
        log.warn("Validation Failed: {}", errorMessage);
        ApiResponse<Object> response = ApiResponse.error(
                ResponseCode.VALIDATION_ERROR.getCode(),
                errorMessage
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception ex) {
        log.error("Unhandled exception occurred: ", ex);

        ApiResponse<Object> response = ApiResponse.error(
                ResponseCode.INTERNAL_SERVER_ERROR.getCode(),
                ResponseCode.INTERNAL_SERVER_ERROR.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
