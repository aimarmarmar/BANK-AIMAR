package id.bni.nasabah.exception;

import id.bni.nasabah.constant.ResponseCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

    private final String responseCode;
    private final HttpStatus status;

    protected BusinessException(ResponseCode responseCode, String message, HttpStatus status) {
        super(message);
        this.responseCode = responseCode.getCode();
        this.status = status;
    }
}

