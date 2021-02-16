package com.ringcentral.carsapi.repositories;

import com.ringcentral.carsapi.entities.Modification;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ModificationRepository extends PagingAndSortingRepository<Modification, Integer> {
}
