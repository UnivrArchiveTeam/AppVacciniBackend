package com.kodikas.appvaccinibackend.controller;
import com.kodikas.appvaccinibackend.model.Entitled;
import com.kodikas.appvaccinibackend.service.EntitledService;
import com.kodikas.appvaccinibackend.wrapper.EntitledWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "vaccine/Entitled")
public class EntitledController {

    private EntitledService entitledService ;

    @Autowired
    public EntitledController(EntitledService entitledService) {
        this.entitledService = entitledService;
    }

    @GetMapping
    public EntitledWrapper getAllEntitled (){return new EntitledWrapper(entitledService.getAllEntitled());}

    @GetMapping
    public Entitled addEntitled(Entitled newEntry){ return entitledService.addEntitled(newEntry);}

    @GetMapping("/Entitled/{category}")
    public  EntitledWrapper getEntitledbyCategory (@PathVariable String category){
        return new EntitledWrapper(entitledService.getEntitledbyCategory(category));
    }
}
