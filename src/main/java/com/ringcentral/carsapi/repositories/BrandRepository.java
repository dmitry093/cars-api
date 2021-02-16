package com.ringcentral.carsapi.repositories;

import com.ringcentral.carsapi.entities.Brand;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BrandRepository extends PagingAndSortingRepository<Brand, Integer> {
}
