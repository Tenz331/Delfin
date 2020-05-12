package Datamapper;

import Model.Members;
import Util.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MemberWrite {
    Connection conn = DBConnect.getInstance().getConnection();


    ArrayList<Members> tempmembers;

    public void setMember(Members members) {
        try {
            tempmembers = new ArrayList<>();
            tempmembers.add(members);
            for (Members i : tempmembers) {
                System.out.println(i.getName().toString());
                String query = "INSERT INTO Delfinen.Membership (member_idd, member_name, member_email, member_telefon, member_fødselsdag, member_favSvømmeart, member_hold, member_kontingent, betalt_kontigent)" + "values(?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, i.getUnicID());
                preparedStatement.setString(2, i.getName());
                preparedStatement.setString(3, i.getEmail());
                preparedStatement.setInt(4, i.getTlfNr());
                preparedStatement.setDate(5, Date.valueOf(i.getFodselsdag()));
                preparedStatement.setString(6, i.getFavSvommeArt());
                preparedStatement.setString(7, i.getSvommeHold());
                preparedStatement.setDouble(8, i.kontigentBeregner());
                preparedStatement.setBoolean(9,false);
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
    public void updateTeam(int tempNewID, String change, Members members){
        try {
            double timeToDouble = Double.parseDouble(change);
            //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
            String query = "INSERT INTO Delfinen.StatistikDB (member_idd, member_name, DB_tid)" + "values(?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1,members.getUnicID());
            preparedStatement.setString(2,members.getName());
            preparedStatement.setDouble(3,timeToDouble);
            preparedStatement.execute();
            System.out.println("Member-ID #" + tempNewID + " Has been changed with the value: " + change);
        } catch (Exception e) {
            System.out.println("ERROR! :" + e);
            System.out.println("Make sure your input is correct");
        }
    }


    public void updateKontigent(int tempNewID, String change) {
        try {
            double kontingent = Double.parseDouble(change);
            //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
            String query = "UPDATE Delfinen.Membership SET member_kontingent = '" + kontingent + "' WHERE member_idd =" + tempNewID;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
            System.out.println("Member ID #" + tempNewID + " Has been changed with the value: " + change);
        }   catch (Exception e) {
            System.out.println("ERROR! :" + e);
            System.out.println("Make sure your input is correct");
        }

    }
}


