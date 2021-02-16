package com.ringcentral.carsapi.controllers;

import com.ringcentral.carsapi.dtos.BrandDto;
import com.ringcentral.carsapi.services.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<Page<BrandDto>> getBrands(@PageableDefault(sort = { "country" }, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(brandService.getBrands(pageable));
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<BrandDto> getBrand(@PathVariable Integer brandId) {
        return brandService.getBrand(brandId)
            .map(ResponseEntity::ok).orElseThrow(() -> {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Brand not found");
            });
    }
}
