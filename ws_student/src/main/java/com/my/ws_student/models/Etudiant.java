package com.my.ws_student.models;

import com.my.ws_student.utils.Connex.Connexion;
import com.my.ws_student.utils.DAO.ObjectBDD;
import com.my.ws_student.utils.inter.DefaultValueAnnotation;
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
    private String motdepasse;
    @KeyAnnotation
    private Date datenaissance;
    @KeyAnnotation
    @DefaultValueAnnotation
    private Date DateInscription;
    @KeyAnnotation
    private String carteetudiant;

    public Etudiant() {
    }

    public Etudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Etudiant(String nom, String prenom, String email, String motDePasse, Date dateNaissance, Date dateInscription, String carteEtudiant) {
        Nom = nom;
        Prenom = prenom;
        Email = email;
        motdepasse = motDePasse;
        datenaissance = dateNaissance;
        DateInscription = dateInscription;
        carteetudiant = carteEtudiant;
    }

    public Etudiant(int idEtudiant, String nom, String prenom, String email, String motDePasse, Date dateNaissance, Date dateInscription, String carteEtudiant) {
        this.idEtudiant = idEtudiant;
        Nom = nom;
        Prenom = prenom;
        Email = email;
        motdepasse = motDePasse;
        datenaissance = dateNaissance;
        DateInscription = dateInscription;
        carteetudiant = carteEtudiant;
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

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public Date getDatenaissance() {
        return datenaissance;
    }

    public void setDatenaissance(Date datenaissance) {
        this.datenaissance = datenaissance;
    }

    public Date getDateInscription() {
        return DateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        DateInscription = dateInscription;
    }

    public String getCarteetudiant() {
        return carteetudiant;
    }

    public void setCarteetudiant(String carteetudiant) {
        this.carteetudiant = carteetudiant;
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

            String sql = "select * from Etudiant where Email='"+email+"' and motdepasse='"+mdp+"'";
            return  (Etudiant) SelectAllByQuerry(sql).get(0);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("email or password incorrect for email :"+email);
        }
    }
//    public static void main(String[] args) throws Exception {
//        Etudiant e = new Etudiant().login("ncakc","imdw");
//    }
}
