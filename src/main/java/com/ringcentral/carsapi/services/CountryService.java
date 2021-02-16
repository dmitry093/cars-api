package com.ringcentral.carsapi.services;

import com.ringcentral.carsapi.dtos.CountryDto;
import com.ringcentral.carsapi.entities.Country;
import com.ringcentral.carsapi.repositories.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;

    private static CountryDto toDto(Country country){
        return new CountryDto(country.getId(), country.getTitle());
    }

    public Optional<CountryDto> getCountry(Integer id) {
        return countryRepository.findById(id).map(CountryService::toDto);
    }

    public Page<CountryDto> getCountries(Pageable pageable) {
        return countryRepository.findAll(pageable).map(CountryService::toDto);
    }
}
