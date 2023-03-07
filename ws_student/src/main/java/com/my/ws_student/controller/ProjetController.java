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
    public ResponseEntity<Double> getPourcentageTache(@PathVariable int id) throws Exception {
        double result = pr.getTachesTermineesPourcentage(id);
        return ResponseEntity.ok(result);
    }


    @PostMapping("/recherche")
    public ResponseEntity<List<Projet>> recherche(@RequestParam("motcle") String motcle,@RequestParam("datedebut") String datedebut) throws Exception
    {
         return new ResponseEntity<List<Projet>>(pr.rechercheParMotCle(motcle,datedebut),HttpStatus.OK);
    }

    @GetMapping("/estimation/{id}")
    public ResponseEntity<Map<String, Integer>> getEstimationProjet(@PathVariable int id) throws Exception {
        int result = pr.getDurreeEstimationProjet(id);
        Map<String, Integer> response = new HashMap<>();
        response.put("estimation", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/durree/{id}")
    public ResponseEntity<Map<String, Integer>> getDurreeProjet(@PathVariable int id) throws Exception {
        int result = pr.getDurreeProjet(id);
        Map<String, Integer> response = new HashMap<>();
        response.put("durree", result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
