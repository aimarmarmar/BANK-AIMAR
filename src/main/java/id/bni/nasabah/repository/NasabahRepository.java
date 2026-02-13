package id.bni.nasabah.repository;

import id.bni.nasabah.model.entity.Nasabah;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NasabahRepository extends JpaRepository<Nasabah, String> {

    Nasabah findByNik(String nik);

    Nasabah findByNikAndStatus(String nik, String status);

    Page<Nasabah> getNasabahByStatus(String status, Pageable pageable);
}
