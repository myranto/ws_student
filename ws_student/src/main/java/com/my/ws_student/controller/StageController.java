package com.my.ws_student.controller;

import com.my.ws_student.models.OffreStage;
import com.my.ws_student.models.ProfilEtudiant;
import com.my.ws_student.utils.ToJsonData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stage")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class StageController {

    @GetMapping("/offrestage/{idetudiant}")
    public ResponseEntity<ToJsonData> getListeEnchere(@PathVariable int idetudiant){
        try{
            ArrayList<OffreStage> list = new OffreStage().suggererOffresStage(idetudiant);
//            return ResponseEntity.ok().body(new ToJsonData<>(list,null))
            return new ResponseEntity<>(new ToJsonData(list,null),HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/insererprofil")
    public ResponseEntity<String> insererProfilEtudiant(@RequestBody ProfilEtudiant etudiant) {
        try {
            new ProfilEtudiant().insererProfilEtudiant(etudiant.getIdEtudiant(), etudiant.getDomaine_etude(), etudiant.getProfil(), etudiant.getCompetences(), etudiant.getExperience());
            return ResponseEntity.ok("Le profil étudiant a été inséré avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de l'insertion du profil étudiant");
        }
    }
    @GetMapping("/profil/{etuid}")
    public ResponseEntity<ToJsonData> getProfilEtudiant(@PathVariable("etuid") int idEtu){
        try {
            return new ResponseEntity<>(new ToJsonData<>(new ProfilEtudiant().getProfilEtudiantByIdEtudiant(idEtu),null),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()),HttpStatus.NOT_ACCEPTABLE);
        }
    }


}
