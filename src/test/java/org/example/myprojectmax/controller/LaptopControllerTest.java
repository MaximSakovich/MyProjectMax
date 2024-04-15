package org.example.myprojectmax.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.myprojectmax.dto.laptopDto.LaptopCreateRequestDto;
import org.example.myprojectmax.dto.laptopDto.LaptopCreateResponseDto;
import org.example.myprojectmax.service.LaptopService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class LaptopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LaptopService laptopService;

    @Test
    void createLaptop_ReturnsCreated() throws Exception {
        // Given
        LaptopCreateRequestDto requestDto = new LaptopCreateRequestDto();
        requestDto.setBrand("TestBrand");
        requestDto.setModel("TestModel");

        LaptopCreateResponseDto responseDto = new LaptopCreateResponseDto();
        responseDto.setId(1);
        responseDto.setBrand("TestBrand");
        responseDto.setModel("TestModel");

        when(laptopService.createLaptop(any(LaptopCreateRequestDto.class))).thenReturn(responseDto);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.post("/api/laptop")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.brand").value("TestBrand"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("TestModel"));
    }

    // Helper method to convert objects to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
