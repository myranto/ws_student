package com.my.ws_student.models;

import com.my.ws_student.utils.Connex.Connexion;
import com.my.ws_student.utils.DAO.ObjectBDD;
import com.my.ws_student.utils.inter.ForeignKeyAnnotation;
import com.my.ws_student.utils.inter.IdAnnotation;
import com.my.ws_student.utils.inter.KeyAnnotation;
import com.my.ws_student.utils.inter.TableAnnotation;

import java.util.ArrayList;

@TableAnnotation
public class PriseDeNotes extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation(name = "idPriseDeNotes")
    private int idPriseDeNotes;
    @KeyAnnotation
    private int EtudiantidEtudiant;
    @KeyAnnotation
    private String TitreNotes;
    @KeyAnnotation
    private String ContenuNotes;
    @KeyAnnotation
    private String image;
    @KeyAnnotation
    private String mots_cles;
    @ForeignKeyAnnotation(name = "EtudiantidEtudiant",references = "idEtudiant")
    private Etudiant etudiant;

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
    public PriseDeNotes() {
    }


    public PriseDeNotes(int idPriseDeNotes) {
        this.idPriseDeNotes = idPriseDeNotes;
    }

    public PriseDeNotes(int etudiantidEtudiant, String titreNotes, String contenuNotes, String image, String mots_cles) {
        EtudiantidEtudiant = etudiantidEtudiant;
        TitreNotes = titreNotes;
        ContenuNotes = contenuNotes;
        this.image = image;
        this.mots_cles = mots_cles;
    }

    public PriseDeNotes(int idPriseDeNotes, int etudiantidEtudiant, String titreNotes, String contenuNotes, String image, String mots_cles) {
        this.idPriseDeNotes = idPriseDeNotes;
        EtudiantidEtudiant = etudiantidEtudiant;
        TitreNotes = titreNotes;
        ContenuNotes = contenuNotes;
        this.image = image;
        this.mots_cles = mots_cles;
    }

    public int getIdPriseDeNotes() {
        return idPriseDeNotes;
    }

    public void setIdPriseDeNotes(int idPriseDeNotes) {
        this.idPriseDeNotes = idPriseDeNotes;
    }

    public int getEtudiantidEtudiant() {
        return EtudiantidEtudiant;
    }

    public void setEtudiantidEtudiant(int etudiantidEtudiant) {
        EtudiantidEtudiant = etudiantidEtudiant;
    }

    public String getTitreNotes() {
        return TitreNotes;
    }

    public void setTitreNotes(String titreNotes) {
        TitreNotes = titreNotes;
    }

    public String getContenuNotes() {
        return ContenuNotes;
    }

    public void setContenuNotes(String contenuNotes) {
        ContenuNotes = contenuNotes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMots_cles() {
        return mots_cles;
    }

    public void setMots_cles(String mots_cles) {
        this.mots_cles = mots_cles;
    }

    public PriseDeNotes findById(int id) throws Exception {
        return findById(Connexion.getConnection(),String.valueOf(id));
    }
    public PriseDeNotes save() throws Exception {
        return super.saveAll(Connexion.getConnection());
    }
    public ArrayList<PriseDeNotes> SelectAll() throws Exception {
        return super.SelectAll(Connexion.getConnection());
    }
    public ArrayList<PriseDeNotes> SelectAllByQuerry(String sql) throws Exception {
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
}
