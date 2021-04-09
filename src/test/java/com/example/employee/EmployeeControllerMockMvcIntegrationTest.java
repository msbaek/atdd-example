package com.example.employee;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = EmployeeApplication.class
)
@TestPropertySource(
        locations = "classpath:application-integration.properties"
)
@AutoConfigureMockMvc
public class EmployeeControllerMockMvcIntegrationTest {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        repository.deleteAll();
        repository.save(new Employee("Baek", "Myeongseok"));
    }

    @Test
    public void shouldReturnDefaultMessageWhenLastNameNotFound() throws Exception {
        String nonExistingLastName = "nonExistingLastName";
        String expectedMessage = "Who is this " + nonExistingLastName + " you're talking about?";

        MvcResult mvcResult = mockMvc.perform(get("/api/hello/" + nonExistingLastName))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(expectedMessage);
    }

    @Test
    public void shouldReturnGreetingMessageWhenLastNameFound() throws Exception {
        String existingLastName = "Baek";
        String expectedMessage = "Hello Myeongseok Baek!";

        MvcResult mvcResult = mockMvc.perform(get("/api/hello/" + existingLastName))
                .andDo(print())
                .andExpect(status().isOk())
//                .andExpect(jsonPath("$['testChannel'].channelName").exists())
//                .andExpect(jsonPath("$['testChannel'].enabled").isBoolean());
                .andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(expectedMessage);
    }
}