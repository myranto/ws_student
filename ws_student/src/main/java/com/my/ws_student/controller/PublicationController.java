package com.my.ws_student.controller;

import com.my.ws_student.models.community.Commentaire;
import com.my.ws_student.models.community.Note_coms;
import com.my.ws_student.models.community.Publication;
import com.my.ws_student.models.param.FichierComs;
import com.my.ws_student.models.projects.Projet;
import com.my.ws_student.models.projects.SousTache;
import com.my.ws_student.models.projects.Tache;
import com.my.ws_student.utils.Fonction;
import com.my.ws_student.utils.ToJsonData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/publication")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class PublicationController {
    @GetMapping("/selectAll")
    public ResponseEntity<ToJsonData> getAllpub() throws Exception {
        ArrayList<Publication> list = new Publication().SelectAll();
        System.out.println(list.size());
        return new ResponseEntity<>(new ToJsonData<>(list,null), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ToJsonData> createPublication(@RequestBody Publication pub){
        try {
            Fonction f = new Fonction();
            String texte = pub.getTexte();
            if(f.controleCommentaire(texte))
            {
                return new ResponseEntity<>(new ToJsonData<>(null,"publication innaceptable"), HttpStatus.UNAUTHORIZED);
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
    public ResponseEntity<ToJsonData> createCommentary(@RequestBody FichierComs coms, @PathVariable("action") String action){
        try {
            Fonction f = new Fonction();
            if (action.equals("create"))
                if (f.controleCommentaire(coms.getCommentaire().getTexte()))
                {
                    return new ResponseEntity<>(new ToJsonData<>(null,"commentaire innacceptable"), HttpStatus.UNAUTHORIZED);
                }
                else {
                    Commentaire c = coms.getCommentaire().save();
                    coms.getFile().setCommentaireidCommentaire(c.getIdCommentaire());
                    coms.getFile().save();
                }
            else if(action.equals("update"))
                coms.getCommentaire().update();
            else
                throw new Exception("url false");
            return new ResponseEntity<>(new ToJsonData<>("create commentary with success",null), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/note_commentaire/create")
    public ResponseEntity<ToJsonData> makeNoteCommentaire(@RequestBody Note_coms note){
        try {
            note.save();
            Note_coms com = new Note_coms().SelectAllByComsEtu(note.getIdCommentaire(), note.getIdEtudiant());
            if (com != null) {
                com.setNotes(note.getNotes());
                com.update();
            }
            return new ResponseEntity<>(new ToJsonData<>("note create with success",null), HttpStatus.OK);
        }catch (Exception e){
                return new ResponseEntity<>(new ToJsonData<>(null,e.getMessage()), HttpStatus.UNAUTHORIZED);


        }
    }
    @GetMapping("/note_commentaire/find/{idcoms}/{idetu}")
    public ResponseEntity<ToJsonData> getNoteCommentaire(@PathVariable("idcoms") int idcoms,@PathVariable("idetu")int idetu){
        try {
            Note_coms com = new Note_coms().SelectAllByComsEtu(idcoms,idetu);
            return new ResponseEntity<>(new ToJsonData<>(com,null), HttpStatus.OK);
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
