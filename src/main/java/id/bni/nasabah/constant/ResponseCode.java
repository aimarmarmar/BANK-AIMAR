package id.bni.nasabah.constant;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS("200", "Success"),

    // Validation
    VALIDATION_ERROR("400", "Validasi gagal"),

    // Data Logic
    NASABAH_NOT_FOUND("404", "Data nasabah tidak ditemukan"),
    DUPLICATE_NIK("409", "NIK sudah terdaftar"),

    // System
    INTERNAL_SERVER_ERROR("500", "Terjadi kesalahan internal");

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}