package utils;

import java.sql.*;

public class MyDB {
    private String url = "jdbc:mysql://localhost:3306/stackflowbeta", username = "root", password = "";
    private Connection cnx;
    private static MyDB instance;

    public Connection getCnx() {
        return cnx;
    }

    public MyDB() {
        try {
            cnx = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion successful");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static MyDB getInstance() {
        if (instance == null)
            instance = new MyDB();
        return instance;
    }

    public Connection getConnection() {
   return cnx;    }
}