package com.my.ws_student.models.projects;

import com.my.ws_student.utils.Connex.Connexion;
import com.my.ws_student.utils.DAO.ObjectBDD;
import com.my.ws_student.utils.inter.ForeignKeyAnnotation;
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
    private int TitreSousTache;
    @KeyAnnotation
    private int Description;
    @KeyAnnotation
    private Timestamp Date_sous_tache;
    @KeyAnnotation
    private int estimation;
    @KeyAnnotation
    private int TempsPasse;
    @KeyAnnotation
    private int priorite;
    @KeyAnnotation
    private int etat;
    @KeyAnnotation
    private int PlanningidTache;
    @ForeignKeyAnnotation(name = "PlanningidTache",references = "idTache")
    private Tache tache;

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public SousTache() {
    }

    public SousTache(int titreSousTache, int description, Timestamp date_sous_tache, int estimation, int tempsPasse, int priorite, int etat, int planningidTache) {
        TitreSousTache = titreSousTache;
        Description = description;
        Date_sous_tache = date_sous_tache;
        this.estimation = estimation;
        TempsPasse = tempsPasse;
        this.priorite = priorite;
        this.etat = etat;
        PlanningidTache = planningidTache;
    }

    public SousTache(int idSousTache) {
        this.idSousTache = idSousTache;
    }

    public SousTache(int idSousTache, int titreSousTache, int description, Timestamp date_sous_tache, int estimation, int tempsPasse, int priorite, int etat, int planningidTache) {
        this.idSousTache = idSousTache;
        TitreSousTache = titreSousTache;
        Description = description;
        Date_sous_tache = date_sous_tache;
        this.estimation = estimation;
        TempsPasse = tempsPasse;
        this.priorite = priorite;
        this.etat = etat;
        PlanningidTache = planningidTache;
    }

    public int getIdSousTache() {
        return idSousTache;
    }

    public void setIdSousTache(int idSousTache) {
        this.idSousTache = idSousTache;
    }

    public int getTitreSousTache() {
        return TitreSousTache;
    }

    public void setTitreSousTache(int titreSousTache) {
        TitreSousTache = titreSousTache;
    }

    public int getDescription() {
        return Description;
    }

    public void setDescription(int description) {
        Description = description;
    }

    public Timestamp getDate_sous_tache() {
        return Date_sous_tache;
    }

    public void setDate_sous_tache(Timestamp date_sous_tache) {
        Date_sous_tache = date_sous_tache;
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

    public int getPlanningidTache() {
        return PlanningidTache;
    }

    public void setPlanningidTache(int planningidTache) {
        PlanningidTache = planningidTache;
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
    public int CalculateTime(Timestamp tocalculate){
        Timestamp current = new Timestamp(System.currentTimeMillis());
        long t = current.getTime()-tocalculate.getTime();
//        60 000=60min
        int h = (int) (t/60000);//en h
        return h*60;
    }
    public SousTache changeEtat(int etat,int idSousTache) throws Exception {
        SousTache e = new SousTache().findById(idSousTache);
        e.setEtat(etat);
        int min = e.CalculateTime(e.getDate_sous_tache());
        e.setTempsPasse(min);
        e.updateById(Connexion.getConnection());
        return e;
    }
}
