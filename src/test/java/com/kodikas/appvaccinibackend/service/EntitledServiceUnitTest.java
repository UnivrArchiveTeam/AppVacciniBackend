package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Entitled;
import com.kodikas.appvaccinibackend.repository.EntitledRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

        entry1 = new Entitled("over80");
        entry2 = new Entitled("over50");
    }
    @Test
    void getAllEntitled() {
        List<Entitled> entitledsList = List.of(entry1,entry2);

        when(entitledRepository.findAll()).thenReturn(entitledsList);

        underTest.getAllEntitled();

        verify(entitledRepository).findAll();
    }

    @Test
    void addEntitled() {

        underTest.addEntitled(entry1);
        verify(entitledRepository).save(entry1);
    }

    @Test
    void getEntitledbyCategory_shouldcorrectlyreturns() {

        List<Entitled> list_entitled = List.of(entry1);
        String category = "over80";

        when(entitledRepository.findAllByCategory(category)).thenReturn(list_entitled);

        List<Entitled> result = underTest.getEntitledByCategory(category);
        boolean check= false;

        for (Entitled find : result){

            if(!(find.getCategory().equals(category))){
                check = true;
            }
        }

        assertThat(check).isFalse();
    }

    @Test
    void getEntitledbyCategory_shouldreturnException() {

        String category = "under40";
        assertThatThrownBy(
                ()-> underTest.getEntitledByCategory(category)
        ).isInstanceOf(IllegalStateException.class).hasMessage("I have not found anyone entitled to this category");

        verify(entitledRepository).findAllByCategory(any());
    }
}