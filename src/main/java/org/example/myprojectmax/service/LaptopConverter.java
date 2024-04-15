package org.example.myprojectmax.service;

import org.example.myprojectmax.dto.laptopDto.LaptopCreateRequestDto;
import org.example.myprojectmax.dto.laptopDto.LaptopCreateResponseDto;
import org.example.myprojectmax.entity.Laptop;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan
public class LaptopConverter {
    private final LaptopService laptopService;
    public LaptopConverter(LaptopService laptopService) {
         this.laptopService = laptopService;
    }

  public LaptopCreateResponseDto toCreateDto(Laptop laptop) {
       return new LaptopCreateResponseDto(laptop.getId(), laptop.getBrand(), laptop.getModel());
   }

    public LaptopCreateResponseDto toDto (Laptop laptop) {
        return new LaptopCreateResponseDto (laptop.getId(), laptop.getBrand(), laptop.getModel());
    }
}
