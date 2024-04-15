package org.example.myprojectmax.service;

import org.example.myprojectmax.dto.selectionDto.SelectionCreateOrUpdateResponseDto;
import org.example.myprojectmax.dto.selectionDto.SelectionCreateRequestDto;
import org.example.myprojectmax.dto.selectionDto.SelectionResponseDto;
import org.example.myprojectmax.dto.selectionDto.SelectionUpdateRequestDto;
import org.example.myprojectmax.entity.Selection;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan
public class SelectionConverter {
    public SelectionCreateOrUpdateResponseDto toCreateDto(Selection selection) {
        SelectionCreateOrUpdateResponseDto dto = new SelectionCreateOrUpdateResponseDto();
        dto.setId(selection.getId());
        dto.setElement(selection.getElement());
        dto.setDescription(selection.getDescription());
        dto.setCreateDate(selection.getCreateDate());
        dto.setLastUpdate(selection.getLastUpdate());
        dto.setStatus(selection.getStatus());
        return dto;
    }

    public SelectionResponseDto toDto(Selection selection) {
        SelectionResponseDto dto = new SelectionResponseDto();
        dto.setIdLaptop(selection.getId());
        dto.setManagerName(selection.getElement());
        dto.setDescription(selection.getDescription());
        dto.setCreateDate(selection.getCreateDate());
        dto.setLastUpdate(selection.getLastUpdate());
        dto.setStatus(selection.getStatus());

        return dto;
    }

    public Selection fromCreateRequest(SelectionCreateRequestDto dto) {
        Selection selection = new Selection();
        if (dto.getManagerName() != null) {
            selection.setElement(dto.getManagerName());
        }
        if (dto.getDescription() != null) {
            selection.setDescription(dto.getDescription());
        }
        return selection;
    }

    public Selection fromUpdateRequest(SelectionUpdateRequestDto dto) {
        Selection selection = new Selection();
        if (dto.getIdSelection() != null) {
            selection.setId(dto.getIdSelection());
        }

        if (dto.getElement() != null) {
            selection.setElement(dto.getElement());
        }
        if (dto.getDescription() != null) {
            selection.setDescription(dto.getDescription());
        }
        if (dto.getStatus() != null) {
            selection.setStatus(dto.getStatus());
        }
        return selection;
    }

}
