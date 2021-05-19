package com.kodikas.appvaccinibackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodikas.appvaccinibackend.model.Entitled;
import com.kodikas.appvaccinibackend.repository.EntitledRepository;
import com.kodikas.appvaccinibackend.service.EntitledService;
import com.kodikas.appvaccinibackend.wrapper.EntitledWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class EntitledControllerAcceptanceTest {
    private final static String URI = "/entitled";

    private Entitled entitled1;
    private Entitled entitled2;

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private EntitledRepository entitledRepository;

    @BeforeEach
    void setUp() {
        entitledRepository.deleteAll();
        entitled1 = new Entitled("over80");
        entitled2 = new Entitled("over50");
    }

    @Test
    void getAllEntitled() throws Exception {
        // given
        List<Entitled> entitles = List.of(entitled1, entitled2);
        EntitledWrapper expected = new EntitledWrapper(entitles);
        entitledRepository.saveAll(entitles);

        // then
        MvcResult result = mockMvc.perform(get(URI))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(expected));
    }

    @Test
    void addEntitled() throws Exception{
        // then
        MvcResult result = mockMvc.perform(post(URI)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(entitled1)))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        Entitled entitled = objectMapper.readValue(resultString, Entitled.class);
        Optional<Entitled> optionalEntitled = entitledRepository.findById(entitled.getEntitledId());

        assertThat(optionalEntitled).isPresent();
    }

    @Test
    void getAllEntitledByCategory() throws Exception {
        // given
        String category = "over80";

        // then
        entitledRepository.save(entitled1);

        MvcResult result = mockMvc.perform(get(URI + "/" + category))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        EntitledWrapper entitledWrapper = objectMapper.readValue(resultString, EntitledWrapper.class);

        entitledWrapper.getEntitles().forEach(
                entitled -> assertThat(entitled.getCategory()).isEqualTo(category)
        );
    }
}