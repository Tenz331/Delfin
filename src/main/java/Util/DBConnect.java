package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private Connection connection;
    private static DBConnect instance;

    private DBConnect() {
        try {
            String baseurl = "jdbc:mysql://cphb-gruppe1.c4mqzn3xlkdy.us-east-2.rds.amazonaws.com/"; //url
            String db = "Delfinen";//database
            String timeZ = "serverTimezone=UTC&allowPublicKeyRetrieval=true"; //noget fra thor idk wwhat it really does
            String totalUrl = baseurl + db + "?" + timeZ; //sætter linked sammen med 'timeZ'
            String user = "fullroot"; //username
            String password = "fullroot"; //password
            connection = DriverManager.getConnection(totalUrl, user, password);//opretter forbindelse
        } catch (SQLException id) {
            System.out.println("Error: " + id.getMessage());//fejl msg
        }
    }

    public static DBConnect getInstance() {

        if (instance == null) {
            instance = new DBConnect();
        }
        return instance;
    }

    public static String Login(String user, String password) throws SQLException {
        try {
            Connection connection;
            String baseurl = "jdbc:mysql://cphb-gruppe1.c4mqzn3xlkdy.us-east-2.rds.amazonaws.com/"; //url
            String db = "Delfinen";//database

            String timeZ = "serverTimezone=UTC&allowPublicKeyRetrieval=true"; //noget fra thor idk wwhat it really does
            String totalUrl = baseurl + db + "?" + timeZ; //sætter linked sammen med 'timeZ'
            connection = DriverManager.getConnection(totalUrl, user, password);
            System.out.println("login succesfull");
            System.out.println("Welcome: '"+user+"'\n");
            connection.close();

        } catch (SQLException e) {
            System.out.println("LOGIN ERROR! ");
            System.out.println(e);
            return "ERROR";
        }
        return user;
    }

    public Connection getConnection() {
        return this.connection;
    }
}