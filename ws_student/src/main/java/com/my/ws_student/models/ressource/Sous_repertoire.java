package com.my.ws_student.models.ressource;


import com.my.ws_student.utils.has.BDDobject;
import com.my.ws_student.utils.has.DBAnnotation;

import java.sql.Date;

@DBAnnotation.Table(name = "sous_repertoire")
public class Sous_repertoire extends BDDobject {
    @DBAnnotation.Id
    int id_sous_repertoire;
    @DBAnnotation.Column
    int id_repertoire_mere;
    @DBAnnotation.Column
    int id_repertoire_fille;
    @DBAnnotation.FK(id = "id_repertoire_mere")
    Repertoire repertoire_mere;
    @DBAnnotation.FK(id = "id_repertoire_fille")
    Repertoire repertoire_fille;

    public int getId_sous_repertoire() {
        return id_sous_repertoire;
    }

    public void setId_sous_repertoire(int id_sous_repertoire) {
        this.id_sous_repertoire = id_sous_repertoire;
    }

    public int getId_repertoire_mere() {
        return id_repertoire_mere;
    }

    public void setId_repertoire_mere(int id_repertoire_mere) {
        this.id_repertoire_mere = id_repertoire_mere;
    }

    public int getId_repertoire_fille() {
        return id_repertoire_fille;
    }

    public void setId_repertoire_fille(int id_repertoire_fille) {
        this.id_repertoire_fille = id_repertoire_fille;
    }

    public Repertoire getRepertoire_mere() {
        return repertoire_mere;
    }

    public void setRepertoire_mere(Repertoire repertoire_mere) {
        this.repertoire_mere = repertoire_mere;
    }

    public Repertoire getRepertoire_fille() {
        return repertoire_fille;
    }

    public void setRepertoire_fille(Repertoire repertoire_fille) {
        this.repertoire_fille = repertoire_fille;
    }

    @Override
    public String toString() {
        return "Sous_repertoire{" +
                "id_sous_repertoire=" + id_sous_repertoire +
                ", id_repertoire_mere=" + id_repertoire_mere +
                ", id_repertoire_fille=" + id_repertoire_fille +
                ", repertoire_mere=" + repertoire_mere +
                ", repertoire_fille=" + repertoire_fille +
                '}';
    }


    public static void main(String args[]) throws Exception {
//        Etudiant e=new Etudiant();
//        e.setNom("Andrianome");
//        e.setPrenom("David Hasinjaka");
//        e.setDate_naissance(Date.valueOf("2003-03-03"));
//        e.setDate_inscription(Date.valueOf("2022-04-09"));
//        e.setCarte_etudiant("ETU001543");
//        e.setEmail("david.hasinjaka@gmail");
//        e.save();
//        Repertoire r=new Repertoire();
////        r=r.findById(1);
////        Repertoire r2=new Repertoire();
////        r2.setNom_repertoire("Info");
////        r.updateRow(r2);
////        System.out.println(r);
//        r.setId_etudiant(1);
//        r.setNom("Info");
//        r.save();
        Sous_repertoire s=new Sous_repertoire();
        s.setId_repertoire_fille(2);
        s.setId_repertoire_mere(1);
        s.save();
//        s=s.findById(1);
//
//        System.out.println(s);
//        Fichier f=new Fichier();
//        f.setId_etudiant(1);
//        f.setId_repertoire(1);
//        f.setNom("pdf");
//        f.setValue("sdfser3435rdfaj");
//        f.save();
    }
}


