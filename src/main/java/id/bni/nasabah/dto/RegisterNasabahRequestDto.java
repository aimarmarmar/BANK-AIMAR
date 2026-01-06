package id.bni.nasabah.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RegisterNasabahRequestDto {

    @NotBlank(message = "NIK tidak boleh kosong")
    private String nik;

    @NotBlank(message = "Nama tidak boleh kosong")
    private String namaLengkap;

    @NotBlank(message = "Alamat tidak boleh kosong")
    private String alamat;

    @NotBlank(message = "Tempat lahir tidak boleh kosong")
    private String tempatLahir;

    @NotNull(message = "Tanggal lahir tidak boleh kosong")
    private LocalDate tanggalLahir;

    @NotBlank(message = "Nomor HP tidak boleh kosong")
    private String noHp;

    @NotBlank(message = "Email tidak boleh kosong")
    private String email;
}
