package com.ringcentral.carsapi.services;

import com.ringcentral.carsapi.dtos.CarV1Dto;
import com.ringcentral.carsapi.dtos.CarV1InfoDto;
import com.ringcentral.carsapi.dtos.CarV2Dto;
import com.ringcentral.carsapi.dtos.CarV2InfoDto;
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

    private static CarV1InfoDto toV1InfoDto(Modification modification) {
        Model model = modification.getGeneration().getModel();
        return new CarV1InfoDto(modification.getId(), model.getSegment().getTitle(),
            model.getBrand().getId(), model.getTitle(), modification.getGeneration().getTitle(), modification.getTitle());
    }

    private static CarV1Dto toV1Dto(Modification modification) {
        Generation generation = modification.getGeneration();
        Model model = generation.getModel();
        return new CarV1Dto(modification.getId(), model.getSegment().getTitle(),
            model.getBrand().getId(), model.getTitle(), modification.getGeneration().getTitle(), modification.getTitle(),
            generation.getYears(), modification.getFuelType(), modification.getEngineType(), modification.getEngineDisplacement(),
            modification.getHp(), modification.getGearboxType(), modification.getWheelDriveType(), generation.getLength(), generation.getWidth(),
            generation.getHeight(), modification.getBodyStyle(), modification.getAcceleration(), modification.getMaxSpeed());
    }

    private static CarV2InfoDto toV2InfoDto(Modification modification) {
        Model model = modification.getGeneration().getModel();
        return new CarV2InfoDto(modification.getId(), model.getSegment().getTitle(),
            model.getBrand().getTitle(), model.getTitle(), modification.getGeneration().getTitle(), modification.getTitle());
    }

    private static CarV2Dto toV2Dto(Modification modification) {
        Generation generation = modification.getGeneration();
        Model model = generation.getModel();
        return new CarV2Dto(modification.getId(), model.getSegment().getTitle(),
            model.getBrand().getTitle(), model.getTitle(), modification.getGeneration().getTitle(), modification.getTitle(),
            generation.getYears(), modification.getFuelType(), modification.getEngineType(), modification.getEngineDisplacement(),
            modification.getHp(), modification.getGearboxType(), modification.getWheelDriveType(), generation.getLength(), generation.getWidth(),
            generation.getHeight(), modification.getBodyStyle(), modification.getAcceleration(), modification.getMaxSpeed());
    }

    public List<CarV1InfoDto> getCarsV1() {
        return StreamSupport.stream(modificationRepository.findAll().spliterator(), false)
            .map(CarService::toV1InfoDto)
            .collect(Collectors.toList());
    }

    public Page<CarV1InfoDto> getCarsV1(Pageable pageable) {
        return modificationRepository.findAll(pageable).map(CarService::toV1InfoDto);
    }

    public Optional<CarV1Dto> getCarV1(Integer carId) {
        return modificationRepository.findById(carId).map(CarService::toV1Dto);
    }

    public List<CarV2InfoDto> getCarsV2() {
        return StreamSupport.stream(modificationRepository.findAll().spliterator(), false)
            .map(CarService::toV2InfoDto)
            .collect(Collectors.toList());
    }

    public Page<CarV2InfoDto> getCarsV2(Pageable pageable) {
        return modificationRepository.findAll(pageable).map(CarService::toV2InfoDto);
    }

    public Optional<CarV2Dto> getCarV2(Integer carId) {
        return modificationRepository.findById(carId).map(CarService::toV2Dto);
    }
}
