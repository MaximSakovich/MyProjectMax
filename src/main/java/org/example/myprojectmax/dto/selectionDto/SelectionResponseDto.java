package org.example.myprojectmax.dto.selectionDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.myprojectmax.entity.SelectionStatus;

import javax.swing.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectionResponseDto {

    private Integer idLaptop;
    private String description;
    private String managerName;
    private Integer id;
    private String element;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;
    private SelectionStatus status;

}
