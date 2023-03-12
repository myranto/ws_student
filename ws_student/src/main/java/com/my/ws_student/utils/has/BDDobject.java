package com.my.ws_student.utils.has;


import java.util.List;

public class BDDobject {
    public BDDobject() {
    }

    public <T> List<T> findAll(Connect con) throws Exception {
        return DBhelper.findAll(this, con.getConnection());
    }

    public <T> List<T> findAll() throws Exception {
        Connect con = new Connect();
        List<T> listes = DBhelper.findAll(this, con.getConnection());
        con.close();
        return listes;
    }

    public int save(Connect con) throws Exception {
        return DBhelper.save(this, con.getConnection());
    }

    public int save() throws Exception {
        Connect con = new Connect();
        int result = DBhelper.save(this, con.getConnection());
        con.close();
        return result;
    }

    public <T> T findById(int id, Connect con) throws Exception {
        return DBhelper.findbyId(this, id, con.getConnection());
    }

    public <T> T findById(int id) throws Exception {
        Connect con = new Connect();
        T result = DBhelper.findbyId(this, id, con.getConnection());
        con.close();
        return result;
    }

    public <T> List<T> findAllWhere(String req, Connect con) throws Exception {
        return DBhelper.findAllWhere(this, con.getConnection(), req);
    }

    public <T> List<T> findAllWhere(String req) throws Exception {
        Connect con = new Connect();
        List<T> result = DBhelper.findAllWhere(this, con.getConnection(), req);
        con.close();
        return result;
    }

    public <T> List<T> findAllFromView(String req, Connect con) throws Exception {
        return DBhelper.findAllFromView(this, con.getConnection(), req);
    }

    public <T> List<T> findAllFromView(String req) throws Exception {
        Connect con = new Connect();
        List<T> result = DBhelper.findAllFromView(this, con.getConnection(), req);
        con.close();
        return result;
    }

    public <T> T findOneRowWhere(String req, Connect con) throws Exception {
        return DBhelper.findOneRowWhere(this, con.getConnection(), req);
    }

    public <T> T findOneRowWhere(String req) throws Exception {
        Connect con = new Connect();
        T result = DBhelper.findOneRowWhere(this, con.getConnection(), req);
        con.close();
        return result;
    }

    public <T> T findOneRowFromView(String req, Connect con) throws Exception {
        return DBhelper.findOneRowFromView(this, con.getConnection(), req);
    }

    public <T> T findOneRowFromView(String req) throws Exception {
        Connect con = new Connect();
        T result = DBhelper.findOneRowFromView(this, con.getConnection(), req);
        con.close();
        return result;
    }

    public void updateRow(BDDobject o, Connect con) {
        DBhelper.updateRow(this, o, con.getConnection());
    }

    public void updateRow(BDDobject o) throws Exception {
        Connect con = new Connect();
        DBhelper.updateRow(this, o, con.getConnection());
        con.close();
    }
}
