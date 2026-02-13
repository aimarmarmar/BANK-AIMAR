package id.bni.nasabah.model.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NasabahResponseDto {

     private String noCif;
     private String nik;
     private String namaLengkap;
     private String alamat;
     private String tempatLahir;
     private LocalDate tanggalLahir;
     private String noHp;
     private String email;
}
