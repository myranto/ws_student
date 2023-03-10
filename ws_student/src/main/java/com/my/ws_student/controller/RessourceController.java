package com.my.ws_student.controller;


import com.my.ws_student.models_hasy.ressource.Ressource;
import com.my.ws_student.service.RessourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@CrossOrigin
public class RessourceController {
    RessourceService rs=new RessourceService();

    @GetMapping("/ressources/{id}")
    public List<Ressource> getAllRessource(@PathVariable(name="id")int id) throws Exception {
        List<Ressource> result=rs.getAllRessource(id);
        return result;
    }
}
