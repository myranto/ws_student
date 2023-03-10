package com.my.ws_student.service;

import com.my.ws_student.models_hasy.ressource.Repertoire;

import java.util.List;

public class RepertoireService {
    public List<Repertoire> listeRepertoire(int id_repertoire) throws Exception{
        List<Repertoire> repertoires=new Repertoire().findAllFromView("select * from sous_repertoire_view where id_repertoire="+id_repertoire);
        return repertoires;
    }



    public static void main(String p[]) throws Exception {
        Repertoire re=new Repertoire();
        for (Repertoire r: new RepertoireService().listeRepertoire(1)){
            System.out.println(r);
        }
    }


    public int nouveauDossier(Repertoire repertoire) throws Exception {
        return repertoire.save();
    }
}
