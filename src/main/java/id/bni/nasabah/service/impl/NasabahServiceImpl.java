package id.bni.nasabah.service.impl;

import id.bni.nasabah.constant.ResponseCode;
import id.bni.nasabah.exception.BusinessException;
import id.bni.nasabah.exception.DuplicateNikException;
import id.bni.nasabah.exception.NasabahNotFoundException;
import id.bni.nasabah.model.ApiResponse;
import id.bni.nasabah.model.dto.NasabahResponseDto;
import id.bni.nasabah.model.dto.PaginationResponseDto;
import id.bni.nasabah.model.dto.RegisterNasabahRequestDto;
import id.bni.nasabah.model.dto.UpdateNasabahRequestDto;
import id.bni.nasabah.model.entity.Nasabah;
import id.bni.nasabah.repository.NasabahRepository;
import id.bni.nasabah.service.NasabahService;
import id.bni.nasabah.util.UtilityJava;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
@AllArgsConstructor
public class NasabahServiceImpl implements NasabahService {

    private final NasabahRepository nasabahRepository;
    private final UtilityJava utility;

    @Override
    public NasabahResponseDto registerNasabah(RegisterNasabahRequestDto requestDto) {

        log.info("Processing register for NIK: {}", utility.maskNik(requestDto.getNik()));

        if (nasabahRepository.findByNik(requestDto.getNik()) != null) {
            log.warn("Duplicate NIK detected: {}", utility.maskNik(requestDto.getNik()));
            throw new DuplicateNikException(requestDto.getNik());
        }

        int umur = java.time.Period.between(requestDto.getTanggalLahir(), LocalDate.now()).getYears();

        if (umur < 17) {
            log.warn("Underage registration attempt for NIK: {}", utility.maskNik(requestDto.getNik()));
            throw new BusinessException(ResponseCode.VALIDATION_ERROR, "Nasabah harus berusia minimal 17 tahun", HttpStatus.BAD_REQUEST);
        }

        String noCif = generateNoCif();

        Nasabah nasabah = new Nasabah();
        nasabah.setNoCif(noCif);
        nasabah.setNik(requestDto.getNik());
        nasabah.setNamaLengkap(requestDto.getNamaLengkap());
        nasabah.setAlamat(requestDto.getAlamat());
        nasabah.setTempatLahir(requestDto.getTempatLahir());
        nasabah.setTanggalLahir(requestDto.getTanggalLahir());
        nasabah.setNoHp(requestDto.getNoHp());
        nasabah.setEmail(requestDto.getEmail());
        nasabah.setStatus("ACTIVE");

        Nasabah saved = nasabahRepository.save(nasabah);

        log.info("Nasabah successfully registered with CIF: {}", saved.getNoCif());
        return toResponseDto(saved);
    }

    /* GET NASABAH BY NIK */
    @Override
    public NasabahResponseDto getNasabahByNik(String nik) {

        Nasabah nasabah = nasabahRepository.findByNikAndStatus(nik, "ACTIVE");
        if (nasabah == null) {
            log.warn("Nasabah not found for NIK: {}", utility.maskNik(nik));
            throw new NasabahNotFoundException(nik);
        }
        log.info("Nasabah successfully found for NIK: {}", utility.maskNik(nik));
        return toResponseDto(nasabah);
    }

    /* GET ALL NASABAH (PAGINATION) */
    @Override
    public ApiResponse<PaginationResponseDto<NasabahResponseDto>> getAllNasabah(Pageable pageable) {

        log.info("Processing getAllNasabah with pagination - page: {}, size: {}", pageable.getPageNumber(), pageable.getPageSize());

        Page<Nasabah> pageResult =
                nasabahRepository.getNasabahByStatus("ACTIVE", pageable);

        log.info("Found Total Elements:{} Total Pages:{} Number Of Elements:{}", pageResult.getTotalElements(), pageResult.getTotalPages(), pageResult.getNumberOfElements());

        Page<NasabahResponseDto> dtoPage =
                pageResult.map(this::toResponseDto);

        PaginationResponseDto<NasabahResponseDto> pagination =
                PaginationResponseDto.<NasabahResponseDto>builder()
                        .items(dtoPage.getContent())
                        .page(dtoPage.getNumber())
                        .size(dtoPage.getSize())
                        .totalElements(dtoPage.getTotalElements())
                        .totalPages(dtoPage.getTotalPages())
                        .hasNext(dtoPage.hasNext())
                        .hasPrevious(dtoPage.hasPrevious())
                        .build();

        return ApiResponse.pagination(ResponseCode.SUCCESS, pagination);
    }

    /* UPDATE NASABAH */
    @Override
    public NasabahResponseDto updateNasabah(String nik, UpdateNasabahRequestDto requestDto) {

        log.info("Processing update for NIK: {}", utility.maskNik(nik));

        Nasabah nasabah = nasabahRepository.findByNikAndStatus(nik, "ACTIVE");
        if (nasabah == null) {
            log.warn("Nasabah not found for NIK: {}", utility.maskNik(nik));
            throw new NasabahNotFoundException(nik);
        }

        if (requestDto.getAlamat() != null && !requestDto.getAlamat().isBlank()) {
            nasabah.setAlamat(requestDto.getAlamat());
        }

        if (requestDto.getNoHp() != null && !requestDto.getNoHp().isBlank()) {
            nasabah.setNoHp(requestDto.getNoHp());
        }

        Nasabah updated = nasabahRepository.save(nasabah);
        log.info("Nasabah successfully updated with NIK: {}", utility.maskNik(nik));
        return toResponseDto(updated);
    }

    /* DELETE NASABAH */

    @Override
    public void deleteNasabahByNik(String nik) {

        Nasabah nasabah = nasabahRepository.findByNikAndStatus(nik, "ACTIVE");
        if (nasabah == null) {
            log.warn("Nasabah not found for NIK: {}", utility.maskNik(nik));
            throw new NasabahNotFoundException(nik);
        }


        // Soft delete: set status to INACTIVE
        nasabah.setStatus("INACTIVE");
        nasabahRepository.save(nasabah);
        log.info("Nasabah dengan NIK {} berhasil dihapus (status INACTIVE)", utility.maskNik(nik));

    }

    /* PRIVATE UTIL */

    private String generateNoCif() {
        String datePart = LocalDate.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int randomPart = ThreadLocalRandom
                .current()
                .nextInt(100000, 1000000);

        return datePart + randomPart;
    }

    private NasabahResponseDto toResponseDto(Nasabah nasabah) {

        return NasabahResponseDto.builder()
                .noCif(nasabah.getNoCif())
                .nik(nasabah.getNik())
                .namaLengkap(nasabah.getNamaLengkap())
                .alamat(nasabah.getAlamat())
                .tempatLahir(nasabah.getTempatLahir())
                .tanggalLahir(nasabah.getTanggalLahir())
                .noHp(nasabah.getNoHp())
                .email(nasabah.getEmail())
                .build();
    }
}
