package id.bni.nasabah.util;

import org.springframework.stereotype.Component;

@Component
public class UtilityJava {

    public String maskNik(String nik) {
        if (nik == null || nik.length() < 4) return "****";
        return "******" + nik.substring(nik.length() - 4);
    }
}
