package com.my.ws_student.models_hasy;

import annotation.DBAnnotation;
import persistence.ObjectBdd;

import java.sql.Date;

@DBAnnotation.Table(name="etudiant")
public class Etudiant extends ObjectBdd {

    @DBAnnotation.Id
    private int id_etudiant;
    @DBAnnotation.Column
    private String nom;
    @DBAnnotation.Column
    private String prenom;
    @DBAnnotation.Column
    private String email;
    @DBAnnotation.Column
    private String motdepasse;
    @DBAnnotation.Column
    private Date date_naissance;
    @DBAnnotation.Column
    private Date date_inscription;
    @DBAnnotation.Column
    private String carte_etudiant;

    public int getId_etudiant() {
        return id_etudiant;
    }

    public void setId_etudiant(int id_etudiant) {
        this.id_etudiant = id_etudiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Date getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(Date date_inscription) {
        this.date_inscription = date_inscription;
    }

    public String getCarte_etudiant() {
        return carte_etudiant;
    }

    public void setCarte_etudiant(String carte_etudiant) {
        this.carte_etudiant = carte_etudiant;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id_etudiant=" + id_etudiant +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", motdepasse='" + motdepasse + '\'' +
                ", date_naissance=" + date_naissance +
                ", date_inscription=" + date_inscription +
                ", carte_etudiant='" + carte_etudiant + '\'' +
                '}';
    }
}