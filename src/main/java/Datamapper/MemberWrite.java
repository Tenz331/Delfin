package Datamapper;

import Model.Members;
import Util.DBConnect;

import java.sql.*;
import java.util.ArrayList;

public class MemberWrite {
    private static final String USERNAME = "fullroot";
    private static final String PASSWORD = "fullroot";
    private static final String CONN_STR = "jdbc:mysql://cphb-gruppe1.c4mqzn3xlkdy.us-east-2.rds.amazonaws.com/";

    ArrayList<Members> tempmembers;

    public void setMember(Members members) {
        try {
            Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            tempmembers = new ArrayList<>();
            tempmembers.add(members);
            for (Members i : tempmembers) {
                System.out.println(i.getName().toString());
                String query = "INSERT INTO Delfinen.Membership (member_idd, member_name, member_email, member_telefon, member_fødselsdag, member_favSvømmeart, member_hold, member_kontingent)" + "values(?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, i.getUnicID());
                preparedStatement.setString(2, i.getName());
                preparedStatement.setString(3, i.getEmail());
                preparedStatement.setInt(4, i.getTlfNr());
                preparedStatement.setDate(5, Date.valueOf(i.getFodselsdag()));
                preparedStatement.setString(6, i.getFavSvommeArt());
                preparedStatement.setString(7, i.getSvommeHold());
                preparedStatement.setDouble(8, i.kontigentBeregner());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateMember() {
    }

    public void registerDisciplin() {
    }

    public void registerResult() {
    }


}


