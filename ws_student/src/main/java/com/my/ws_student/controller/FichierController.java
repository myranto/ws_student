package com.my.ws_student.controller;


import com.my.ws_student.models.ressource.Fichier;
import com.my.ws_student.service.FichierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fichier")
@CrossOrigin(methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS})
public class FichierController {
    FichierService fs=new FichierService();
    @GetMapping("/{id}")
    public List<Fichier> getAllFichier(@PathVariable(name="id") int id) throws Exception {
        List<Fichier> result=fs.listeFiles(id);
        return result;
    }

    @PostMapping("/ajout")
    public void ajout(@RequestBody Fichier fichier) throws Exception {
        fichier.setId_repertoire(1);
        fichier.setId_etudiant(1);
        fs.ajouterFichier(fichier);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") int id) throws Exception {
        fs.deletefile(id);
    }

    @PostMapping("/update/{id}")
    public void update(@RequestBody Fichier fichier) throws Exception {
        fs.update(fichier);
    }

}
