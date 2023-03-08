package com.example.demo.controller;

import com.example.demo.models.OffreStage;
import com.example.demo.models.ProfilEtudiant;
import com.example.demo.models.community.Commentaire;
import com.example.demo.models.community.Note_coms;
import com.example.demo.models.community.Publication;
import com.example.demo.models.projects.Projet;
import com.example.demo.models.projects.Tache;
import com.example.demo.utils.Fonction;
import com.example.demo.utils.ToJsonData;
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
    OffreStage os = new OffreStage();


    @GetMapping("/offrestage/{idetudiant}")
    public ResponseEntity<List<OffreStage>> getListeEnchere(@PathVariable int idetudiant){
        try{
            List<OffreStage> list = os.suggererOffresStage(idetudiant);
            return new ResponseEntity<List<OffreStage>>(list,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/insererprofil/{idetudiant}")
    public ResponseEntity<String> insererProfilEtudiant(@PathVariable int idEtudiant,
                                                        @RequestParam String domaineEtude,
                                                        @RequestParam String profil,
                                                        @RequestParam String competences,
                                                        @RequestParam int experience) {
        try {
            ProfilEtudiant.insererProfilEtudiant(idEtudiant, domaineEtude, profil, competences, experience);
            return ResponseEntity.ok("Le profil étudiant a été inséré avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur s'est produite lors de l'insertion du profil étudiant");
        }
    }


}
