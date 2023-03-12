package com.my.ws_student.utils.has;

import java.sql.*;

public class Connect {
    public Connection con;
    public String req;
    public PreparedStatement prepstat;
    public ResultSet res;

    public PreparedStatement getPreparedStatement(String s) throws SQLException {
        try {
            this.prepstat = this.getConnection().prepareStatement(s);
            return this.prepstat;
        } catch (SQLException var3) {
            throw var3;
        }
    }

    public Connect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hiu", "postgres", "myranto");
        } catch (SQLException | ClassNotFoundException var2) {
            throw var2;
        }
    }

    public Connect(String base) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + base, "postgres", "myranto");
        } catch (SQLException | ClassNotFoundException var3) {
            throw var3;
        }
    }

    public void commit() throws SQLException {
        try {
            this.con.commit();
        } catch (SQLException var2) {
            throw var2;
        }
    }

    public Connection getConnection() {
        return this.con;
    }

    public void close() {
        try {
            if (this.prepstat != null) {
                this.prepstat.close();
            }

            if (this.res != null) {
                this.res.close();
            }

            if (this.con != null) {
                this.con.close();
            }
        } catch (SQLException var2) {
        }

    }

    public ResultSet getResultSet() throws SQLException {
        try {
            this.res = this.prepstat.executeQuery();
        } catch (SQLException var2) {
            throw var2;
        }

        return this.res;
    }

    public void setRes(ResultSet res) {
        this.res = res;
    }
}
