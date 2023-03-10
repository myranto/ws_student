package com.my.ws_student.controller;


import com.my.ws_student.models.projects.Projet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projet")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class ProjetController {

    Projet pr = new Projet();

    @GetMapping("/pourcentage/{id}")
    public double getPourcentageTache(@PathVariable int id) throws Exception {
        double result = pr.getTachesTermineesPourcentage(id);
        return result;
    }

    @PostMapping("/recherche")
    public ResponseEntity<List<Projet>> recherche(@RequestParam("motcle") String motcle,@RequestParam("datedebut") String datedebut) throws Exception
    {
         return new ResponseEntity<List<Projet>>(pr.rechercheParMotCle(motcle,datedebut),HttpStatus.OK);
    }

}
