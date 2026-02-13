package id.bni.nasabah.constant;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ResponseCode {

    SUCCESS("200", "Success", HttpStatus.OK),

    // Validation
    VALIDATION_ERROR("400", "BAD REQUEST", HttpStatus.BAD_REQUEST),

    // Data Logic
    NASABAH_NOT_FOUND("404", "Data nasabah tidak ditemukan", HttpStatus.NOT_FOUND),
    DUPLICATE_NIK("409", "NIK sudah terdaftar", HttpStatus.CONFLICT),

    // System
    INTERNAL_SERVER_ERROR("500", "Terjadi kesalahan internal", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    ResponseCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}