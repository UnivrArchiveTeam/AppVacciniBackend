package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.id.DispId;
import com.kodikas.appvaccinibackend.model.Disponibilita;
import com.kodikas.appvaccinibackend.repository.DisponibilitaRepostitory;
import com.kodikas.appvaccinibackend.wrapper.DisponibilitaWrapper;
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
        disponibilitaRepostitory.save(disponibilita);
    }

    public DisponibilitaWrapper getDisponibilitaDate (String categoria , Long vaccino){

        List<Disponibilita> id_find = disponibilitaRepostitory.findAllById_IdVaccino(vaccino);

        if(id_find.isEmpty())
            throw new IllegalArgumentException("Nessun valore trovato");

        System.out.println("RESPONSE--> "+id_find);

        DisponibilitaWrapper result = null;
        for (Disponibilita questo:id_find){
            if (questo.getCategoria().equals(categoria)){
                if(result == null){
                    result = new DisponibilitaWrapper(List.of(questo));
                }
                else {
                    result.setDisponibilita(List.of(questo));
                }
            }
        }

        return result;
    }

}
