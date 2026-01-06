package id.bni.nasabah.constant;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS("20000", "Sukses"),

    // Validation
    VALIDATION_ERROR("40001", "Validasi gagal"),
    MISSING_MANDATORY_FIELD("40002", "Field wajib belum diisi"),

    // Data Logic
    NASABAH_NOT_FOUND("40401", "Data nasabah tidak ditemukan"),
    DUPLICATE_NIK("40901", "NIK sudah terdaftar"),

    // System
    INTERNAL_SERVER_ERROR("50000", "Terjadi kesalahan internal");

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}