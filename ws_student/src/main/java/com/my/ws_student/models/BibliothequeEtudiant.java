package com.my.ws_student.models;

import com.my.ws_student.utils.Connex.Connexion;
import com.my.ws_student.utils.DAO.ObjectBDD;
import com.my.ws_student.utils.inter.ForeignKeyAnnotation;
import com.my.ws_student.utils.inter.IdAnnotation;
import com.my.ws_student.utils.inter.KeyAnnotation;
import com.my.ws_student.utils.inter.TableAnnotation;

import java.util.ArrayList;

@TableAnnotation
public class BibliothequeEtudiant extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation(name = "idBibliothequeEtudiant")
    private int idBibliothequeEtudiant;
    @KeyAnnotation
    private int EtudiantidEtudiant;
    @KeyAnnotation
    private String NomDocument;
    @KeyAnnotation
    private String TypeDocument;
    @KeyAnnotation
    private String CheminDocument;
    @ForeignKeyAnnotation(name = "EtudiantidEtudiant",references = "idEtudiant")
    private Etudiant etudiant;

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public BibliothequeEtudiant(int etudiantidEtudiant, String nomDocument, String typeDocument, String cheminDocument) {
        EtudiantidEtudiant = etudiantidEtudiant;
        NomDocument = nomDocument;
        TypeDocument = typeDocument;
        CheminDocument = cheminDocument;
    }

    public BibliothequeEtudiant(int idBibliothequeEtudiant) {
        this.idBibliothequeEtudiant = idBibliothequeEtudiant;
    }

    public BibliothequeEtudiant(int idBibliothequeEtudiant, int etudiantidEtudiant, String nomDocument, String typeDocument, String cheminDocument) {
        this.idBibliothequeEtudiant = idBibliothequeEtudiant;
        EtudiantidEtudiant = etudiantidEtudiant;
        NomDocument = nomDocument;
        TypeDocument = typeDocument;
        CheminDocument = cheminDocument;
    }

    public BibliothequeEtudiant() {
    }

    public int getIdBibliothequeEtudiant() {
        return idBibliothequeEtudiant;
    }

    public void setIdBibliothequeEtudiant(int idBibliothequeEtudiant) {
        this.idBibliothequeEtudiant = idBibliothequeEtudiant;
    }

    public int getEtudiantidEtudiant() {
        return EtudiantidEtudiant;
    }

    public void setEtudiantidEtudiant(int etudiantidEtudiant) {
        EtudiantidEtudiant = etudiantidEtudiant;
    }

    public String getNomDocument() {
        return NomDocument;
    }

    public void setNomDocument(String nomDocument) {
        NomDocument = nomDocument;
    }

    public String getTypeDocument() {
        return TypeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        TypeDocument = typeDocument;
    }

    public String getCheminDocument() {
        return CheminDocument;
    }

    public void setCheminDocument(String cheminDocument) {
        CheminDocument = cheminDocument;
    }
    public BibliothequeEtudiant findById(int id) throws Exception {
        return findById(Connexion.getConnection(),String.valueOf(id));
    }
    public BibliothequeEtudiant save() throws Exception {
        return super.saveAll(Connexion.getConnection());
    }
    public ArrayList<BibliothequeEtudiant> SelectAll() throws Exception {
        return super.SelectAll(Connexion.getConnection());
    }
    public ArrayList<BibliothequeEtudiant> SelectAllByIdEtudiant(int idEtu) throws Exception {
        String sql = "select * from "+getNomTable()+" where EtudiantidEtudiant="+idEtu;
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
    public ArrayList<BibliothequeEtudiant> SelectAllByQuerry(String sql) throws Exception {
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
}
