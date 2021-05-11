package com.kodikas.appvaccinibackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodikas.appvaccinibackend.model.CampagnaVaccinale;
import com.kodikas.appvaccinibackend.model.Vaccino;
import com.kodikas.appvaccinibackend.service.CampagnaVaccinaleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CampagnaVaccinaleController.class)
class CampagnaVaccinaleControllerIntTest {
    private final static String URI = "/campagnevaccinali";

    CampagnaVaccinale campagnaVaccinale;
    CampagnaVaccinale expectedCampagnaVaccinale;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CampagnaVaccinaleService campagnaVaccinaleService;


    @BeforeEach
    void setUp() {
        this.campagnaVaccinale = new CampagnaVaccinale(
                "campagna2",
                Set.of(
                        new Vaccino(
                                "jansen",
                                100L
                        )
                )
        );

        this.expectedCampagnaVaccinale = new CampagnaVaccinale(
                2L,
                "campagna2",
                Set.of(
                        new Vaccino(
                                1L,
                                "jansen",
                                100L
                        )
                )
        );
    }


    @Test
    void shouldReturnCampagneVaccinali() throws Exception {
        mockMvc.perform(get(URI)).andExpect(status().isOk());
        verify(campagnaVaccinaleService).getCampagneVaccinali();
    }

    @Test
    void shouldAddCampagnaVaccinale() throws Exception {
        // when
        when(campagnaVaccinaleService.addCampagnaVaccinale(any())).thenReturn(expectedCampagnaVaccinale);

        MvcResult result = mockMvc.perform(post(URI)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(campagnaVaccinale)))
                .andExpect(status().isOk())
                .andReturn();

        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString).isEqualToIgnoringWhitespace(
                objectMapper.writeValueAsString(expectedCampagnaVaccinale)
        );
    }
}