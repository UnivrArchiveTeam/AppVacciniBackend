package com.kodikas.appvaccinibackend.service;

import com.kodikas.appvaccinibackend.model.Availability;
import com.kodikas.appvaccinibackend.repository.AvailabilityRepostitory;
import com.kodikas.appvaccinibackend.wrapper.AvailabilityWrapper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AvailabilityService {

    private  final AvailabilityRepostitory availabilityRepostitory;

    public List<Availability> getAllAvailability(){
        return  availabilityRepostitory.findAll();
    }

    public void addAvailability(Availability newEntry){
        availabilityRepostitory.save(newEntry);
    }

    public List<Availability> getByDateandCategoryAvailability(String category , LocalDate date){

        List<Availability> filtered_category = availabilityRepostitory.findAllByCategoria(category);
        List<Availability> result = null;

        if (filtered_category.isEmpty()){
            throw  new IllegalStateException("Nothing available");
        }
        for(Availability search : filtered_category){
            if (search.getDataInizio().isBefore(date)&&search.getDataFine().isAfter(date)||(search.getDataInizio().isEqual(date)||search.getDataFine().isEqual(date))){
                if (result == null){
                    result = List.of(search);
                }
                else {
                    result.add(search);
                }
            }
        }

        if(result == null){
            throw new IllegalStateException("Nothing on this date");
        }

        return result;
    }
}
