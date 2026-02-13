package id.bni.nasabah.controller;

import id.bni.nasabah.constant.ResponseCode;
import id.bni.nasabah.model.ApiResponse;
import id.bni.nasabah.model.dto.NasabahResponseDto;
import id.bni.nasabah.model.dto.PaginationResponseDto;
import id.bni.nasabah.model.dto.RegisterNasabahRequestDto;
import id.bni.nasabah.model.dto.UpdateNasabahRequestDto;
import id.bni.nasabah.service.NasabahService;
import id.bni.nasabah.util.UtilityJava;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/nasabah")
@Slf4j
public class NasabahController {

    private final NasabahService nasabahService;
    private final UtilityJava utility;


    @PostMapping("/register")
    public ResponseEntity<ApiResponse<NasabahResponseDto>> registerNasabah(
            @Valid @RequestBody RegisterNasabahRequestDto request
    ) {
        log.info("Register request received for NIK: {}", utility.maskNik(request.getNik()));

        NasabahResponseDto result = nasabahService.registerNasabah(request);
        log.info("Nasabah register successful");

        ApiResponse<NasabahResponseDto> response = ApiResponse.success(ResponseCode.SUCCESS, result);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{nik}")
    public ResponseEntity<ApiResponse<NasabahResponseDto>> getNasabah(@PathVariable String nik) {
        log.info("Get Nasabah for NIK: {}", utility.maskNik(nik));
        NasabahResponseDto result = nasabahService.getNasabahByNik(nik);

        ApiResponse<NasabahResponseDto> response = ApiResponse.success(ResponseCode.SUCCESS, result);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<PaginationResponseDto<NasabahResponseDto>>> getAllNasabah(Pageable pageable) {

        log.info("GetAllNasabah request - page = {} size = {}", pageable.getPageNumber(), pageable.getPageSize());
        ApiResponse<PaginationResponseDto<NasabahResponseDto>> response = nasabahService.getAllNasabah(pageable);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{nik}")
    public ResponseEntity<ApiResponse<NasabahResponseDto>> updateNasabah(
            @PathVariable String nik,
            @Valid @RequestBody UpdateNasabahRequestDto request
    ) {
        log.info("Update request received for NIK: {}", utility.maskNik(nik));
        NasabahResponseDto result = nasabahService.updateNasabah(nik, request);

        ApiResponse<NasabahResponseDto> response = ApiResponse.success(ResponseCode.SUCCESS, result);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{nik}")
    public ResponseEntity<ApiResponse<NasabahResponseDto>> deleteNasabah(
            @PathVariable String nik
    ) {
        log.info("Delete request received for NIK: {}", utility.maskNik(nik));
        nasabahService.deleteNasabahByNik(nik);
        ApiResponse<NasabahResponseDto> response = ApiResponse.success(ResponseCode.SUCCESS, null);

        return ResponseEntity.ok(response);
    }
}
