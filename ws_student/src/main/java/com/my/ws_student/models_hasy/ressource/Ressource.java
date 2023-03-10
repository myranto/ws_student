package com.my.ws_student.models_hasy.ressource;

import annotation.DBAnnotation;
import com.my.ws_student.models_hasy.Etudiant;
import persistence.ObjectBdd;

import java.sql.Timestamp;

public class Ressource extends ObjectBdd {
    int id_ressource;
    @DBAnnotation.Column(name = "nom")
    String nom;
    @DBAnnotation.Column
    @DBAnnotation.Default
    Timestamp date_creation;
    @DBAnnotation.Column
    int id_etudiant;

    int taille;
    String type;
    @DBAnnotation.FK(id = "id_etudiant")
    Etudiant etudiant;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTaille() {
        return taille;
    }

    public void setTaille(int taille) {
        this.taille = taille;
    }

    public int getId_ressource() {
        return id_ressource;
    }

    public void setId_ressource(int id_ressource) {
        this.id_ressource = id_ressource;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Timestamp getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Timestamp dateCreation) {
        this.date_creation = dateCreation;
    }

    public int getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(int id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }


}
