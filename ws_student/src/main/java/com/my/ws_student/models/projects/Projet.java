package com.my.ws_student.models.projects;

import com.my.ws_student.models.Etudiant;
import com.my.ws_student.utils.Connex.Connexion;
import com.my.ws_student.utils.DAO.ObjectBDD;
import com.my.ws_student.utils.Fonction;
import com.my.ws_student.utils.inter.ForeignKeyAnnotation;
import com.my.ws_student.utils.inter.IdAnnotation;
import com.my.ws_student.utils.inter.KeyAnnotation;
import com.my.ws_student.utils.inter.TableAnnotation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    private int estimationProject = 0;
    private int tempPasser = 0;
    private double tachesTermineesPourcentage=0;

    public double getTachesTermineesPourcentage()  {
        if (tachesTermineesPourcentage == 0) {
            try {
                tachesTermineesPourcentage = getTachesTermineesPourcentage(getIdProjet());
            } catch (Exception e) {
                e.printStackTrace();
                tachesTermineesPourcentage=0;
            }
        }
        return tachesTermineesPourcentage;
    }

    public void setTachesTermineesPourcentage(double tachesTermineesPourcentage) {
        this.tachesTermineesPourcentage = tachesTermineesPourcentage;
    }

    public int getTempPasser() throws Exception {
        if (tempPasser==0)
             tempPasser = getDurreeProjet(getIdProjet());
        return tempPasser;
    }

    public void setTempPasser(int tempPasser) {
        this.tempPasser = tempPasser;
    }

    public int getEstimationProject() throws Exception {
        if (estimationProject == 0) {
                estimationProject=getDurreeEstimationProjet(getIdProjet());
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
    public ArrayList<Projet> SelectAllByEtudiant(int idetudiant) throws Exception {
        String sql = "select * from "+getNomTable()+" where EtudiantidEtudiant="+idetudiant;
        return SelectAllByQuerry(sql);
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
    public double getTachesTermineesPourcentage(int idProject) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double pourcentage = 0;
        try {
            con = Connexion.getConnection();
            String query = "SELECT COUNT(DISTINCT idTache) FILTER (WHERE etat = 1) * 100.0 / COUNT(DISTINCT idTache) AS pourcentage_tache FROM Tache WHERE ProjetidProjet =?";
            ps = con.prepareStatement(query);
            ps.setInt(1, idProject);
            rs = ps.executeQuery();
            if (rs.next()) {
                pourcentage = rs.getDouble(1);
            }
        } catch (Exception e) {
            if (!e.getMessage().equals("ERREUR: division par zéro")) {
                throw e;
            }
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
    public int getDurreeEstimationProjet(int idProject) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int durree = 0;
        try {
            con = Connexion.getConnection();
            String query ="SELECT SUM(Tache.Durree) as duree_totale from Tache where projetidprojet  =?";

            ps = con.prepareStatement(query);
            ps.setInt(1, idProject);
            rs = ps.executeQuery();
            if (rs.next()) {
                durree = rs.getInt(1);
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
        return durree;
    }

    public int getDurreeProjet(int idProject) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int durree = 0;
        try {
            con = Connexion.getConnection();
            String query ="SELECT SUM(sousTache.TempsPasse) AS duree_passee_total FROM projet join Tache on projet.idprojet=Tache.projetidprojet JOIN SousTache ON Tache.idTache = SousTache.PlanningidTache WHERE projet.idProjet =?";
            ps = con.prepareStatement(query);
            ps.setInt(1, idProject);
            rs = ps.executeQuery();
            if (rs.next()) {
                durree = rs.getInt(1);
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
        return durree;
    }

    public double getMoyenneDurree(int idEtudiant) throws Exception {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double pourcentage = 0;
        try {
            con = Connexion.getConnection();
            String query = "SELECT AVG(TempsPasse) AS temps_moyen FROM SousTache st INNER JOIN Tache t ON t.idTache = st.PlanningidTache INNER JOIN Projet p ON p.idProjet = t.ProjetidProjet INNER JOIN Etudiant e ON e.idEtudiant = p.EtudiantidEtudiant WHERE e.idEtudiant = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, idEtudiant);
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

}
