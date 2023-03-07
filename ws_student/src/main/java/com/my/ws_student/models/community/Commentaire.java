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
public class Commentaire extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation(name = "idCommentaire")
    private int idCommentaire;
    @KeyAnnotation
    private int PublicationidPublication;
    @KeyAnnotation
    private String texte;
    @KeyAnnotation
    private Timestamp DateCommentaire;
    @KeyAnnotation
    private int EtudiantidEtudiant;
//    @ForeignKeyAnnotation(name = "PublicationidPublication",references = "idPublication")
//    private Publication publication;
    @ForeignKeyAnnotation(name = "EtudiantidEtudiant",references = "idEtudiant")
    private Etudiant etudiant;

//    public Publication getPublication() {
//        return publication;
//    }
//
//    public void setPublication(Publication publication) {
//        this.publication = publication;
//    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
    private ArrayList<Note_coms> list_note_coms = null;

    public ArrayList<Note_coms> getList_note_coms() throws Exception {
        if (list_note_coms == null) {
            list_note_coms = new Note_coms().SelectAllByComs(getIdCommentaire());
        }
        return list_note_coms;
    }

    public void setList_note_coms(ArrayList<Note_coms> list_note_coms) {
        this.list_note_coms = list_note_coms;
    }

    public Commentaire() {
    }

    public Commentaire(int publicationidPublication, String texte, Timestamp dateCommentaire, int etudiantidEtudiant) {
        PublicationidPublication = publicationidPublication;
        this.texte = texte;
        DateCommentaire = dateCommentaire;
        EtudiantidEtudiant = etudiantidEtudiant;
    }

    public Commentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public Commentaire(int idCommentaire, int publicationidPublication, String texte, Timestamp dateCommentaire, int etudiantidEtudiant) {
        this.idCommentaire = idCommentaire;
        PublicationidPublication = publicationidPublication;
        this.texte = texte;
        DateCommentaire = dateCommentaire;
        EtudiantidEtudiant = etudiantidEtudiant;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) throws Exception {
        this.idCommentaire = idCommentaire;
        list_note_coms = getList_note_coms();
    }

    public int getPublicationidPublication() {
        return PublicationidPublication;
    }

    public void setPublicationidPublication(int publicationidPublication) {
        PublicationidPublication = publicationidPublication;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Timestamp getDateCommentaire() {
        return DateCommentaire;
    }

    public void setDateCommentaire(Timestamp dateCommentaire) {
        DateCommentaire = dateCommentaire;
    }

    public int getEtudiantidEtudiant() {
        return EtudiantidEtudiant;
    }

    public void setEtudiantidEtudiant(int etudiantidEtudiant) {
        EtudiantidEtudiant = etudiantidEtudiant;
    }

    public Commentaire findById(int id) throws Exception {
        Commentaire e = findById(Connexion.getConnection(),String.valueOf(id));
//        init(e);
        return e;
    }
    public Commentaire save() throws Exception {
        Commentaire e = super.saveAll(Connexion.getConnection());
//        init(e);
        return e;
    }
    public ArrayList<Commentaire> SelectAll() throws Exception {
        ArrayList<Commentaire> list = super.SelectAll(Connexion.getConnection());
//        initTable(list);
        return list;
    }
    public ArrayList<Commentaire> SelectAllByIdpu(int idpub) throws Exception {
        String sql = "select * from "+getNomTable()+" where PublicationidPublication="+idpub;
        ArrayList<Commentaire> list = SelectAllByQuerry(sql);
//        initTable(list);
        return list;
    }
    public ArrayList<Commentaire> SelectAllByQuerry(String sql) throws Exception {
        ArrayList<Commentaire> list =SelectAllByQuery(Connexion.getConnection(),sql);
//        initTable(list);
        return list;
    }


}
