package com.my.ws_student.service;


import com.my.ws_student.models_hasy.ressource.Fichier;

import java.lang.reflect.Field;
import java.util.List;

public class FichierService {
    public void ajouterFichier(Fichier fichier) throws Exception{
        fichier.save();
    }
    public void ajouterFichier(List<Fichier> fichiers) throws Exception{
        for (Fichier f: fichiers){
            f.save();
        }
    }

    public List<Fichier> listeFiles(int id) throws Exception {
        Fichier f=new Fichier();
        List<Fichier> results=f.findAllWhere("where id_repertoire="+1);
        return results;
    }

    public void deletefile(int id) {
    }

    public void update(Fichier fichier) {
    }

    public static void main(String args[]) throws Exception {
        for (Fichier f : new FichierService().listeFiles(1)){
            System.out.println(f);
        }
//        for (Field f : Fichier.class.getSuperclass().getDeclaredFields()){
//            System.out.println(f);
//        }
    }

}
