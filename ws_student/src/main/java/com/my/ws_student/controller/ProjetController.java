package com.example.demo.controller;

import com.example.demo.models.community.Publication;
import com.example.demo.models.projects.Projet;
import com.example.demo.models.projects.Tache;
import com.example.demo.utils.ToJsonData;
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
