package com.my.ws_student.controller;


import com.my.ws_student.models.ressource.Ressource;
import com.my.ws_student.service.RessourceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class RessourceController {
    RessourceService rs=new RessourceService();

    @GetMapping("/ressources/{id}")
    public List<Ressource> getAllRessource(@PathVariable(name="id")int id) throws Exception {
        List<Ressource> result=rs.getAllRessource(id);
        return result;
    }
}
