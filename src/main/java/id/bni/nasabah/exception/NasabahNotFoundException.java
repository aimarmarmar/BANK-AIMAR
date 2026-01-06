package id.bni.nasabah.exception;

import id.bni.nasabah.constant.ResponseCode;
import org.springframework.http.HttpStatus;

public class NasabahNotFoundException extends BusinessException {

    public NasabahNotFoundException(String nik) {
        super(ResponseCode.NASABAH_NOT_FOUND,"Nasabah dengan NIK " + nik + " tidak ditemukan", HttpStatus.NOT_FOUND);
    }

}
