package com.ringcentral.carsapi.controllers;

import com.ringcentral.carsapi.dtos.CountryDto;
import com.ringcentral.carsapi.services.CountryService;
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
@RequestMapping("/api/v1/country")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<Page<CountryDto>> getCountries(@PageableDefault(sort = { "title" }, direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.ok(countryService.getCountries(pageable));
    }

    @GetMapping("/{countryId}")
    public ResponseEntity<CountryDto> getCountry(@PathVariable Integer countryId) {
        return countryService.getCountry(countryId)
            .map(ResponseEntity::ok).orElseThrow(() -> {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country not found");
            });
    }
}
