package org.example.myprojectmax.service;

import lombok.AllArgsConstructor;
import org.example.myprojectmax.dto.selectionDto.SelectionCreateOrUpdateResponseDto;
import org.example.myprojectmax.dto.selectionDto.SelectionCreateRequestDto;
import org.example.myprojectmax.dto.selectionDto.SelectionResponseDto;
import org.example.myprojectmax.entity.Laptop;
import org.example.myprojectmax.entity.Selection;
import org.example.myprojectmax.entity.SelectionStatus;
import org.example.myprojectmax.repository.SelectionRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SelectionService {
    private final SelectionRepository selectionRepository;
    private final LaptopService laptopService;
    private final SelectionConverter converter;

    public List<SelectionResponseDto> findAll() {
        return selectionRepository.findAll().stream()
                .map(converter::toDto)
                .toList();
    }

    public SelectionResponseDto findById(Integer id) {
        Selection selection = selectionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task with id = " + id + " not found"));
        return converter.toDto(selection);
    }

    // получить список задач по имени менеджера
    public List<SelectionResponseDto> findSelectionByManagerName(String managerName) {
        Laptop laptop = laptopService.findByModel(managerName);
        return selectionRepository.findAll().stream()
                .map(converter::toDto)
                .toList();

    }

    public SelectionCreateOrUpdateResponseDto createSelection(SelectionCreateRequestDto request) {

        Laptop laptop = laptopService.findByModel(request.getManagerName());
        Selection newSelection = converter.fromCreateRequest(request);
        newSelection.setLaptop(laptop);
        newSelection.setCreateDate(LocalDateTime.now());
        newSelection.setLastUpdate(LocalDateTime.now());
        newSelection.setStatus(SelectionStatus.OPEN);
        return converter.toCreateDto(selectionRepository.save(newSelection));

    }

    public List<SelectionResponseDto> findSelectionsByStatus(SelectionStatus status) {
        List<Selection> selections = selectionRepository.findAllByStatus(status);
        return selections.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public List<SelectionResponseDto> findSelectionsByCreateDate(LocalDateTime createDate) {
        List<Selection> selections = selectionRepository.findAllByCreateDate(createDate);
        return selections.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    private SelectionResponseDto mapToResponseDto(Selection selection) {
        Laptop laptop = selection.getLaptop();
        return new SelectionResponseDto(
                selection.getId(),
                laptop.getBrand(),
                laptop.getModel(),
                laptop.getId(),
                selection.getElement(),
                selection.getCreateDate(),
                selection.getLastUpdate(),
                selection.getStatus()
        );
    }
}