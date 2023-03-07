package com.my.ws_student.controller;


import com.my.ws_student.models.Etudiant;
import com.my.ws_student.models.projects.Projet;
import com.my.ws_student.models.projects.SousTache;
import com.my.ws_student.models.projects.Tache;
import com.my.ws_student.utils.ToJsonData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class ProjectController {
//    create and update
    @PostMapping("/{action}")
    public ResponseEntity<ToJsonData> ActionProject(@RequestBody Projet project,@PathVariable("action") String action){
        String message = (action.equals("create"))?"project create with success":"project is updated with success";
        try {
            if (action.equals("create"))
                 project.save();
            else if (action.equals("update"))
                project.update();
            else
                throw new Exception("url false");
            return new ResponseEntity<>(new ToJsonData<>(message,null), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/tache/{action}")
    public ResponseEntity<ToJsonData> ActionTache(@RequestBody Tache tache, @PathVariable("action") String action){
        String message = (action.equals("create"))?"tache create with success":"tache is updated with success";
        try {
            if (action.equals("create"))
                tache.save();
            else if (action.equals("update"))
                tache.update();
            else
                throw new Exception("url false");
            return new ResponseEntity<>(new ToJsonData<>(message,null), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/soustache/{action}")
    public ResponseEntity<ToJsonData> ActionSousTache(@PathVariable("action")String action, SousTache tache){
        String message = (action.equals("create"))?"tache create with success":"tache is updated with success";
        try {
            if (action.equals("create"))
                tache.save();
            else if (action.equals("update"))
                tache.update();
            else
                throw new Exception("url false");
            return new ResponseEntity<>(new ToJsonData<>(message,null), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/find/{action}/{id}")
    public ResponseEntity<ToJsonData> findById(@PathVariable("action")String action,@PathVariable("id")int id){
        try {
            if (action.equals("project"))
                return new ResponseEntity<>(new ToJsonData<>(new Projet().findById(id),null), HttpStatus.OK);
            else if (action.equals("tache"))
                return new ResponseEntity<>(new ToJsonData<>(new Tache().findById(id),null), HttpStatus.OK);
            else if (action.equals("sous_tache"))
                return new ResponseEntity<>(new ToJsonData<>(new SousTache().findById(id),null), HttpStatus.OK);
            else
                throw new Exception("url false");
        } catch (Exception e) {
            return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/etat/{action}/{id}/{etat}/{dure}")
    public ResponseEntity<ToJsonData> changeState(@PathVariable("action")String action,@PathVariable("id")int id,@PathVariable("etat")int etat,@PathVariable("dure")int dure){
        try {
            if (action.equals("tache"))
                return new ResponseEntity<>(new ToJsonData<>(new Tache().changeEtat(etat,id),null), HttpStatus.OK);
            else if (action.equals("sous_tache"))
                return new ResponseEntity<>(new ToJsonData<>(new SousTache().changeEtat(etat,id,dure),null), HttpStatus.OK);
            else
                throw new Exception("url false");
        } catch (Exception e) {
            return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }
}
