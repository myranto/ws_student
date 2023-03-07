package com.my.ws_student.models.community;

import com.my.ws_student.models.Etudiant;
import com.my.ws_student.utils.Connex.Connexion;
import com.my.ws_student.utils.DAO.ObjectBDD;
import com.my.ws_student.utils.inter.ForeignKeyAnnotation;
import com.my.ws_student.utils.inter.IdAnnotation;
import com.my.ws_student.utils.inter.KeyAnnotation;
import com.my.ws_student.utils.inter.TableAnnotation;

import java.sql.Timestamp;
import java.util.ArrayList;

@TableAnnotation
public class Publication extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation(name = "idPublication")
    private int idPublication;
    @KeyAnnotation
    private String texte;
    @KeyAnnotation
    private Timestamp DatePublication;
    @KeyAnnotation
    private int EtudiantidEtudiant;
    @ForeignKeyAnnotation(name = "EtudiantidEtudiant",references = "idEtudiant")
    private Etudiant etudiant;

    public Etudiant getEtudiant() {
        return etudiant;
    }
//
    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    private ArrayList<Commentaire> list_coms = null;

    public ArrayList<Commentaire> getList_coms() throws Exception {
        if (list_coms == null) {
            list_coms = new Commentaire().SelectAllByIdpu(getIdPublication());
        }
        return list_coms;
    }

    public void setList_coms(ArrayList<Commentaire> list_coms) {
        this.list_coms = list_coms;
    }

    public Publication() {
    }

    public Publication(String texte, Timestamp datePublication, int etudiantidEtudiant) {
        this.texte = texte;
        DatePublication = datePublication;
        EtudiantidEtudiant = etudiantidEtudiant;
    }

    public Publication(int idPublication) {
        this.idPublication = idPublication;
    }

    public Publication(int idPublication, String texte, Timestamp datePublication, int etudiantidEtudiant) {
        this.idPublication = idPublication;
        this.texte = texte;
        DatePublication = datePublication;
        EtudiantidEtudiant = etudiantidEtudiant;
    }

    public int getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(int idPublication) throws Exception {
        this.idPublication = idPublication;
        list_coms = getList_coms();
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Timestamp getDatePublication() {
        return DatePublication;
    }

    public void setDatePublication(Timestamp datePublication) {
        DatePublication = datePublication;
    }

    public int getEtudiantidEtudiant() {
        return EtudiantidEtudiant;
    }

    public void setEtudiantidEtudiant(int etudiantidEtudiant) {
        EtudiantidEtudiant = etudiantidEtudiant;
    }
    public Publication findById(int id) throws Exception {
        Publication e = findById(Connexion.getConnection(),String.valueOf(id));
        return e;
    }
    public Publication save() throws Exception {
        Publication e = super.saveAll(Connexion.getConnection());
        return e;
    }
    public ArrayList<Publication> SelectAll() throws Exception {
        ArrayList<Publication> list= super.SelectAll(Connexion.getConnection());
        return list;
    }
    public ArrayList<Publication> SelectAllByQuerry(String sql) throws Exception {
        ArrayList<Publication> list = SelectAllByQuery(Connexion.getConnection(),sql);
        return list;
    }

}
