package id.bni.nasabah.service;

import id.bni.nasabah.dto.NasabahResponseDto;
import id.bni.nasabah.dto.RegisterNasabahRequestDto;
import id.bni.nasabah.dto.UpdateNasabahRequestDto;
import id.bni.nasabah.exception.DuplicateNikException;
import id.bni.nasabah.exception.NasabahNotFoundException;
import id.bni.nasabah.model.NasabahModel;
import id.bni.nasabah.repository.NasabahRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class NasabahService {

    private final NasabahRepository nasabahRepository;

    /* =========================
       REGISTER NASABAH
       ========================= */
    public NasabahResponseDto  registerNasabah(RegisterNasabahRequestDto requestDto
    ) {

        if (nasabahRepository.findByNik(requestDto.getNik()) != null) {
            throw new DuplicateNikException(requestDto.getNik());
        }

        String noCif = generateNoCif();

        NasabahModel nasabah = new NasabahModel();
        nasabah.setNoCif(noCif);
        nasabah.setNik(requestDto.getNik());
        nasabah.setNamaLengkap(requestDto.getNamaLengkap());
        nasabah.setAlamat(requestDto.getAlamat());
        nasabah.setTempatLahir(requestDto.getTempatLahir());
        nasabah.setTanggalLahir(requestDto.getTanggalLahir());
        nasabah.setNoHp(requestDto.getNoHp());
        nasabah.setEmail(requestDto.getEmail());

        NasabahModel saved = nasabahRepository.save(nasabah);
        return toResponseDto(saved);
    }
    /* =========================
       GET NASABAH BY NIK
       ========================= */
    public NasabahResponseDto getNasabahByNik(String nik) {

        NasabahModel nasabah = nasabahRepository.findByNik(nik);
        if (nasabah == null) {
            throw new NasabahNotFoundException(nik);
        }

        return toResponseDto(nasabah);
    }
    /* =========================
       GET ALL NASABAH (PAGINATION)
       ========================= */
    public Page<NasabahResponseDto> getAllNasabah(Pageable pageable) {

        return nasabahRepository
                .findAll(pageable)
                .map(this::toResponseDto);
    }
    /* =========================
       UPDATE NASABAH
       ========================= */
    public NasabahResponseDto updateNasabah(String nik, UpdateNasabahRequestDto requestDto) {

        NasabahModel nasabah = nasabahRepository.findByNik(nik);
        if (nasabah == null) {
            throw new NasabahNotFoundException(nik);
        }

        if (requestDto.getAlamat() != null && !requestDto.getAlamat().isBlank()) {
            nasabah.setAlamat(requestDto.getAlamat());
        }

        if (requestDto.getNoHp() != null && !requestDto.getNoHp().isBlank()) {
            nasabah.setNoHp(requestDto.getNoHp());
        }

        NasabahModel updated = nasabahRepository.save(nasabah);
        return toResponseDto(updated);
    }
    /* =========================
       DELETE NASABAH
       ========================= */
    public void deleteNasabahByNik(String nik) {

        NasabahModel nasabah = nasabahRepository.findByNik(nik);
        if (nasabah == null) {
            throw new NasabahNotFoundException(nik);
        }

        nasabahRepository.delete(nasabah);
    }

    /* =========================
       PRIVATE UTIL
       ========================= */
    private String generateNoCif() {
        String datePart = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int randomPart = ThreadLocalRandom
                .current()
                .nextInt(100000, 1000000);

        return datePart + randomPart;
    }

    private NasabahResponseDto toResponseDto(NasabahModel nasabah) {

        return NasabahResponseDto.builder()
                .noCif(nasabah.getNoCif())
                .nik(nasabah.getNik())
                .namaLengkap(nasabah.getNamaLengkap())
                .alamat(nasabah.getAlamat())
                .tempatLahir(nasabah.getTempatLahir())
                .tanggalLahir(nasabah.getTanggalLahir())
                .noHp(nasabah.getNoHp())
                .email(nasabah.getEmail())
                .createdAt(nasabah.getCreatedAt())
                .build();
    }
}
