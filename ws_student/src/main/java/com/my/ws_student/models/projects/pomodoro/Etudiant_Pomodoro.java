package com.my.ws_student.models.projects.pomodoro;

import com.my.ws_student.utils.Connex.Connexion;
import com.my.ws_student.utils.DAO.ObjectBDD;
import com.my.ws_student.utils.inter.KeyAnnotation;
import com.my.ws_student.utils.inter.TableAnnotation;

import java.sql.Timestamp;
import java.util.ArrayList;

@TableAnnotation
public class Etudiant_Pomodoro extends ObjectBDD {
    @KeyAnnotation
    private int EtudiantidEtudiant;
    @KeyAnnotation
    private int PomodoroidPomodoro;
    @KeyAnnotation
    private int SousTacheidSousTache;
    @KeyAnnotation
    private Timestamp DateFin;

    public Etudiant_Pomodoro() {
    }

    public Etudiant_Pomodoro(int etudiantidEtudiant, int pomodoroidPomodoro, int sousTacheidSousTache, Timestamp dateFin) {
        EtudiantidEtudiant = etudiantidEtudiant;
        PomodoroidPomodoro = pomodoroidPomodoro;
        SousTacheidSousTache = sousTacheidSousTache;
        DateFin = dateFin;
    }

    public int getEtudiantidEtudiant() {
        return EtudiantidEtudiant;
    }

    public void setEtudiantidEtudiant(int etudiantidEtudiant) {
        EtudiantidEtudiant = etudiantidEtudiant;
    }

    public int getPomodoroidPomodoro() {
        return PomodoroidPomodoro;
    }

    public void setPomodoroidPomodoro(int pomodoroidPomodoro) {
        PomodoroidPomodoro = pomodoroidPomodoro;
    }

    public int getSousTacheidSousTache() {
        return SousTacheidSousTache;
    }

    public void setSousTacheidSousTache(int sousTacheidSousTache) {
        SousTacheidSousTache = sousTacheidSousTache;
    }

    public Timestamp getDateFin() {
        return DateFin;
    }

    public void setDateFin(Timestamp dateFin) {
        DateFin = dateFin;
    }
    public Etudiant_Pomodoro save() throws Exception {
        return super.saveAll(Connexion.getConnection());
    }
    public ArrayList<Etudiant_Pomodoro> SelectAll() throws Exception {
        return super.SelectAll(Connexion.getConnection());
    }
    public ArrayList<Etudiant_Pomodoro> SelectAllByQuerry(String sql) throws Exception {
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
}
