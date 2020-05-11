package Datamapper;

import Model.Members;
import Util.DBConnect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberWrite {

    Connection connection = DBConnect.getInstance().getConnection();
    ArrayList<Members> tempmembers;

    public void setMember(Members members) {
        try {
            tempmembers = new ArrayList<>();
            tempmembers.add(members);
            for (Members i : tempmembers) {
                System.out.println(i.getName().toString());
                String query = "INSERT INTO Delfinen.Membership (member_name, member_email, member_telefon, member_fødselsdag, member_favSvømmeart, member_hold, member_kontingent, member_idd )" + "values(?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, i.getName());
                preparedStatement.setString(2, i.getEmail());
                preparedStatement.setInt(3, i.getTlfNr());
                preparedStatement.setDate(4, Date.valueOf(i.getFodselsdag()));
                preparedStatement.setString(5, i.getFavSvommeArt());
                preparedStatement.setString(6, i.getSvommeHold());
                preparedStatement.setDouble(7, i.kontigentBeregner());
                preparedStatement.setInt(8,i.getUnicID());
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


