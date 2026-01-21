package id.bni.nasabah.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RegisterNasabahRequestDto {

    @NotBlank(message = "NIK tidak boleh kosong")
    @Size(min = 16, max = 16, message = "NIK harus 16 digit")
    @Pattern(regexp = "\\d+", message = "NIK harus berupa angka")
    private String nik;

    @NotBlank(message = "Nama tidak boleh kosong")
    @Size(min = 2, max = 60,message = "Nama harus antara 2 hingga 60 karakter")
    @Pattern(regexp = "^[a-zA-Z\\s'.]+$",message = "Nama tidak valid")
    private String namaLengkap;

    @NotBlank(message = "Alamat tidak boleh kosong")
    private String alamat;

    @NotBlank(message = "Tempat lahir tidak boleh kosong")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Tempat lahir tidak valid")
    private String tempatLahir;

    @NotNull(message = "Tanggal lahir tidak boleh kosong")
    @Past(message = "Tanggal lahir tidak valid")
    private LocalDate tanggalLahir;

    @NotBlank(message = "Nomor HP tidak boleh kosong")
    @Pattern(regexp = "^08\\d{8,11}$")
    @Size(min = 10, max = 12)
    private String noHp;

    @NotBlank(message   = "Email tidak boleh kosong")
    @Email(message      = "Format email tidak sesuai")
    private String email;
}
