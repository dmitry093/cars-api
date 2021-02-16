package com.ringcentral.carsapi.services;

import com.ringcentral.carsapi.dtos.BrandDto;
import com.ringcentral.carsapi.entities.Brand;
import com.ringcentral.carsapi.repositories.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;

    private static BrandDto toDto(Brand brand) {
        return new BrandDto(brand.getId(), brand.getTitle(), brand.getCountry().getTitle());
    }

    public Optional<BrandDto> getBrand(Integer id) {
        return brandRepository.findById(id).map(BrandService::toDto);
    }

    public Page<BrandDto> getBrands(Pageable pageable) {
        return brandRepository.findAll(pageable).map(BrandService::toDto);
    }
}
