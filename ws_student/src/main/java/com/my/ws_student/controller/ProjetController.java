package com.my.ws_student.controller;

import com.my.ws_student.models.community.Note_coms;
import com.my.ws_student.models.projects.Projet;
import com.my.ws_student.models.projects.SousTache;
import com.my.ws_student.models.projects.Tache;
import com.my.ws_student.utils.ToJsonData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/projet")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class ProjetController {

    Projet pr = new Projet();
    @GetMapping("/selectAll")
    public ResponseEntity<ToJsonData> SelectAllproject() throws Exception {
        return new ResponseEntity<>(new ToJsonData(pr.SelectAll(),null),HttpStatus.OK);
    }
    @GetMapping("/selectAll/{id}")
    public ResponseEntity<ToJsonData> SelectAllprojectEtu(@PathVariable("id")int id) throws Exception {
        return new ResponseEntity<>(new ToJsonData(pr.SelectAllByEtudiant(id),null),HttpStatus.OK);
    }
    @GetMapping("/pourcentage/{id}")
    public ResponseEntity<ToJsonData> getPourcentageTache(@PathVariable int id) throws Exception {
        double result = pr.getTachesTermineesPourcentage(id);
        return new ResponseEntity<>(new ToJsonData(result,null),HttpStatus.OK);
    }
    @PostMapping("/recherche")
    public ResponseEntity<ToJsonData> recherche(@RequestParam("motcle") String motcle,@RequestParam("datedebut") String datedebut) throws Exception
    {
         return new ResponseEntity<>(new ToJsonData(pr.rechercheParMotCle(motcle,datedebut),null),HttpStatus.OK);
    }
    //    create and update
    @PostMapping("/{action}")
    public ResponseEntity<ToJsonData> ActionProject(@RequestBody Projet project, @PathVariable("action") String action){
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
                return new ResponseEntity<>(new ToJsonData<>(null,"url false"),HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(new ToJsonData<>(message,null), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/soustache/{action}")
    public ResponseEntity<ToJsonData> ActionSousTache(@PathVariable("action")String action,@RequestBody SousTache tache){
        String message = (action.equals("create"))?"tache create with success":"tache is updated with success";
        try {
            if (action.equals("create"))
                tache.save();
            else if (action.equals("update"))
                tache.update();
            else
                return new ResponseEntity<>(new ToJsonData<>(null,"url false"),HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(new ToJsonData<>(message,null), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/findCustomize/{action}/{id}")
    public ResponseEntity<ToJsonData> findCustomize(@PathVariable("action")String action,@PathVariable("id")int id){
        try {
            if (action.equals("tache"))
                return new ResponseEntity<>(new ToJsonData<>(new Tache().SelectByIdProject(id),null), HttpStatus.OK);
            else if (action.equals("sous_tache"))
                return new ResponseEntity<>(new ToJsonData<>(new SousTache().SelectByIdTache(id),null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ToJsonData<>(null,"url false"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
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
                return new ResponseEntity<>(new ToJsonData<>(null,"url false"),HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/etat/{action}/{id}/{etat}")
    public ResponseEntity<ToJsonData> changeState(@PathVariable("action")String action,@PathVariable("id")int id,@PathVariable("etat")int etat){
        try {
            if (action.equals("tache"))
                return new ResponseEntity<>(new ToJsonData<>(new Tache().changeEtat(etat,id),null), HttpStatus.OK);
            else if (action.equals("sous_tache"))
                return new ResponseEntity<>(new ToJsonData<>(new SousTache().changeEtat(etat,id),null), HttpStatus.OK);
            else
                throw new Exception("url false");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/estimation/{id}")
    public ResponseEntity<ToJsonData> getEstimationProjet(@PathVariable int id) throws Exception {
        int result = pr.getDurreeEstimationProjet(id);
        HashMap<String, Integer> response = new HashMap<>();
        response.put("estimation", result);
        return new ResponseEntity<>(new ToJsonData(response,null), HttpStatus.OK);
    }

    @GetMapping("/durree/{id}")
    public ResponseEntity<ToJsonData> getDurreeProjet(@PathVariable int id) throws Exception {
        int result = pr.getDurreeProjet(id);
        HashMap<String, Integer> response = new HashMap<>();
        response.put("durree", result);
        return new ResponseEntity<>(new ToJsonData(response,null), HttpStatus.OK);
    }

    @GetMapping("/moyenne/{idetudiant}")
    public ResponseEntity<ToJsonData> getmoyenneDurree(@PathVariable int idetudiant) throws Exception {
        double result = pr.getMoyenneDurree(idetudiant);
        return new ResponseEntity<>(new ToJsonData(result,null), HttpStatus.OK);
    }

}
