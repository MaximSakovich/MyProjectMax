package org.example.myprojectmax.controller;

import lombok.AllArgsConstructor;
import org.example.myprojectmax.dto.laptopDto.LaptopCreateRequestDto;
import org.example.myprojectmax.dto.laptopDto.LaptopCreateResponseDto;
import org.example.myprojectmax.service.LaptopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/laptop")
public class LaptopController {
    private static final Logger log = LoggerFactory.getLogger(LaptopController.class);
    private final LaptopService laptopService;

    @PostMapping
   public ResponseEntity<LaptopCreateResponseDto> createLaptop(@RequestBody LaptopCreateRequestDto request) {
   return new ResponseEntity<>(laptopService.createLaptop(request), HttpStatus.CREATED);
  }
}
