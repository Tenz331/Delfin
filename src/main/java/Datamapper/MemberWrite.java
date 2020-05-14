package Datamapper;

import Model.Members;
import Util.DBConnect;

import java.sql.*;
import java.util.ArrayList;

public class MemberWrite {
    Connection conn = DBConnect.getInstance().getConnection();


    ArrayList<Members> tempmembers;

    public void setMember(Members members) {
        try {
            tempmembers = new ArrayList<>();
            tempmembers.add(members);
            for (Members i : tempmembers) {
                System.out.println(i.getName().toString());
                String query = "INSERT INTO Delfinen.Membership (member_idd, member_name, member_email, member_telefon, member_fødselsdag, member_favSvømmeart, member_hold, betalt_kontigent, member_aktiv)" + "values(?,?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, i.getUnicID());
                preparedStatement.setString(2, i.getName());
                preparedStatement.setString(3, i.getEmail());
                preparedStatement.setInt(4, i.getTlfNr());
                preparedStatement.setDate(5, Date.valueOf(i.getFodselsdag()));
                preparedStatement.setString(6, i.getFavSvommeArt());
                preparedStatement.setString(7, i.getSvommeHold());
                preparedStatement.setBoolean(8,false);
                preparedStatement.setBoolean(9,true);
                preparedStatement.execute();
                setKontigent(members);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void setKontigent(Members members) {
        tempmembers = new ArrayList<>();
        tempmembers.add(members);
        try {
            for (Members i : tempmembers) {
                String query = "INSERT INTO Delfinen.Restance (member_name, member_kontigent)" + "values(?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, i.getName());
                preparedStatement.setDouble(2, i.kontigentBeregner());
                preparedStatement.execute();
            }
        }catch (SQLException e) {
            System.out.println(e);
            }
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

    public void deleteMember(int id) {
        try {
            String query = "Delete from Delfinen.Membership where member_idd = '" + id+"'";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
            System.out.println("Member ID #" + id + " Has been deleted");
        }   catch (Exception e) {
            System.out.println("ERROR! :" + e);
            System.out.println("Make sure your input is correct");
        }

    }

    public void addKonkurrence(String tid,String type, String konkurrencenLocation, Members member) {
        tempmembers = new ArrayList<>();
        tempmembers.add(member);
        try {
            for (Members i : tempmembers) {
                String query = "INSERT INTO Delfinen.StatistikDB (member_idd, member_name, DB_tournament, DB_placement, member_hold, DB_tid )" + "values(?,?,?,?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, i.getUnicID());
                preparedStatement.setString(2, i.getName());
                preparedStatement.setString(3,type);
                preparedStatement.setString(4,konkurrencenLocation);
                preparedStatement.setString(5,i.getSvommeHold());
                preparedStatement.setString(6,tid);
                preparedStatement.execute();
            }
        }catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateKontingentActive(int memberID, String memberKontingent ) {
        try {
            String query = "UPDATE Delfinen.Membership SET betalt_kontigent = '" + memberKontingent + "'WHERE member_idd = " + memberID + "";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void updateMemberActive(int memberID, String memberActive) {
        try {
            String query = "UPDATE Delfinen.Membership SET member_aktiv ='" + memberActive + "' WHERE member_idd = " + memberID + "";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    }




