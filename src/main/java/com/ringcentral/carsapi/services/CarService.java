package com.ringcentral.carsapi.services;

import com.ringcentral.carsapi.dtos.CarV1Dto;
import com.ringcentral.carsapi.dtos.CarV1InfoDto;
import com.ringcentral.carsapi.entities.Generation;
import com.ringcentral.carsapi.entities.Model;
import com.ringcentral.carsapi.entities.Modification;
import com.ringcentral.carsapi.repositories.ModificationRepository;
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
public class CarService {
    private final ModificationRepository modificationRepository;

    private static CarV1InfoDto toInfoDto(Modification modification) {
        Model model = modification.getGeneration().getModel();
        return new CarV1InfoDto(modification.getId(), model.getSegment().getTitle(),
            model.getBrand().getId(), model.getTitle(), modification.getGeneration().getTitle(), modification.getTitle());
    }

    private static CarV1Dto toDto(Modification modification) {
        Generation generation = modification.getGeneration();
        Model model = generation.getModel();
        return new CarV1Dto(modification.getId(), model.getSegment().getTitle(),
            model.getBrand().getId(), model.getTitle(), modification.getGeneration().getTitle(), modification.getTitle(),
            generation.getYears(), modification.getFuelType(), modification.getEngineType(), modification.getEngineDisplacement(),
            modification.getHp(), modification.getGearboxType(), modification.getWheelDriveType(), generation.getLength(), generation.getWidth(),
            generation.getHeight(), modification.getBodyStyle(), modification.getAcceleration(), modification.getMaxSpeed());
    }

    public List<CarV1InfoDto> getCars() {
        return StreamSupport.stream(modificationRepository.findAll().spliterator(), false)
            .map(CarService::toInfoDto)
            .collect(Collectors.toList());
    }

    public Page<CarV1InfoDto> getCars(Pageable pageable) {
        return modificationRepository.findAll(pageable).map(CarService::toInfoDto);
    }

    public Optional<CarV1Dto> getCar(Integer carId) {
        return modificationRepository.findById(carId).map(CarService::toDto);
    }
}
