package com.my.ws_student.controller;


import com.my.ws_student.models.Etudiant;
import com.my.ws_student.models.projects.pomodoro.Pomodoro;
import com.my.ws_student.utils.ToJsonData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Etudiant")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class EtudiantController {
    @PostMapping("/connexion/{action}")
    public ResponseEntity<ToJsonData> connexion(@RequestBody Etudiant etu,@PathVariable("action") String action ){
        try {
            if (action.equals("login"))
                return new ResponseEntity<>(new ToJsonData<>(new Etudiant().login(etu.getEmail(),etu.getMotdepasse()),null), HttpStatus.ACCEPTED);
            else if (action.equals("inscription"))
                return new ResponseEntity<>(new ToJsonData<>(etu.save(),null), HttpStatus.CREATED);
            else
                throw new Exception("url false");
        }catch (Exception e){
            return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/pomodoro/last")
    public ResponseEntity<ToJsonData> findLastPomodoro() throws Exception {
        return new ResponseEntity<>(new ToJsonData<>(new Pomodoro().findLast(),null),HttpStatus.OK);
    }
    @PostMapping("/pomodoro/save")
    public ResponseEntity<ToJsonData> Save(@RequestBody Pomodoro pomodoro){
        try {
            return new ResponseEntity<>(new ToJsonData<>(pomodoro.save(),null), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }
}
