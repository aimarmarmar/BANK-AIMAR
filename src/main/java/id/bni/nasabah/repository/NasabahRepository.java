package id.bni.nasabah.repository;

import id.bni.nasabah.model.NasabahModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NasabahRepository extends JpaRepository<NasabahModel, Long> {

    NasabahModel findByNik(String nik);



}
