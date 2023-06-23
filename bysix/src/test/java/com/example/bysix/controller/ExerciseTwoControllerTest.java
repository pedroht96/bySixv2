package com.example.bysix.controller;

import com.example.bysix.service.ExerciseTwoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExerciseTwoController.class)
public class ExerciseTwoControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    ExerciseTwoService exerciseTwoService;

    private JacksonTester<List<Map<String, Object>>> json;

    private List<Map<String, Object>> data;

    @Before
    public void setUp() throws IOException {
        Mockito.reset(exerciseTwoService);
        JacksonTester.initFields(this, new ObjectMapper());
        this.data = json.read(new ClassPathResource("countries.json").getInputStream()).getObject();
    }

    @Test
    public void shouldReadNumberOfCountries() throws Exception {

        when(exerciseTwoService.getCountriesCount()).thenReturn(data.size());

        mockMvc.perform(get("/exerciseTwo/countries/count").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

}
