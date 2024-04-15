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
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

        // Создаем фиктивный объект Laptop
        Laptop laptop = new Laptop();
        laptop.setId(123); // Устанавливаем идентификатор ноутбука
        laptop.setBrand("TestBrand"); // Устанавливаем бренд ноутбука
        laptop.setModel("TestModel"); // Устанавливаем модель ноутбука

        Selection selection = new Selection();
        selection.setId(id);
        selection.setLaptop(laptop); // Устанавливаем объект Laptop в Selection

        when(selectionRepository.findById(id)).thenReturn(Optional.of(selection));

        // Act
        SelectionResponseDto result = selectionService.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());

        // Проверяем информацию о ноутбуке через объект SelectionResponseDto
        assertNotNull(result.getIdLaptop());
        assertEquals(laptop.getId(), result.getIdLaptop()); // Проверка идентификатора ноутбука
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
        SelectionStatus status = SelectionStatus.OPEN;

        Laptop laptop1 = new Laptop();
        laptop1.setId(1);
        laptop1.setBrand("Brand1");
        laptop1.setModel("Model1");
        Laptop laptop2 = new Laptop();
        laptop2.setId(2);
        laptop2.setBrand("Brand2");
        laptop2.setModel("Model2");

        Selection selection1 = new Selection();
        selection1.setLaptop(laptop1);
        Selection selection2 = new Selection();
        selection2.setLaptop(laptop2);
        List<Selection> selections = Arrays.asList(selection1, selection2);
        when(selectionRepository.findAllByStatus(status)).thenReturn(selections);
        when(converter.toDto(any())).thenReturn(new SelectionResponseDto());

        List<SelectionResponseDto> result = selectionService.findSelectionsByStatus(status);

        assertEquals(2, result.size());
        verify(converter, times(2)).toDto(any());
    }

    @Test
    void testFindSelectionsByCreateDate() {
        LocalDateTime createDate = LocalDateTime.now();

        Laptop laptop1 = new Laptop();
        laptop1.setId(1);
        laptop1.setBrand("Brand1");
        laptop1.setModel("Model1");

        Laptop laptop2 = new Laptop();
        laptop2.setId(2);
        laptop2.setBrand("Brand2");
        laptop2.setModel("Model2");

        Selection selection1 = new Selection();
        selection1.setLaptop(laptop1);
        Selection selection2 = new Selection();
        selection2.setLaptop(laptop2);
        List<Selection> selections = Arrays.asList(selection1, selection2);
        when(selectionRepository.findAllByCreateDate(createDate)).thenReturn(selections);
        when(converter.toDto(any())).thenReturn(new SelectionResponseDto());

        List<SelectionResponseDto> result = selectionService.findSelectionsByCreateDate(createDate);

        assertEquals(2, result.size());
        verify(converter, times(2)).toDto(any());
    }
}

