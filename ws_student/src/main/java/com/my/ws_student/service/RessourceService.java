package com.my.ws_student.service;

import com.my.ws_student.models_hasy.ressource.Fichier;
import com.my.ws_student.models_hasy.ressource.Repertoire;
import com.my.ws_student.models_hasy.ressource.Ressource;

import java.util.ArrayList;
import java.util.List;

public class RessourceService {

    public List<Ressource> getAllRessource(int id) throws Exception {
        Fichier f=new Fichier();
        Repertoire r=new Repertoire();
        List<Ressource> result=new ArrayList<>();
        result.addAll(f.findAllWhere("where id_repertoire="+id));
        result.addAll(r.findAllFromView("select * from sous_repertoire_view where id_repertoire_mere="+id));
        return result;
    }

    public static void main(String argsp[]) throws Exception {
        List<Ressource> ressources=new RessourceService().getAllRessource(2);
        for (Ressource r: ressources){
            System.out.println(r);
        }
    }
}
