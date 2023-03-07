package com.my.ws_student.models.community;

import com.my.ws_student.models.Etudiant;
import com.my.ws_student.utils.Connex.Connexion;
import com.my.ws_student.utils.DAO.ObjectBDD;
import com.my.ws_student.utils.inter.ForeignKeyAnnotation;
import com.my.ws_student.utils.inter.KeyAnnotation;
import com.my.ws_student.utils.inter.TableAnnotation;

import java.util.ArrayList;

@TableAnnotation
public class Note_coms extends ObjectBDD {
    @KeyAnnotation
    private int idCommentaire;
    @KeyAnnotation
    private int notes;
    @KeyAnnotation
    private int idEtudiant;
    @ForeignKeyAnnotation(name = "idEtudiant",references = "idEtudiant")
    private Etudiant etudiant;
    @ForeignKeyAnnotation(name = "idCommentaire",references = "idCommentaire")
    private Commentaire commentaire;

    public Commentaire getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
    }
    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
    public Note_coms() {
    }

    public Note_coms(int idCommentaire, int notes, int idEtudiant) {
        this.idCommentaire = idCommentaire;
        this.notes = notes;
        this.idEtudiant = idEtudiant;
    }

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public int getNotes() {
        return notes;
    }

    public void setNotes(int notes) {
        this.notes = notes;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }
    public Note_coms save() throws Exception {
        return super.saveAll(Connexion.getConnection());
    }
    public ArrayList<Note_coms> SelectAll() throws Exception {
        return super.SelectAll(Connexion.getConnection());
    }
    public ArrayList<Note_coms> SelectAllByQuerry(String sql) throws Exception {
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
    public ArrayList<Note_coms> SelectAllByComs(int idcom) throws Exception {
        String Sql = "select * from "+getNomTable()+" where idCommentaire="+idcom;
        return SelectAllByQuery(Connexion.getConnection(),Sql);
    }
}
