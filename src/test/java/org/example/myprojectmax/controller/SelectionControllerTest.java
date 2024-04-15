package org.example.myprojectmax.controller;

import org.example.myprojectmax.dto.selectionDto.SelectionResponseDto;
import org.example.myprojectmax.service.SelectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(SelectionController.class)
class SelectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SelectionService selectionService;

    @Test
    void testFindAll() throws Exception {
        List<SelectionResponseDto> selectionList = new ArrayList<>();
        // Добавьте несколько объектов SelectionResponseDto в список selectionList

        // Установите поведение заглушки для метода findAll() вашего сервиса
        when(selectionService.findAll()).thenReturn(selectionList);

        mockMvc.perform(get("/api/selection"))
                .andExpect(status().isOk());
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Добавьте дополнительные проверки для ожидаемых значений JSON-ответа
               // .andExpect(jsonPath("$", hasSize(selectionList.size())));
    }

    @Test
    void testFindSelectionById() throws Exception {
        int selectionId = 1;
        SelectionResponseDto selectionDto = new SelectionResponseDto();
        selectionDto.setId(selectionId);
        // Установите поведение заглушки для метода findById() вашего сервиса
        when(selectionService.findById(selectionId)).thenReturn(selectionDto);

        mockMvc.perform(get("/api/selection/{id}", selectionId))
                .andExpect(status().isOk());
                // .andExpect(content().contentType(MediaType.APPLICATION_JSON))
               // .andExpect(jsonPath("$.id", is(selectionId)));
        // Другие ожидаемые значения JSON-ответа
    }

    @Test
    void testFindSelectionByManagerName() throws Exception {
        String managerName = "John Doe";
        List<SelectionResponseDto> selections = new ArrayList<>();
        // Заполните список selections ожидаемыми данными

        // Установите поведение заглушки для метода findSelectionByManagerName() вашего сервиса
        when(selectionService.findSelectionByManagerName(managerName)).thenReturn(selections);

        mockMvc.perform(get("/api/selection").param("managerName", managerName))
                .andExpect(status().isOk());
               // .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // Добавьте проверки для ожидаемых значений JSON-ответа
              //  .andExpect(jsonPath("$", hasSize(selections.size())))
    }
}
