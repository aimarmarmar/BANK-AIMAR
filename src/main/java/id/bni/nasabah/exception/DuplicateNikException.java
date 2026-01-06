package id.bni.nasabah.exception;

import id.bni.nasabah.constant.ResponseCode;
import org.springframework.http.HttpStatus;

public class DuplicateNikException extends BusinessException {

    public DuplicateNikException(String nik) {

        super(ResponseCode.DUPLICATE_NIK,"Nasabah dengan NIK " + nik + " sudah terdaftar.", HttpStatus.CONFLICT);

    }
}
