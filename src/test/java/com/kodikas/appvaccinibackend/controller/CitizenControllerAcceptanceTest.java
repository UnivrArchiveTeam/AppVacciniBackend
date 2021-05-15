package com.kodikas.appvaccinibackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodikas.appvaccinibackend.model.Citizen;
import com.kodikas.appvaccinibackend.repository.CitizenRepository;
import com.kodikas.appvaccinibackend.wrapper.CitizenWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CitizenControllerAcceptanceTest {
    private final static String URI = "/citizens";

    private Citizen citizen;
    private Citizen citizen2;
    private String fiscalCode;

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private CitizenRepository citizenRepository;

    @BeforeEach
    void setUp() {
        citizenRepository.deleteAll();
        fiscalCode = "MZZMMT61M22D854K";
        citizen = new Citizen(
                fiscalCode,
                237971319838010581L,
                "Mouhameth",
                "Mazza",
                "Gaiarine",
                LocalDate.parse("1961-08-22"),
                "paziente iperteso"
        );
        citizen2 = new Citizen(
                "NNCFNC59C67E709B",
                963231202896772494L,
                "Francesca Amira",
                "Innocenti",
                "Lozzo Atestino",
                LocalDate.parse("1959-03-27"),
                "paziente oncologico"
        );
    }

    @Test
    void getCitizens_shouldRetrieveListOfCitizensFromDatabase() throws Exception {
        // given
        citizenRepository.saveAll(List.of(citizen,citizen2));

        // then
        MvcResult result = mockMvc.perform(get(URI)).andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        CitizenWrapper resultObject = objectMapper.readValue(response, CitizenWrapper.class);
        assertThat(resultObject.getCitizens()).isNotNull();
        assertThat(resultObject.getCitizens().size()).isEqualTo(2);
    }

    @Test
    void getCitizen_shouldReturnCitizen() throws Exception {
        // given
        citizenRepository.saveAll(List.of(citizen,citizen2));

        // then
        MvcResult result = mockMvc.perform(get(URI+"/"+fiscalCode)).andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        assertThat(response).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(citizen));
    }

    @Test
    void modifyRegistered_shouldReturnModifiedCitizen() throws Exception {
        // given
        citizenRepository.save(citizen);
        citizen.setRegistered(true);

        // then
        MvcResult result = mockMvc.perform(put(URI+"/registered/"+fiscalCode)).andExpect(status().isOk()).andReturn();
        String response = result.getResponse().getContentAsString();
        assertThat(response).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(citizen));
    }
}