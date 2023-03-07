package com.my.ws_student.models;

import com.my.ws_student.utils.Connex.Connexion;
import com.my.ws_student.utils.DAO.ObjectBDD;
import com.my.ws_student.utils.inter.IdAnnotation;
import com.my.ws_student.utils.inter.KeyAnnotation;
import com.my.ws_student.utils.inter.TableAnnotation;

import java.sql.Date;
import java.util.ArrayList;

@TableAnnotation
public class Etudiant extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation(name = "idEtudiant")
    private int idEtudiant;
    @KeyAnnotation
    private String Nom;
    @KeyAnnotation
    private String Prenom;
    @KeyAnnotation
    private String Email;
    @KeyAnnotation
    private String MotDePasse;
    @KeyAnnotation
    private Date DateNaissance;
    @KeyAnnotation
    private Date DateInscription;
    @KeyAnnotation
    private String CarteEtudiant;

    public Etudiant() {
    }

    public Etudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Etudiant(String nom, String prenom, String email, String motDePasse, Date dateNaissance, Date dateInscription, String carteEtudiant) {
        Nom = nom;
        Prenom = prenom;
        Email = email;
        MotDePasse = motDePasse;
        DateNaissance = dateNaissance;
        DateInscription = dateInscription;
        CarteEtudiant = carteEtudiant;
    }

    public Etudiant(int idEtudiant, String nom, String prenom, String email, String motDePasse, Date dateNaissance, Date dateInscription, String carteEtudiant) {
        this.idEtudiant = idEtudiant;
        Nom = nom;
        Prenom = prenom;
        Email = email;
        MotDePasse = motDePasse;
        DateNaissance = dateNaissance;
        DateInscription = dateInscription;
        CarteEtudiant = carteEtudiant;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        Prenom = prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMotDePasse() {
        return MotDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        MotDePasse = motDePasse;
    }

    public Date getDateNaissance() {
        return DateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        DateNaissance = dateNaissance;
    }

    public Date getDateInscription() {
        return DateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        DateInscription = dateInscription;
    }

    public String getCarteEtudiant() {
        return CarteEtudiant;
    }

    public void setCarteEtudiant(String carteEtudiant) {
        CarteEtudiant = carteEtudiant;
    }
    public Etudiant findById(int id) throws Exception {
        return findById(Connexion.getConnection(),String.valueOf(id));
    }
    public Etudiant save() throws Exception {
       return super.saveAll(Connexion.getConnection());
    }
    public ArrayList<Etudiant> SelectAll() throws Exception {
        return super.SelectAll(Connexion.getConnection());
    }
    public ArrayList<Etudiant> SelectAllByQuerry(String sql) throws Exception {
        return SelectAllByQuery(Connexion.getConnection(),sql);
    }
    public Etudiant login(String email,String mdp) throws Exception {
        try {
            Etudiant etu = new Etudiant();
            etu.setEmail(email);
            etu.setMotDePasse(mdp);
            return  (Etudiant) etu.search(Connexion.getConnection()).get(0);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("email or password incorrect for email :"+email);
        }
    }

    public static void main(String[] args) throws Exception {
        Etudiant e = new Etudiant().login("ncakc","imdw");
    }
}
