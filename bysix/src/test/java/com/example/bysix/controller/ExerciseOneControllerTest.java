package com.example.bysix.controller;


import com.example.bysix.service.ExerciseOneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExerciseOneController.class)
public class ExerciseOneControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    ExerciseOneService exerciseOneService;

    @Before
    public void setUp() {
        Mockito.reset(exerciseOneService);
    }

    @Test
    public void shouldProcessNumbers() throws Exception {

        List<String> numbers = Arrays.asList("1", "2", "Visual", "4", "Nuts", "Visual", "7", "8", "Visual", "Nuts",
                "11", "Visual", "13", "14", "Visual Nuts");

        when(exerciseOneService.processNumbers(15)).thenReturn(numbers);

        mockMvc.perform(get("/exerciseone?limit=15").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("length()").value(numbers.size()))
                .andExpect(jsonPath("$[0]", is("1")))
                .andExpect(jsonPath("$[1]", is("2")))
                .andExpect(jsonPath("$[2]", is("Visual")))
                .andExpect(jsonPath("$[3]", is("4")))
                .andExpect(jsonPath("$[4]", is("Nuts")))
                .andExpect(jsonPath("$[5]", is("Visual")))
                .andExpect(jsonPath("$[6]", is("7")))
                .andExpect(jsonPath("$[7]", is("8")))
                .andExpect(jsonPath("$[8]", is("Visual")))
                .andExpect(jsonPath("$[9]", is("Nuts")))
                .andExpect(jsonPath("$[10]", is("11")))
                .andExpect(jsonPath("$[11]", is("Visual")))
                .andExpect(jsonPath("$[12]", is("13")))
                .andExpect(jsonPath("$[13]", is("14")))
                .andExpect(jsonPath("$[14]", is("Visual Nuts")));
    }

    @Test
    public void shouldNotProcessNumbers() throws Exception {
        when(exerciseOneService.processNumbers(15)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/exerciseone?limit=15").accept(APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
