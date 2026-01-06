package id.bni.nasabah.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Value
@Builder
@AllArgsConstructor
public class NasabahResponseDto {

     String noCif;
     String nik;
     String namaLengkap;
     String alamat;
     String tempatLahir;
     LocalDate tanggalLahir;
     String noHp;
     String email;
     LocalDateTime createdAt;
}
