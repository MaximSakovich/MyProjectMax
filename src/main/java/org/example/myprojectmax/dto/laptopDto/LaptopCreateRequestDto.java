package org.example.myprojectmax.dto.laptopDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaptopCreateRequestDto {
    private Integer id;
    private String brand;
    private String model;
}
