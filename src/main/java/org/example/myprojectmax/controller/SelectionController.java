package org.example.myprojectmax.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.example.myprojectmax.dto.selectionDto.SelectionCreateOrUpdateResponseDto;
import org.example.myprojectmax.dto.selectionDto.SelectionCreateRequestDto;
import org.example.myprojectmax.dto.selectionDto.SelectionResponseDto;
import org.example.myprojectmax.entity.SelectionStatus;
import org.example.myprojectmax.service.SelectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/selection")
@AllArgsConstructor
public class SelectionController {

    private final SelectionService selectionService;

    @GetMapping
    @Operation(summary = "Get a list of all tasks")
    @ApiResponse(responseCode = "200", description = "Successfully retried lit",
            content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SelectionResponseDto.class))})
    public ResponseEntity<List<SelectionResponseDto>> findAll(){
        return new ResponseEntity<>(selectionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SelectionResponseDto> findSelectionById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(selectionService.findById(id), HttpStatus.OK);
    }


    @GetMapping(params = "managerName")
    public ResponseEntity<List<SelectionResponseDto>> findSelectionByManagerName(@RequestParam String managerName) {
        return new ResponseEntity<>(selectionService.findSelectionByManagerName(managerName), HttpStatus.OK);
    }


    @PostMapping
    @Operation(summary = "Create a new order", description = "Создание новой задачи из данных, полученных от пользователя")
    @ApiResponse(responseCode = "201", description = "Order created",
            content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SelectionCreateOrUpdateResponseDto.class))
            })
    public ResponseEntity<SelectionCreateOrUpdateResponseDto> createNewSelection(@RequestBody SelectionCreateRequestDto request){
        return new ResponseEntity<>(selectionService.createSelection(request), HttpStatus.CREATED);
    }

    @GetMapping("/byStatus/{status}")
    public List<SelectionResponseDto> getSelectionsByStatus(@PathVariable SelectionStatus status) {
        return selectionService.findSelectionsByStatus(status);
    }

    @GetMapping("/byCreateDate")
    public List<SelectionResponseDto> getSelectionsByCreateDate(@RequestParam LocalDateTime createDate) {
        return selectionService.findSelectionsByCreateDate(createDate);
    }

}
