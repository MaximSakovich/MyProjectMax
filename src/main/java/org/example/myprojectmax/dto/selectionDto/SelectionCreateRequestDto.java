package org.example.myprojectmax.dto.selectionDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectionCreateRequestDto {
    private Integer idLaptop;
    private String description;
    private String managerName;
}
