package com.example.demo.models;


import com.example.demo.utils.Connex.Connexion;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OffreStage {

    private int idoffrestage;
    private String nom_entreprise;
    private String poste;
    private String description_stage;
    private String exigences;
    private String localisation;
    private Date date_debut;
    private Date date_fin;
    private String info_contact;
    private String lien;


    public  List<OffreStage> getAllOffreStage() throws Exception {
        Connection con = Connexion.getConnection();
        String sql = "SELECT * FROM OffreStage";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<OffreStage> offresStage = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("idoffrestage");
            String nomEntreprise = rs.getString("nom_entreprise");
            String poste = rs.getString("poste");
            String description = rs.getString("description_stage");
            String exigences = rs.getString("exigences");
            String localisation = rs.getString("localisation");
            Date dateDebut = rs.getDate("date_debut");
            Date dateFin = rs.getDate("date_fin");
            String infoContact = rs.getString("info_contact");
            String lien = rs.getString("lien");
            OffreStage offreStage = new OffreStage(id, nomEntreprise, poste, description, exigences, localisation, dateDebut, dateFin, infoContact, lien);
            offresStage.add(offreStage);
        }
        con.close();
        return offresStage;
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

    public OffreStage() {

    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public List<OffreStage> suggererOffresStage(int idetudiant) throws Exception{

        // Créer un profil étudiant fictif
        ProfilEtudiant profilEtudiant = new ProfilEtudiant().getProfilEtudiantByIdEtudiant(idetudiant);

        // Récupérer toutes les offres de stage de la base de données
        List<OffreStage> offresStage = new ArrayList<OffreStage>();
        offresStage = this.getAllOffreStage();

        // Filtrer les offres de stage en fonction du profil étudiant
        List<OffreStage> offresStageFiltrees = new ArrayList<OffreStage>();
                for (OffreStage offreStage : offresStage) {
            if (offreStage.getPoste().equals(profilEtudiant.getProfil())
                    && offreStage.getExigences().contains(profilEtudiant.getCompetences()))
            {
                offresStageFiltrees.add(offreStage);
            }
        }

        // Retourner les offres de stage filtrées
        return offresStageFiltrees;
    }


}
