package id.bni.nasabah.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateNasabahRequestDto {
    private String alamat;
    private String noHp;
}

