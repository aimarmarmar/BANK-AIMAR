package id.bni.nasabah.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "nasabah")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Nasabah {

    @Column(name = "no_cif")
    private String noCif;

    @Column(name = "nama_lengkap")
    private String namaLengkap;

    @Id
    @Column(name = "nik")
    private String nik;

    @Column(name = "tempat_lahir")
    private String tempatLahir;

    @Column(name = "tanggal_lahir")
    private LocalDate tanggalLahir;

    @Column(name = "alamat")
    private String alamat;

    @Column(name = "no_hp")
    private String noHp;

    @Column(name = "email")
    private String email;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "status")
    private String status;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
