package com.my.ws_student.models.param;

import com.my.ws_student.models.community.Commentaire;
import com.my.ws_student.models.community.FichierPartager;

public class FichierComs {
    private Commentaire commentaire;
    private FichierPartager file;

    public FichierComs() {
    }

    public FichierComs(Commentaire commentaire, FichierPartager file) {
        this.commentaire = commentaire;
        this.file = file;
    }

    public Commentaire getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(Commentaire commentaire) {
        this.commentaire = commentaire;
    }

    public FichierPartager getFile() {
        return file;
    }

    public void setFile(FichierPartager file) {
        this.file = file;
    }
}
