package com.ringcentral.carsapi.controllers;

import com.ringcentral.carsapi.dtos.CarV2Dto;
import com.ringcentral.carsapi.dtos.CarV2InfoDto;
import com.ringcentral.carsapi.services.CarService;
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

import java.util.List;

@RestController
@RequestMapping("/api/v2/cars")
@RequiredArgsConstructor
public class CarV2Controller {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarV2InfoDto>> getCars() {
        return ResponseEntity.ok(carService.getCarsV2());
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<CarV2InfoDto>> getCarsPaged(@PageableDefault(sort = { "title" }, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(carService.getCarsV2(pageable));
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarV2Dto> getCar(@PathVariable Integer carId) {
        return carService.getCarV2(carId)
            .map(ResponseEntity::ok).orElseThrow(() -> {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found");
            });
    }
}
