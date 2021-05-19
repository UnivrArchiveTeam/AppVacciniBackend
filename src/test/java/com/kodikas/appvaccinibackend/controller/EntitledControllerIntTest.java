package com.kodikas.appvaccinibackend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodikas.appvaccinibackend.model.Entitled;
import com.kodikas.appvaccinibackend.service.EntitledService;
import com.kodikas.appvaccinibackend.wrapper.EntitledWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = EntitledController.class)
class EntitledControllerIntTest {
    private final static String URI = "/entitled";

    private Entitled entitled1;
    private Entitled entitled2;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EntitledService entitledService;

    @BeforeEach
    void setUp() {
        entitled1 = new Entitled("over80");
        entitled2 = new Entitled("over50");
    }

    @Test
    void getAllEntitled_shouldReturnEntitledWrapper() throws Exception {
        // given
        List<Entitled> entitles = List.of(entitled1, entitled2);
        EntitledWrapper expected = new EntitledWrapper(entitles);

        // when
        when(entitledService.getAllEntitled()).thenReturn(entitles);

        // then
        MvcResult result = mockMvc.perform(get(URI))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expected));
    }

    @Test
    void addEntitled_shouldCallService() throws Exception {
        // when
        when(entitledService.addEntitled(any())).thenReturn(entitled1);

        // then
        MvcResult result = mockMvc.perform(post(URI)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(entitled1)))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(entitled1));
    }

    @Test
    void getAllEntitledByCategory_shouldReturnCorrectEntitled() throws Exception {
        // given
        String category = "over80";
        List<Entitled> entitleds = List.of(entitled1);

        // when
        when(entitledService.getEntitledByCategory(category)).thenReturn(entitleds);

        // then
        MvcResult result = mockMvc.perform(get(URI + "/" + category))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        EntitledWrapper entitledWrapper = objectMapper.readValue(resultString, EntitledWrapper.class);
        assertThat(entitledWrapper.getEntitles().size()).isEqualTo(1);
        entitledWrapper.getEntitles().forEach(
                entitled -> assertThat(entitled.getCategory()).isEqualTo(category)
        );
    }
}