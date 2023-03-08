package com.example.demo.models;

import com.example.demo.utils.Connex.Connexion;
import com.example.demo.utils.DAO.ObjectBDD;
import com.example.demo.utils.inter.ForeignKeyAnnotation;
import com.example.demo.utils.inter.IdAnnotation;
import com.example.demo.utils.inter.KeyAnnotation;
import com.example.demo.utils.inter.TableAnnotation;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;


public class ProfilEtudiant {

    private int idprofiletudiant;

    private int idEtudiant;

    private String domaine_etude;

    private String profil;

    private String competences;

    public ProfilEtudiant() {
    }

    private int experience;

    public ProfilEtudiant(int idprofiletudiant, int idEtudiant, String domaine_etude, String profil, String competences, int experience) {
        this.idprofiletudiant = idprofiletudiant;
        this.idEtudiant = idEtudiant;
        this.domaine_etude = domaine_etude;
        this.profil = profil;
        this.competences = competences;
        this.experience = experience;
    }

    public int getIdprofiletudiant() {
        return idprofiletudiant;
    }

    public void setIdprofiletudiant(int idprofiletudiant) {
        this.idprofiletudiant = idprofiletudiant;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getDomaine_etude() {
        return domaine_etude;
    }

    public void setDomaine_etude(String domaine_etude) {
        this.domaine_etude = domaine_etude;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String getCompetences() {
        return competences;
    }

    public void setCompetences(String competences) {
        this.competences = competences;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    // Méthode pour insérer un profil étudiant dans la base de données
    public static void insererProfilEtudiant(int idEtudiant, String domaineEtude, String profil, String competences, int experience) throws Exception {
        Connection con = Connexion.getConnection();
        String sql = "INSERT INTO ProfilEtudiant (idEtudiant, domaine_etude, profil, competences, experience) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idEtudiant);
        ps.setString(2, domaineEtude);
        ps.setString(3, profil);
        ps.setString(4, competences);
        ps.setInt(5, experience);
        try {
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new Exception("misy erreur");
        } finally {
           con.close();
        }
    }

    // Méthode pour récupérer les profils étudiants en fonction de l'ID d'un étudiant
    public ProfilEtudiant getProfilEtudiantByIdEtudiant(int idEtudiant) throws Exception {
        Connection con = Connexion.getConnection();
        String sql = "SELECT * FROM ProfilEtudiant WHERE idEtudiant=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idEtudiant);
        ResultSet rs = ps.executeQuery();
        ProfilEtudiant profilEtudiant = null;
        if (rs.next()) {
            int idProfilEtudiant = rs.getInt("idprofiletudiant");
            String domaineEtude = rs.getString("domaine_etude");
            String profil = rs.getString("profil");
            String competences = rs.getString("competences");
            int experience = rs.getInt("experience");
            profilEtudiant = new ProfilEtudiant(idProfilEtudiant, idEtudiant, domaineEtude, profil, competences, experience);
        }
        con.close();
        return profilEtudiant;
    }

}
