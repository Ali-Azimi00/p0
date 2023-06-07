package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionUtil {

    public static Connection getConnection() throws SQLException {

        try{
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e){
            System.out.println("problem occured locating driver");
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=p0";
        String username = "postgres";
        String password = "password";

        return DriverManager.getConnection(url, username, password);
    }


}
