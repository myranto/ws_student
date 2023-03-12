package com.my.ws_student.models.community;

import com.my.ws_student.utils.Connex.Connexion;
import com.my.ws_student.utils.DAO.ObjectBDD;
import com.my.ws_student.utils.inter.ForeignKeyAnnotation;
import com.my.ws_student.utils.inter.KeyAnnotation;
import com.my.ws_student.utils.inter.TableAnnotation;

import java.util.ArrayList;

@TableAnnotation
public class FichierPartager extends ObjectBDD {
    @KeyAnnotation
    private int commentaireidCommentaire;
    @KeyAnnotation
    private String fichierPartager;
//    @ForeignKeyAnnotation(name = "CommentaireidCommentaire",references = "idCommentaire")
//    private Commentaire commentaire;
//
//    public Commentaire getCommentaire() {
//        return commentaire;
//    }
//
//    public void setCommentaire(Commentaire commentaire) {
//        this.commentaire = commentaire;
//    }

    public int getCommentaireidCommentaire() {
        return commentaireidCommentaire;
    }

    public void setCommentaireidCommentaire(int commentaireidCommentaire) {
        this.commentaireidCommentaire = commentaireidCommentaire;
    }

    public String getFichierPartager() {
        return fichierPartager;
    }

    public FichierPartager() {
    }

    public void setFichierPartager(String fichierPartager) {
        this.fichierPartager = fichierPartager;
    }

    public FichierPartager(int commentaireidCommentaire, String fichierPartager) {
        this.commentaireidCommentaire = commentaireidCommentaire;
        this.fichierPartager = fichierPartager;
    }
    public FichierPartager save() throws Exception {
        return super.saveAll(Connexion.getConnection());
    }
    public ArrayList<FichierPartager> SelectAll() throws Exception {
        return super.SelectAll(Connexion.getConnection());
    }
    public ArrayList<FichierPartager> SelectAllByQuerry(String sql) throws Exception {
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
    public ArrayList<FichierPartager> SelectAllByCommentaire(int id) throws Exception {
        String sql = "select * from "+getNomTable()+" where commentaireidCommentaire="+id+" order by commentaireidCommentaire asc";
        return SelectAllByQuerry(sql);
    }
}
