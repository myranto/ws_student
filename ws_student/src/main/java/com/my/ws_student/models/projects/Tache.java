package com.my.ws_student.models.projects;

import com.my.ws_student.models.Etudiant;
import com.my.ws_student.models.community.Publication;
import com.my.ws_student.utils.Connex.Connexion;
import com.my.ws_student.utils.DAO.ObjectBDD;
import com.my.ws_student.utils.inter.ForeignKeyAnnotation;
import com.my.ws_student.utils.inter.IdAnnotation;
import com.my.ws_student.utils.inter.KeyAnnotation;
import com.my.ws_student.utils.inter.TableAnnotation;

import java.sql.Timestamp;
import java.util.ArrayList;

@TableAnnotation
public class Tache extends ObjectBDD {
    @KeyAnnotation
    @IdAnnotation(name = "idTache")
    private int idTache;
    @KeyAnnotation
    private int idEtudiant;
    @KeyAnnotation
    private Timestamp DatePlanning;
    @KeyAnnotation
    private int Durree;
    @KeyAnnotation
    private String TitreTache;
    @KeyAnnotation
    private String DescriptionTache;
    @KeyAnnotation
    private int priorite;
    @KeyAnnotation
    private Timestamp rappel;
    @KeyAnnotation
    private int etat;
    @KeyAnnotation
    private int ProjetidProjet;
    @ForeignKeyAnnotation(name = "ProjetidProjet",references = "idProjet")
    private Projet projet;
    @ForeignKeyAnnotation(name = "idEtudiant",references = "idEtudiant")
    private Etudiant etudiant;
    private ArrayList<SousTache> list_sousTache=null;

    public ArrayList<SousTache> getList_sousTache() throws Exception {
        if (list_sousTache == null) {
            list_sousTache = new SousTache().SelectByIdTache(getIdTache());
        }
        return list_sousTache;
    }

    public void setList_sousTache(ArrayList<SousTache> list_sousTache) {
        this.list_sousTache = list_sousTache;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Tache(int idEtudiant, Timestamp datePlanning, int durree, String titreTache, String descriptionTache, int priorite, Timestamp rappel, int etat, int projetidProjet) {
        this.idEtudiant = idEtudiant;
        DatePlanning = datePlanning;
        Durree = durree;
        TitreTache = titreTache;
        DescriptionTache = descriptionTache;
        this.priorite = priorite;
        this.rappel = rappel;
        this.etat = etat;
        ProjetidProjet = projetidProjet;
    }

    public Tache() {
    }

    public Tache(int idTache) {
        this.idTache = idTache;
    }

    public Tache(int idTache, int idEtudiant, Timestamp datePlanning, int durree, String titreTache, String descriptionTache, int priorite, Timestamp rappel, int etat, int projetidProjet) {
        this.idTache = idTache;
        this.idEtudiant = idEtudiant;
        DatePlanning = datePlanning;
        Durree = durree;
        TitreTache = titreTache;
        DescriptionTache = descriptionTache;
        this.priorite = priorite;
        this.rappel = rappel;
        this.etat = etat;
        ProjetidProjet = projetidProjet;
    }

    public int getIdTache() {
        return idTache;
    }

    public void setIdTache(int idTache) {
        this.idTache = idTache;
    }

    public int getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(int idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Timestamp getDatePlanning() {
        return DatePlanning;
    }

    public void setDatePlanning(Timestamp datePlanning) {
        DatePlanning = datePlanning;
    }

    public int getDurree() {
        return Durree;
    }

    public void setDurree(int durree) {
        Durree = durree;
    }

    public String getTitreTache() {
        return TitreTache;
    }

    public void setTitreTache(String titreTache) {
        TitreTache = titreTache;
    }

    public String getDescriptionTache() {
        return DescriptionTache;
    }

    public void setDescriptionTache(String descriptionTache) {
        DescriptionTache = descriptionTache;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public Timestamp getRappel() {
        return rappel;
    }

    public void setRappel(Timestamp rappel) {
        this.rappel = rappel;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getProjetidProjet() {
        return ProjetidProjet;
    }

    public void setProjetidProjet(int projetidProjet) {
        ProjetidProjet = projetidProjet;
    }
    public Tache findById(int id) throws Exception {
        Tache e = findById(Connexion.getConnection(),String.valueOf(id));
//        init(e);
        return e;
    }
    public Tache save() throws Exception {
        Tache e= super.saveAll(Connexion.getConnection());
//        init(e);
        return e;
    }
    public ArrayList<Tache> SelectByIdProject(int idProject) throws Exception {
        String sql = "select * from "+getNomTable()+" where ProjetidProjet="+idProject+" order by priorite desc";
        ArrayList<Tache> list = SelectAllByQuerry(sql);
//        initTable(list);
        return list;
    }
    public ArrayList<Tache> SelectAll() throws Exception {
        ArrayList<Tache> list = super.SelectAll(Connexion.getConnection());
//        initTable(list);
        return list;
    }
    public ArrayList<Tache> SelectAllByQuerry(String sql) throws Exception {
        ArrayList<Tache> list = SelectAllByQuery(Connexion.getConnection(),sql);
//        initTable(list);
        return list;
    }
    public Tache changeEtat(int etat,int idTache) throws Exception {
        Tache e = new Tache().findById(idTache);
        e.setEtat(etat);
        e.updateById(Connexion.getConnection());
//        init(e);
        return e;
    }
    public void update() throws Exception {
        Tache t = findById(getIdTache());
        t = new Tache(t.getIdTache(), getIdEtudiant(), getDatePlanning(), getDurree(), getTitreTache(), getDescriptionTache(), getPriorite(), getRappel(), getEtat(), getProjetidProjet());
        t.updateById(Connexion.getConnection());
    }

     public double getSousTachesTermineesPourcentage(int idTache) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double pourcentage = 0;
        try {
            con = Connexion.getConnection();
            String query = "SELECT COUNT(*) FILTER (WHERE etat = 1) * 100.0 / COUNT(*) AS pourcentage_sous_tache FROM  SousTache  WHERE PlanningidTache=?";
            ps = con.prepareStatement(query);
            ps.setInt(1, idTache);
            rs = ps.executeQuery();
            if (rs.next()) {
                pourcentage = rs.getDouble(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return pourcentage;
    }

    public List<Projet> rechercheParMotCle(String motCle, String dateDebut) throws Exception {
        List<Projet> result = new ArrayList<>();
        Fonction f= new Fonction();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "SELECT * FROM Projet WHERE (NomProjet LIKE ? OR DescriptionProjet LIKE ?) AND (DateDebut = ? )";
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + motCle + "%");
            ps.setString(2, "%" + motCle + "%");
            ps.setTimestamp(3,f.transform(dateDebut));
            rs = ps.executeQuery();
            while (rs.next()) {
                Projet projet = new Projet();
                projet.setIdProjet(rs.getInt("idProjet"));
                projet.setEtudiantidEtudiant(rs.getInt("EtudiantidEtudiant"));
                projet.setNomProjet(rs.getString("NomProjet"));
                projet.setDateDebut(rs.getTimestamp("DateDebut"));
                projet.setDescriptionProjet(rs.getString("DescriptionProjet"));
                projet.setDateFin(rs.getTimestamp("DateFin"));
                result.add(projet);
            }
        }
        catch(Exception e)
        {
            throw e;
        }
        finally{
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
