package org.example.myprojectmax.service;

import lombok.RequiredArgsConstructor;
import org.example.myprojectmax.dto.laptopDto.LaptopCreateRequestDto;
import org.example.myprojectmax.dto.laptopDto.LaptopCreateResponseDto;
import org.example.myprojectmax.entity.Laptop;
import org.example.myprojectmax.repository.LaptopRepository;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ComponentScan
public class LaptopService {

    private final LaptopRepository repository;

    public LaptopCreateResponseDto findByBrand(String brandName) {
        Laptop laptop = repository.findByBrand(brandName)
                .orElseThrow(() -> new NotFoundException("Laptop with brand " + brandName + " not found"));
        return toDto(laptop);
    }

    public Laptop findByModel(String modelName) {
        return repository.findByModel(modelName)
                .orElseThrow(() -> new NotFoundException("Laptop with model " + modelName + " not found"));

    }

    public LaptopCreateResponseDto createLaptop(LaptopCreateRequestDto request) {
        if (repository.findByBrand(request.getBrand()).isEmpty()) {
            Laptop newLaptop = new Laptop();
            newLaptop.setBrand(request.getBrand());
            newLaptop.setModel(request.getModel());

            Laptop savedLaptop = repository.save(newLaptop);
            return toDto(savedLaptop);
        } else {
            throw new AlreadyExistException("Laptop with model " + request.getModel() + " already exists!");
        }
    }

    private LaptopCreateResponseDto toDto(Laptop laptop) {
        return new LaptopCreateResponseDto(laptop.getId(), laptop.getBrand(), laptop.getModel());
    }
}