package com.my.ws_student.controller;

import com.my.ws_student.models.ressource.Repertoire;
import com.my.ws_student.models.ressource.Sous_repertoire;
import com.my.ws_student.service.RepertoireService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repertoire")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class RepertoireController {
    RepertoireService rs=new RepertoireService();
    @GetMapping("/{id}")
    public List<Repertoire> getAllRepertoire(@PathVariable(name="id") int id) throws Exception {
        List<Repertoire> result=rs.listeRepertoire(id);
        return result;
    }
    @PostMapping("/nouveau")
    public void ajout(@RequestBody Repertoire repertoire) throws Exception {
        repertoire.setId_etudiant(1);
        int id=rs.nouveauDossier(repertoire);
        Sous_repertoire sous_repertoire=new Sous_repertoire();
        sous_repertoire.setId_repertoire_mere(1);
        sous_repertoire.setId_repertoire_fille(id);
        sous_repertoire.save();
    }
}
