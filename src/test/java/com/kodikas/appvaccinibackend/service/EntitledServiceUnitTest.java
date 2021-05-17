package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Entitled;
import com.kodikas.appvaccinibackend.repository.EntitledRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EntitledServiceUnitTest {

    @Mock
    private EntitledRepository entitledRepository;
    private EntitledService underTest;
    private Entitled entry1;
    private Entitled entry2;

    @BeforeEach
    void setUp(){
        underTest = new EntitledService(entitledRepository);

        entry1 = new Entitled()
    }
    @Test
    void getAllEntitled() {
    }

    @Test
    void addEntitled() {
    }

    @Test
    void getEntitledbyCategory() {
    }
}