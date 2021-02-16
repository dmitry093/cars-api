package com.ringcentral.carsapi.services;

import com.ringcentral.carsapi.dtos.CountryDto;
import com.ringcentral.carsapi.entities.Brand;
import com.ringcentral.carsapi.entities.Country;
import com.ringcentral.carsapi.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    private static CountryDto toDto(Country country){
        return new CountryDto(country.getId(), country.getTitle(), country.getBrands().stream().map(Brand::getTitle).collect(Collectors.toList()));
    }

    public Optional<CountryDto> getCountry(Integer id) {
        return countryRepository.findById(id).map(CountryService::toDto);
    }

    public Page<CountryDto> getCountries(Pageable pageable) {
        return countryRepository.findAll(pageable).map(CountryService::toDto);
    }

    public List<CountryDto> getCountries() {
        return StreamSupport.stream(countryRepository.findAll().spliterator(), false)
            .map(CountryService::toDto)
            .collect(Collectors.toList());
    }
}
