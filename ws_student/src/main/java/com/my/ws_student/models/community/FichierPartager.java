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
    private int CommentaireidCommentaire;
    @KeyAnnotation
    private String FichierPartager;
    @ForeignKeyAnnotation(name = "CommentaireidCommentaire",references = "idCommentaire")
    private Commentaire commentaire;

    public Commentaire getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
    }

    public int getCommentaireidCommentaire() {
        return CommentaireidCommentaire;
    }

    public void setCommentaireidCommentaire(int commentaireidCommentaire) {
        CommentaireidCommentaire = commentaireidCommentaire;
    }

    public String getFichierPartager() {
        return FichierPartager;
    }

    public void setFichierPartager(String fichierPartager) {
        FichierPartager = fichierPartager;
    }

    public FichierPartager(int commentaireidCommentaire, String fichierPartager) {
        CommentaireidCommentaire = commentaireidCommentaire;
        FichierPartager = fichierPartager;
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
}
