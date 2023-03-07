package com.example.demo.controller;

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

@RestController
@RequestMapping("/publication")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class PublicationController {
    @GetMapping("/selectAll")
    public ResponseEntity<ArrayList<Publication>> getAllpub() throws Exception {
        ArrayList<Publication> list = new Publication().SelectAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ToJsonData> createPublication(@RequestBody Publication pub){
        try {
            Fonction f = new Fonction();
                 String texte = pub.getTexte();
                 if(f.controleCommentaire(texte))
                 {
                     return new ResponseEntity<>(new ToJsonData<>(null,"commentaire innaceptable"), HttpStatus.UNAUTHORIZED);
                 }
                 else {
                     Publication p = pub.save();
                     return new ResponseEntity<>(new ToJsonData<>("create with success",null), HttpStatus.OK);
                 }

        }catch (Exception e){
            return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/commentaire/{action}")
    public ResponseEntity<ToJsonData> createCommentary(@RequestBody Commentaire coms, @PathVariable("action") String action){
        try {
            Fonction f = new Fonction();
            if (action.equals("create"))
                if (f.controleCommentaire(coms.getTexte()))
                {
                    return new ResponseEntity<>(new ToJsonData<>("commentaire innacceptable",null), HttpStatus.UNAUTHORIZED);
                }
                else {
                    coms.save();
                }
            else if(action.equals("update"))
                coms.update();
            else
                throw new Exception("url false");
            return new ResponseEntity<>(new ToJsonData<>("create commentary with success",null), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/note_commentaire")
    public ResponseEntity<ToJsonData> makeNoteCommentaire(@RequestBody Note_coms note){
        try {
            note.save();
            return new ResponseEntity<>(new ToJsonData<>("note create with success",null), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/find/{action}/{id}")
    public ResponseEntity<ToJsonData> findById(@PathVariable("action")String action,@PathVariable("id")int id){
        try {
            if (action.equals("pub"))
                return new ResponseEntity<>(new ToJsonData<>(new Publication().findById(id),null), HttpStatus.OK);
            else if (action.equals("coms"))
                return new ResponseEntity<>(new ToJsonData<>(new Commentaire().findById(id),null), HttpStatus.OK);
            else
                throw new Exception("url false");
        } catch (Exception e) {
            return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()),HttpStatus.UNAUTHORIZED);
        }
    }
}
