package org.example.myprojectmax.dto.selectionDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.myprojectmax.entity.SelectionStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectionUpdateRequestDto {
    private Integer idSelection;
    private String element;
    private SelectionStatus status;
    private String description;
}
