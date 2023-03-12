package com.my.ws_student.models.projects;

import com.my.ws_student.utils.Connex.Connexion;
import com.my.ws_student.utils.DAO.ObjectBDD;
import com.my.ws_student.utils.inter.IdAnnotation;
import com.my.ws_student.utils.inter.KeyAnnotation;
import com.my.ws_student.utils.inter.TableAnnotation;

import java.sql.Timestamp;
import java.util.ArrayList;

@TableAnnotation
public class SousTache extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation(name = "idSousTache")
    private int idSousTache;
    @KeyAnnotation

    private String titresoustache;
    @KeyAnnotation
    private String description;
    @KeyAnnotation
    private Timestamp date_sous_tache;
    @KeyAnnotation
    private int estimation;
    @KeyAnnotation
    private int TempsPasse=0;
    @KeyAnnotation
    private int priorite;
    @KeyAnnotation
    private int etat=0;
    @KeyAnnotation
    private int planningidtache;
//    @ForeignKeyAnnotation(name = "PlanningidTache",references = "idTache")
//    private Tache tache;
//
//    public Tache getTache() {
//        return tache;
//    }
//
//    public void setTache(Tache tache) {
//        this.tache = tache;
//    }

    public SousTache() {
    }

    public SousTache(String titresoustache, String description, Timestamp date_sous_tache, int estimation, int tempsPasse, int priorite, int etat, int planningidtache) {
        this.titresoustache = titresoustache;
        this.description = description;
        this.date_sous_tache = date_sous_tache;
        this.estimation = estimation;
        TempsPasse = tempsPasse;
        this.priorite = priorite;
        this.etat = etat;
        this.planningidtache = planningidtache;
    }

    public SousTache(int idSousTache) {
        this.idSousTache = idSousTache;
    }

    public SousTache(int idSousTache, String titresoustache, String description, Timestamp date_sous_tache, int estimation, int tempsPasse, int priorite, int etat, int planningidtache) {
        this.idSousTache = idSousTache;
        this.titresoustache = titresoustache;
        this.description = description;
        this.date_sous_tache = date_sous_tache;
        this.estimation = estimation;
        TempsPasse = tempsPasse;
        this.priorite = priorite;
        this.etat = etat;
        this.planningidtache = planningidtache;
    }

    public int getIdSousTache() {
        return idSousTache;
    }

    public void setIdSousTache(int idSousTache) {
        this.idSousTache = idSousTache;
    }

    public String getTitresoustache() {
        return titresoustache;
    }

    public void setTitresoustache(String titresoustache) {
        this.titresoustache = titresoustache;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDate_sous_tache() {
        return date_sous_tache;
    }

    public void setDate_sous_tache(Timestamp date_sous_tache) {
        this.date_sous_tache = date_sous_tache;
    }

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public int getTempsPasse() {
        return TempsPasse;
    }

    public void setTempsPasse(int tempsPasse) {
        TempsPasse = tempsPasse;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getPlanningidtache() {
        return planningidtache;
    }

    public void setPlanningidtache(int planningidtache) {
        this.planningidtache = planningidtache;
    }

    public SousTache findById(int id) throws Exception {
        return findById(Connexion.getConnection(),String.valueOf(id));
    }
    public SousTache save() throws Exception {
        return super.saveAll(Connexion.getConnection());
    }
    public ArrayList<SousTache> SelectAll() throws Exception {
        return super.SelectAll(Connexion.getConnection());
    }
    public ArrayList<SousTache> SelectAllByQuerry(String sql) throws Exception {
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
    public ArrayList<SousTache> SelectByIdTache(int idTache) throws Exception {
        String sql = "select * from "+getNomTable()+" where PlanningidTache="+idTache+" order by priorite desc";
        return SelectAllByQuerry(sql);
    }
    public int CalculateTime(Timestamp tocalculate) {
        Timestamp current = new Timestamp(System.currentTimeMillis());
        long t = tocalculate.getTime() - current.getTime();
        return (int) (t / 60000);
    }
//    public SousTache changeEtat(int etat,int idSousTache,int min) throws Exception {
//        SousTache e = new SousTache().findById(idSousTache);
//        e.setEtat(etat);
////        int min = e.CalculateTime(e.getDate_sous_tache());
//        e.setTempsPasse(min);
//        e.updateById(Connexion.getConnection());
//        return e;
//    }
    public SousTache changeEtat(int etat,int idSousTache) throws Exception {
        SousTache e = new SousTache().findById(idSousTache);
        e.setEtat(etat);
//        if (etat==1){
            int min = e.CalculateTime(e.getDate_sous_tache());
            e.setTempsPasse(Math.abs(min));
//        }
        e.updateById(Connexion.getConnection());
        return e;
    }
    public void update() throws Exception {
        SousTache e = findById(getIdSousTache());
        e  = new SousTache(e.getIdSousTache(), getTitresoustache(), getDescription(), getDate_sous_tache(), getEstimation(), getTempsPasse(), getPriorite(), getEtat(), getPlanningidtache());
        e.updateById(Connexion.getConnection());
    }
}
