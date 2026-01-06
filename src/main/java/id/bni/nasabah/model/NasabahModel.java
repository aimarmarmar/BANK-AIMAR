package id.bni.nasabah.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;



@Entity
@Table(name = "nasabah")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NasabahModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nasabah_id")
    private Long nasabahId;

    @Column(name ="no_cif")
    private String noCif;

    @Column(name = "nama_lengkap")
    private String namaLengkap;

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

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
