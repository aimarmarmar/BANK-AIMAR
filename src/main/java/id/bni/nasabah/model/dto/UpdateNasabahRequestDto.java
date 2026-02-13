package id.bni.nasabah.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateNasabahRequestDto {
    private String alamat;
    private String noHp;
}

