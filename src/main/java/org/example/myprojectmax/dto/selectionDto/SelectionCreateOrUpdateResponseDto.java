package org.example.myprojectmax.dto.selectionDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.myprojectmax.dto.laptopDto.LaptopCreateResponseDto;
import org.example.myprojectmax.entity.SelectionStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectionCreateOrUpdateResponseDto {

    private Integer id;
    private Integer idLaptop;
    private String element;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;
    private SelectionStatus status;
    private String description;
    private LaptopCreateResponseDto laptopCreateResponseDto;

}
