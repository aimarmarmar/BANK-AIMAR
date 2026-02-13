package id.bni.nasabah.service;

import id.bni.nasabah.model.ApiResponse;
import id.bni.nasabah.model.dto.NasabahResponseDto;
import id.bni.nasabah.model.dto.PaginationResponseDto;
import id.bni.nasabah.model.dto.RegisterNasabahRequestDto;
import id.bni.nasabah.model.dto.UpdateNasabahRequestDto;
import org.springframework.data.domain.Pageable;

public interface NasabahService {

    NasabahResponseDto registerNasabah(RegisterNasabahRequestDto requestDto);
    NasabahResponseDto getNasabahByNik(String nik);
    ApiResponse<PaginationResponseDto<NasabahResponseDto>> getAllNasabah(Pageable pageable);
    NasabahResponseDto updateNasabah(String nik, UpdateNasabahRequestDto requestDto);
    void deleteNasabahByNik(String nik);
}
