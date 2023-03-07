package com.my.ws_student.utils.Connex;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexion {
    public static Connection getConnection() throws Exception {
        Connection con=null;
        try{
            Class.forName("org.postgresql.Driver");
            String url="jdbc:postgresql://localhost:5432/hiu";
	   		String user="postgres";
	   		String password="myranto";
	   		con=(Connection) DriverManager.getConnection(url, user, password);
            return con;
        }
        catch(Exception e){
            throw e;
        }
    }
}
