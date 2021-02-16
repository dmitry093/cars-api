package com.ringcentral.carsapi.repositories;

import com.ringcentral.carsapi.entities.Country;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CountryRepository extends PagingAndSortingRepository<Country, Integer> {
}
