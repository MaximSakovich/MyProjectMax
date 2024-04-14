package org.example.myprojectmax.service;

import org.example.myprojectmax.dto.laptopDto.LaptopCreateRequestDto;
import org.example.myprojectmax.dto.laptopDto.LaptopCreateResponseDto;
import org.example.myprojectmax.entity.Laptop;
import org.example.myprojectmax.repository.LaptopRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class LaptopServiceTest {

    @Mock
    private LaptopRepository repository;

    @InjectMocks
    private LaptopService laptopService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByBrand() {
        // Arrange
        String brandName = "Brand";
        Laptop laptop = new Laptop();
        laptop.setId(1);
        laptop.setBrand(brandName);
        when(repository.findByBrand(brandName)).thenReturn(Optional.of(laptop));

        // Act
        LaptopCreateResponseDto result = laptopService.findByBrand(brandName);

        // Assert
        assertEquals(laptop.getId(), result.getId());
        assertEquals(laptop.getBrand(), result.getBrand());
        verify(repository).findByBrand(brandName);
    }

    @Test
    void testFindByBrand_NotFound() {
        // Arrange
        String brandName = "Brand";
        when(repository.findByBrand(brandName)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundException.class, () -> laptopService.findByBrand(brandName));
        verify(repository).findByBrand(brandName);
    }

    @Test
    void testFindByModel() {
        // Arrange
        String modelName = "Model";
        Laptop laptop = new Laptop();
        laptop.setId(1);
        laptop.setModel(modelName);
        when(repository.findByModel(modelName)).thenReturn(Optional.of(laptop));

        // Act
        Laptop result = laptopService.findByModel(modelName);

        // Assert
        assertEquals(laptop.getId(), result.getId());
        assertEquals(laptop.getModel(), result.getModel());
        verify(repository).findByModel(modelName);
    }

    @Test
    void testFindByModel_NotFound() {
        // Arrange
        String modelName = "Model";
        when(repository.findByModel(modelName)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(NotFoundException.class, () -> laptopService.findByModel(modelName));
        verify(repository).findByModel(modelName);
    }

    @Test
    void testCreateLaptop() {
        // Arrange
        String brandName = "Brand";
        String modelName = "Model";
        LaptopCreateRequestDto request = new LaptopCreateRequestDto();
        request.setBrand(brandName);
        request.setModel(modelName);
        when(repository.findByBrand(brandName)).thenReturn(Optional.empty());
        Laptop savedLaptop = new Laptop();
        savedLaptop.setId(1);
        savedLaptop.setBrand(brandName);
        savedLaptop.setModel(modelName);
        when(repository.save(any())).thenReturn(savedLaptop);

        // Act
        LaptopCreateResponseDto result = laptopService.createLaptop(request);

        // Assert
        assertEquals(savedLaptop.getId(), result.getId());
        assertEquals(savedLaptop.getBrand(), result.getBrand());
        assertEquals(savedLaptop.getModel(), result.getModel());
        verify(repository).findByBrand(brandName);
        verify(repository).save(any());
    }

    @Test
    void testCreateLaptop_AlreadyExists() {
        // Arrange
        String brandName = "Brand";
        String modelName = "Model";
        LaptopCreateRequestDto request = new LaptopCreateRequestDto();
        request.setBrand(brandName);
        request.setModel(modelName);
        when(repository.findByBrand(brandName)).thenReturn(Optional.of(new Laptop()));

        // Act and Assert
        assertThrows(AlreadyExistException.class, () -> laptopService.createLaptop(request));
        verify(repository).findByBrand(brandName);
        verify(repository, never()).save(any());
    }
}
