package com.my.ws_student.models.projects.pomodoro;

import com.my.ws_student.utils.Connex.Connexion;
import com.my.ws_student.utils.DAO.ObjectBDD;
import com.my.ws_student.utils.inter.IdAnnotation;
import com.my.ws_student.utils.inter.KeyAnnotation;
import com.my.ws_student.utils.inter.TableAnnotation;

import java.sql.Timestamp;
import java.util.ArrayList;

@TableAnnotation
public class Pomodoro extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation(name = "idPomodoro")
    private int idPomodoro;
    @KeyAnnotation
    private Timestamp DateDebut = new Timestamp(System.currentTimeMillis());
    @KeyAnnotation
    private int durree;
    @KeyAnnotation
    private int pause;

    public Pomodoro() {
    }

    public Pomodoro(int idPomodoro, Timestamp dateDebut, int durree, int pause) {
        this.idPomodoro = idPomodoro;
        DateDebut = dateDebut;
        this.durree = durree;
        this.pause = pause;
    }

    public Pomodoro(Timestamp dateDebut, int durree, int pause) {
        DateDebut = dateDebut;
        this.durree = durree;
        this.pause = pause;
    }

    public Pomodoro(int idPomodoro) {
        this.idPomodoro = idPomodoro;
    }

    public int getIdPomodoro() {
        return idPomodoro;
    }

    public void setIdPomodoro(int idPomodoro) {
        this.idPomodoro = idPomodoro;
    }

    public Timestamp getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Timestamp dateDebut) {
        DateDebut = dateDebut;
    }

    public int getDurree() {
        return durree;
    }

    public void setDurree(int durree) {
        this.durree = durree;
    }

    public int getPause() {
        return pause;
    }

    public void setPause(int pause) {
        this.pause = pause;
    }
    public Pomodoro findById(int id) throws Exception {
        return findById(Connexion.getConnection(),String.valueOf(id));
    }
    public Pomodoro save() throws Exception {
        return super.saveAll(Connexion.getConnection());
    }
    public ArrayList<Pomodoro> SelectAll() throws Exception {
        return super.SelectAll(Connexion.getConnection());
    }
    public ArrayList<Pomodoro> SelectAllByQuerry(String sql) throws Exception {
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
    public Pomodoro findLast() throws Exception {
        return super.findlast(Connexion.getConnection());
    }
}
