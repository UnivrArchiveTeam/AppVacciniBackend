package com.kodikas.appvaccinibackend.controller;
import com.kodikas.appvaccinibackend.model.Entitled;
import com.kodikas.appvaccinibackend.service.EntitledService;
import com.kodikas.appvaccinibackend.wrapper.EntitledWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/entitled")
public class EntitledController {

    private final EntitledService entitledService;

    @GetMapping
    public EntitledWrapper getAllEntitled(){
        return new EntitledWrapper(entitledService.getAllEntitled());
    }

    @PostMapping
    public Entitled addEntitled(@RequestBody Entitled entitled){
        return entitledService.addEntitled(entitled);
    }

    @GetMapping("/{ca}")
    public EntitledWrapper getAllEntitledByCategory(@PathVariable String ca) throws UnsupportedEncodingException {
        String category = URLDecoder.decode(ca,"UTF-8");
        System.out.println(category);
        return new EntitledWrapper(entitledService.getEntitledByCategory(category));
    }

    @GetMapping("/categories")
    public List<String> getCategories() {
        return entitledService.getCategories();
    }
}
