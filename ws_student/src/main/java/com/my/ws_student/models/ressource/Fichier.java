package com.my.ws_student.models.ressource;


import com.my.ws_student.utils.has.DBAnnotation;

@DBAnnotation.Table(name="fichier")
public class Fichier extends Ressource {
    @DBAnnotation.Id
    int id_fichier;
    @DBAnnotation.Column
    String value;
    @DBAnnotation.Column
    int id_repertoire;



    @DBAnnotation.FK(id="id_repertoire")
    Repertoire repertoire;

    public Fichier(){
        this.type="txt";
    }

    public int getId_fichier() {
        return id_fichier;
    }

    public void setId_fichier(int id_fichier) {
        this.id_fichier = id_fichier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getId_repertoire() {
        return id_repertoire;
    }

    public void setId_repertoire(int id_repertoire) {
        this.id_repertoire = id_repertoire;
    }

    public Repertoire getRepertoire() {
        return repertoire;
    }

    public void setRepertoire(Repertoire repertoire) {
        this.repertoire = repertoire;
    }

    @Override
    public String toString() {
        return "Fichier{" +
                "id_fichier=" + id_fichier +
                ", value='" + value + '\'' +
                ", id_repertoire=" + id_repertoire +
                ", repertoire=" + repertoire +
                ", id_ressource=" + id_ressource +
                ", nom='" + nom + '\'' +
                ", date_creation=" + date_creation +
                ", id_etudiant=" + id_etudiant +
                ", taille=" + taille +
                ", type='" + type + '\'' +
                '}';
    }
}
