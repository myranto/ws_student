package com.my.ws_student.models;


import com.my.ws_student.utils.Connex.Connexion;
import com.my.ws_student.utils.DAO.ObjectBDD;
import com.my.ws_student.utils.inter.IdAnnotation;
import com.my.ws_student.utils.inter.KeyAnnotation;
import com.my.ws_student.utils.inter.TableAnnotation;

import java.sql.*;
import java.util.ArrayList;

@TableAnnotation
public class OffreStage extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation(name = "idoffrestage")
    private int idoffrestage;
    @KeyAnnotation
    private String nom_entreprise;
    @KeyAnnotation
    private String poste;
    @KeyAnnotation
    private String description_stage;
    @KeyAnnotation
    private String exigences;
    @KeyAnnotation
    private String localisation;
    @KeyAnnotation
    private Date date_debut;
    @KeyAnnotation
    private Date date_fin;
    @KeyAnnotation
    private String info_contact;
    @KeyAnnotation
    private String lien;

    public OffreStage() {
    }

    public OffreStage(String nom_entreprise, String poste, String description_stage, String exigences, String localisation, Date date_debut, Date date_fin, String info_contact, String lien) {
        this.nom_entreprise = nom_entreprise;
        this.poste = poste;
        this.description_stage = description_stage;
        this.exigences = exigences;
        this.localisation = localisation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.info_contact = info_contact;
        this.lien = lien;
    }

    public OffreStage(int idoffrestage, String nom_entreprise, String poste, String description_stage, String exigences, String localisation, Date date_debut, Date date_fin, String info_contact, String lien) {
        this.idoffrestage = idoffrestage;
        this.nom_entreprise = nom_entreprise;
        this.poste = poste;
        this.description_stage = description_stage;
        this.exigences = exigences;
        this.localisation = localisation;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.info_contact = info_contact;
        this.lien = lien;
    }

    public int getIdoffrestage() {
        return idoffrestage;
    }

    public void setIdoffrestage(int idoffrestage) {
        this.idoffrestage = idoffrestage;
    }

    public String getNom_entreprise() {
        return nom_entreprise;
    }

    public void setNom_entreprise(String nom_entreprise) {
        this.nom_entreprise = nom_entreprise;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getDescription_stage() {
        return description_stage;
    }

    public void setDescription_stage(String description_stage) {
        this.description_stage = description_stage;
    }

    public String getExigences() {
        return exigences;
    }

    public void setExigences(String exigences) {
        this.exigences = exigences;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public String getInfo_contact() {
        return info_contact;
    }

    public void setInfo_contact(String info_contact) {
        this.info_contact = info_contact;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

        public ArrayList<OffreStage> suggererOffresStage(int idetudiant) throws Exception {

        // Créer un profil étudiant fictif
        ProfilEtudiant profilEtudiant = new ProfilEtudiant().getProfilEtudiantByIdEtudiant(idetudiant);
        // Récupérer toutes les offres de stage de la base de données
        ArrayList<OffreStage> offresStages = this.SelectAllOfferStage();
        // Filtrer les offres de stage en fonction du profil étudiant
        ArrayList<OffreStage> offresStageFiltrees = new ArrayList<>();
        for (OffreStage offreStage : offresStages) {
            if (offreStage.getPoste().equals(profilEtudiant.getProfil())
                    && offreStage.getExigences().contains(profilEtudiant.getCompetences())) {
                offresStageFiltrees.add(offreStage);
            }
        }
        // Retourner les offres de stage filtrées
        return offresStageFiltrees;
    }
    public ArrayList<OffreStage> SelectAllOfferStage() throws Exception {
        return super.SelectAll(Connexion.getConnection());
    }
//    public static void main(String[] args) throws Exception {
//        ArrayList<OffreStage> list = new OffreStage().getAllOffreStage();
//        for (OffreStage e:list) {
//            System.out.println(e.getExigences()+" " +e.getDescription_stage());
//        }
//    }
}
