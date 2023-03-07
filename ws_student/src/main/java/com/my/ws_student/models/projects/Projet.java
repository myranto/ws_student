package com.my.ws_student.models.projects;

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
public class Projet extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation(name = "idProjet")
    private int idProjet;
    @KeyAnnotation
    private int EtudiantidEtudiant;
    @KeyAnnotation
    private String NomProjet;
    @KeyAnnotation
    private Timestamp DateDebut;
    @KeyAnnotation
    private String DescriptionProjet;
    @KeyAnnotation
    private Timestamp DateFin;
    @ForeignKeyAnnotation(name = "EtudiantidEtudiant",references = "idEtudiant")
    private Etudiant etudiant;
    private ArrayList<Tache> list_tache = null;
    private int estimationProject = -1;

    public int getEstimationProject() throws Exception {
        if (estimationProject == -1) {
            for (Tache e:getList_tache()) {
                estimationProject+=e.getDurree();
            }
        }
        return estimationProject;
    }

    public void setEstimationProject(int estimationProject) {
        this.estimationProject = estimationProject;
    }

    public ArrayList<Tache> getList_tache() throws Exception {
        if (list_tache == null){
            list_tache = new Tache().SelectByIdProject(getIdProjet());
        }
        return list_tache;
    }

    public void setList_tache(ArrayList<Tache> list_tache) {
        this.list_tache = list_tache;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Projet() {
    }

    public Projet(int etudiantidEtudiant, String nomProjet, Timestamp dateDebut, String descriptionProjet, Timestamp dateFin) {
        EtudiantidEtudiant = etudiantidEtudiant;
        NomProjet = nomProjet;
        DateDebut = dateDebut;
        DescriptionProjet = descriptionProjet;
        DateFin = dateFin;
    }

    public Projet(int idProjet) {
        this.idProjet = idProjet;
    }

    public Projet(int idProjet, int etudiantidEtudiant, String nomProjet, Timestamp dateDebut, String descriptionProjet, Timestamp dateFin) {
        this.idProjet = idProjet;
        EtudiantidEtudiant = etudiantidEtudiant;
        NomProjet = nomProjet;
        DateDebut = dateDebut;
        DescriptionProjet = descriptionProjet;
        DateFin = dateFin;
    }

    public int getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(int idProjet) {
        this.idProjet = idProjet;
    }

    public int getEtudiantidEtudiant() {
        return EtudiantidEtudiant;
    }

    public void setEtudiantidEtudiant(int etudiantidEtudiant) {
        EtudiantidEtudiant = etudiantidEtudiant;
    }

    public String getNomProjet() {
        return NomProjet;
    }

    public void setNomProjet(String nomProjet) {
        NomProjet = nomProjet;
    }

    public Timestamp getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        DateDebut = dateDebut;
    }

    public String getDescriptionProjet() {
        return DescriptionProjet;
    }

    public void setDescriptionProjet(String descriptionProjet) {
        DescriptionProjet = descriptionProjet;
    }

    public Timestamp getDateFin() {
        return DateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        DateFin = dateFin;
    }

    public Projet findById(int id) throws Exception {
        Projet e =  findById(Connexion.getConnection(),String.valueOf(id));
//        init(e);
        return e;
    }
    public Projet save() throws Exception {
        Projet e = super.saveAll(Connexion.getConnection());
//        init(e);
        return e;
    }
    public ArrayList<Projet> SelectAll() throws Exception {
        ArrayList<Projet> list =  super.SelectAll(Connexion.getConnection());
//        initTable(list);
        return list;
    }
    public ArrayList<Projet> SelectAllByQuerry(String sql) throws Exception {
        ArrayList<Projet> list = SelectAllByQuery(Connexion.getConnection(),sql);
        return list;
    }
    public void update() throws Exception {
        Projet pr = findById(getIdProjet());
        pr = new Projet(pr.getIdProjet(),getEtudiantidEtudiant(), getNomProjet(),getDateDebut(), getDescriptionProjet(), getDateFin());
        pr.updateById(Connexion.getConnection());
    }
}
