package com.my.ws_student.controller;

import com.my.ws_student.models.projects.Tache;
import com.my.ws_student.utils.ToJsonData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/tache")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class TacheController {

    Tache t = new Tache();

    @GetMapping("/pourcentage/{id}")
    public ResponseEntity<ToJsonData> getPourcentageTache(@PathVariable int id) throws Exception {
        double result = t.getSousTachesTermineesPourcentage(id);
        return new ResponseEntity<>(new ToJsonData(result,null),HttpStatus.OK);
    }
    @PostMapping("/recherche")
    public ResponseEntity<ToJsonData> recherche(@RequestParam("motcle") String motcle, @RequestParam("dateplanning") String dateplanning) throws Exception
    {
        return new ResponseEntity<>(new ToJsonData(t.rechercheParMotCle(motcle,dateplanning),null),HttpStatus.OK);
    }
    @GetMapping("/durree/{id}")
    public ResponseEntity<ToJsonData> getDureeTache(@PathVariable int id) throws Exception {
        int[] result = t.getDureeTache(id);
        HashMap<String, Integer> response = new HashMap<>();
        response.put("dureeEstimee", result[0]);
        response.put("dureePassee", result[1]);
        return new ResponseEntity<>(new ToJsonData(response,null), HttpStatus.OK);
    }

}
