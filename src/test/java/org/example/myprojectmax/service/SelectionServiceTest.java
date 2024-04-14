package org.example.myprojectmax.service;

import org.example.myprojectmax.dto.selectionDto.SelectionCreateOrUpdateResponseDto;
import org.example.myprojectmax.dto.selectionDto.SelectionCreateRequestDto;
import org.example.myprojectmax.dto.selectionDto.SelectionResponseDto;
import org.example.myprojectmax.entity.Laptop;
import org.example.myprojectmax.entity.Selection;
import org.example.myprojectmax.entity.SelectionStatus;
import org.example.myprojectmax.repository.SelectionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SelectionServiceTest {

        @Mock
        private SelectionRepository selectionRepository;

        @Mock
        private LaptopService laptopService;

        @Mock
        private SelectionConverter converter;

        @InjectMocks
        private SelectionService selectionService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void testFindAll() {
            // Arrange
            Selection selection1 = new Selection();
            Selection selection2 = new Selection();
            List<Selection> selections = Arrays.asList(selection1, selection2);

            when(selectionRepository.findAll()).thenReturn(selections);

            // Act
            List<SelectionResponseDto> result = selectionService.findAll();

            // Assert
            assertEquals(2, result.size());
            verify(converter, times(2)).toDto(any());
        }

        @Test
        void testFindById() {
            // Arrange
            int id = 1;
            Selection selection = new Selection();
            selection.setId(id);
            when(selectionRepository.findById(id)).thenReturn(Optional.of(selection));

            // Act
            SelectionResponseDto result = selectionService.findById(id);

            // Assert
            assertEquals(id, result.getId());
            verify(converter).toDto(selection);
        }



    @Test
    void testFindAllOK() {
        // Arrange
        Selection selection1 = new Selection();
        Selection selection2 = new Selection();
        List<Selection> selections = Arrays.asList(selection1, selection2);

        when(selectionRepository.findAll()).thenReturn(selections);
        when(converter.toDto(selection1)).thenReturn(new SelectionResponseDto());
        when(converter.toDto(selection2)).thenReturn(new SelectionResponseDto());

        // Act
        List<SelectionResponseDto> result = selectionService.findAll();

        // Assert
        assertEquals(2, result.size());
        verify(converter, times(2)).toDto(any());
    }

    @Test
    void testFindByIdOk() {
        // Arrange
        int id = 1;
        Selection selection = new Selection();
        selection.setId(id);
        when(selectionRepository.findById(id)).thenReturn(Optional.of(selection));
        when(converter.toDto(selection)).thenReturn(new SelectionResponseDto());

        // Act
        SelectionResponseDto result = selectionService.findById(id);

        // Assert
        assertEquals(id, result.getId());
        verify(converter).toDto(selection);
    }

    @Test
    void testFindSelectionByManagerName() {
        // Arrange
        String managerName = "Manager";
        List<Selection> selections = Arrays.asList(new Selection(), new Selection());
        when(laptopService.findByModel(managerName)).thenReturn(new Laptop());
        when(selectionRepository.findAll()).thenReturn(selections);
        when(converter.toDto(any())).thenReturn(new SelectionResponseDto());

        // Act
        List<SelectionResponseDto> result = selectionService.findSelectionByManagerName(managerName);

        // Assert
        assertEquals(2, result.size());
        verify(converter, times(2)).toDto(any());
    }

    @Test
    void testCreateSelection() {
        // Arrange
        SelectionCreateRequestDto requestDto = new SelectionCreateRequestDto();
        requestDto.setManagerName("Manager");
        when(laptopService.findByModel(requestDto.getManagerName())).thenReturn(new Laptop());
        when(converter.fromCreateRequest(requestDto)).thenReturn(new Selection());
        when(converter.toCreateDto(any())).thenReturn(new SelectionCreateOrUpdateResponseDto());

        // Act
        SelectionCreateOrUpdateResponseDto result = selectionService.createSelection(requestDto);

        // Assert
        verify(selectionRepository).save(any());
    }

    @Test
    void testFindSelectionsByStatus() {
        // Arrange
        SelectionStatus status = SelectionStatus.OPEN;
        List<Selection> selections = Arrays.asList(new Selection(), new Selection());
        when(selectionRepository.findAllByStatus(status)).thenReturn(selections);
        when(converter.toDto(any())).thenReturn(new SelectionResponseDto());

        // Act
        List<SelectionResponseDto> result = selectionService.findSelectionsByStatus(status);

        // Assert
        assertEquals(2, result.size());
        verify(converter, times(2)).toDto(any());
    }

    @Test
    void testFindSelectionsByCreateDate() {
        // Arrange
        LocalDateTime createDate = LocalDateTime.now();
        List<Selection> selections = Arrays.asList(new Selection(), new Selection());
        when(selectionRepository.findAllByCreateDate(createDate)).thenReturn(selections);
        when(converter.toDto(any())).thenReturn(new SelectionResponseDto());

        // Act
        List<SelectionResponseDto> result = selectionService.findSelectionsByCreateDate(createDate);

        // Assert
        assertEquals(2, result.size());
        verify(converter, times(2)).toDto(any());
    }
}

