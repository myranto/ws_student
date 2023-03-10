package com.my.ws_student.controller;


import com.my.ws_student.models.projects.Tache;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tache")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class TacheController {

    Tache t = new Tache();

    @GetMapping("/pourcentage/{id}")
    public double getPourcentageTache(@PathVariable int id) throws Exception {
        double result = t.getSousTachesTermineesPourcentage(id);
        return result;
    }

    @PostMapping("/recherche")
    public ResponseEntity<List<Object[]>> recherche(@RequestParam("motcle") String motcle, @RequestParam("dateplanning") String dateplanning) throws Exception
    {
        return new ResponseEntity<List<Object[]>>((MultiValueMap<String, String>) t.rechercheParMotCle(motcle,dateplanning),HttpStatus.OK);
    }

}
