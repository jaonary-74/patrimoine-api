package com.patrimoine.demo;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.patrimoine.demo.Model.Patrimoine;
import com.patrimoine.demo.Service.PatrimoineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class PatrimoineTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final String BASE_URL = "/patrimoines/";
    @Mock
    private PatrimoineService patrimoineService;

    @BeforeEach
    public void setUp() {
    }

    @Test
    void get_patrimoine_by_id_ok() throws Exception {
        String id = "1";
        mockMvc.perform(get(BASE_URL + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.owner").value("John Doe"));
    }

    @Test
    void get_patrimoine_by_id_not_found() throws Exception {
        String id = "999";
        mockMvc.perform(get(BASE_URL + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_or_update_patrimoine_ok() throws Exception {
        String id = "1";
        Patrimoine existingPatrimoine = new Patrimoine();
        existingPatrimoine.setOwner("John Doe");

        when(patrimoineService.saveOrUpdatePatrimoine(eq(id), (Patrimoine) any(Patrimoine.class)))
                .thenReturn(existingPatrimoine);

        Patrimoine updatedPatrimoine = new Patrimoine();
        updatedPatrimoine.setOwner("Jane Doe");

        mockMvc.perform(put(BASE_URL + id)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updatedPatrimoine)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.owner").value("Jane Doe"));
    }
}