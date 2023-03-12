package com.my.ws_student.models.ressource;


import com.my.ws_student.utils.has.DBAnnotation;

@DBAnnotation.Table(name = "repertoire")
public class Repertoire extends Ressource {
    @DBAnnotation.Id
    public int id_repertoire;

    public Repertoire(){
        this.type="dossier";
    }

    public int getId_repertoire() {
        return id_repertoire;
    }

    public void setId_repertoire(int id_repertoire) {
        this.id_repertoire = id_repertoire;
    }

    @Override
    public String toString() {
        return "Repertoire{" +
                "id_repertoire=" + id_repertoire +
                ", id_ressource=" + id_ressource +
                ", nom='" + nom + '\'' +
                ", date_creation=" + date_creation +
                ", id_etudiant=" + id_etudiant +
                ", taille=" + taille +
                ", type='" + type + '\'' +
                '}';
    }
}
