package id.bni.nasabah.controller;

import id.bni.nasabah.constant.ResponseCode;
import id.bni.nasabah.dto.RegisterNasabahRequestDto;
import id.bni.nasabah.dto.NasabahResponseDto;
import id.bni.nasabah.dto.UpdateNasabahRequestDto;
import id.bni.nasabah.model.ApiResponse;
import id.bni.nasabah.service.NasabahService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nasabah")
@Slf4j
public class NasabahController {

    private final NasabahService nasabahService;

    public NasabahController(NasabahService nasabahService) {
        this.nasabahService = nasabahService;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<NasabahResponseDto>> registerNasabah(
            @Valid @RequestBody RegisterNasabahRequestDto request
    ) {
        NasabahResponseDto result = nasabahService.registerNasabah(request);

        ApiResponse<NasabahResponseDto> response = ApiResponse.success(ResponseCode.SUCCESS, result);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{nik}")
    public ResponseEntity<ApiResponse<NasabahResponseDto>> getNasabah(@PathVariable String nik) {
        NasabahResponseDto result = nasabahService.getNasabahByNik(nik);

        ApiResponse<NasabahResponseDto> response = ApiResponse.success(ResponseCode.SUCCESS, result);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<NasabahResponseDto>>> getAllNasabah(Pageable pageable) {
        Page<NasabahResponseDto> result = nasabahService.getAllNasabah(pageable);

        ApiResponse<Page<NasabahResponseDto>> response = ApiResponse.success(ResponseCode.SUCCESS, result);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{nik}")
    public ResponseEntity<ApiResponse<NasabahResponseDto>> updateNasabah(
            @PathVariable String nik,
            @Valid @RequestBody UpdateNasabahRequestDto request
    ) {
        NasabahResponseDto result = nasabahService.updateNasabah(nik, request);

        ApiResponse<NasabahResponseDto> response = ApiResponse.success(ResponseCode.SUCCESS, result);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{nik}")
    public ResponseEntity<ApiResponse<NasabahResponseDto>> deleteNasabah(
            @PathVariable String nik
    ) {
        nasabahService.deleteNasabahByNik(nik);
        ApiResponse<NasabahResponseDto> response = ApiResponse.success(ResponseCode.SUCCESS, null);

        return ResponseEntity.ok(response);
    }
}
