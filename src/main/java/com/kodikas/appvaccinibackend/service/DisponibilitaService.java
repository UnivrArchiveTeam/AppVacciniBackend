package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Disponibilita;
import com.kodikas.appvaccinibackend.repository.DisponibilitaRepostitory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@AllArgsConstructor
public class DisponibilitaService {

    private  final DisponibilitaRepostitory disponibilitaRepostitory;

    public List<Disponibilita> getDisponibilita (){
        return  disponibilitaRepostitory.findAll();
    }

    public void addNewDisponibilita(Disponibilita disponibilita){
        //System.out.println(disponibilita);
    }
}
