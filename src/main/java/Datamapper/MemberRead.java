package Datamapper;

import Model.JuniorMedlem;
import Model.Members;
import Model.PensionistMedlem;
import Model.SeniorMedlem;
import Util.DBConnect;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class MemberRead {
    static int tempcounter = 0;
    Connection conn = DBConnect.getInstance().getConnection();

    Map<Integer, Members> tempMembers;

    public Map<Integer, Members> getMember() {
        tempMembers = new HashMap<>();
        try (

                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM Delfinen.Membership")
        ) {
            while (rs.next()) {
                tempcounter++;
                StringBuffer buffer = new StringBuffer();
                String teamType = rs.getString("member_hold");
                if (teamType.equals("Junior")) {
                    //StringBuffer buffer = new StringBuffer();
                    //buffer.append(rs.getInt("member_idd"), rs.getString("member_name"), rs.getString("member_email"), rs.getInt("member_telefon"), rs.getDate("member_fodselsdag").toLocalDate(), rs.getString("member_favSvommeArt"), rs.getString("member_svommeHold"));
                    JuniorMedlem temp = new JuniorMedlem(rs.getInt("member_idd"), rs.getString("member_name"), rs.getString("member_email"), rs.getInt("member_telefon"), rs.getDate("member_fødselsdag").toLocalDate(), rs.getString("member_favSvømmeArt"), rs.getString("member_hold"), rs.getDouble("member_kontingent"), rs.getBoolean("betalt_kontigent"));
                    tempMembers.put(tempcounter, temp);

                } else if (teamType.equals("Senior")) {
                    SeniorMedlem temp = new SeniorMedlem(rs.getInt("member_idd"), rs.getString("member_name"), rs.getString("member_email"), rs.getInt("member_telefon"), rs.getDate("member_fødselsdag").toLocalDate(), rs.getString("member_favSvømmeArt"), rs.getString("member_hold"), rs.getDouble("member_kontingent"), rs.getBoolean("betalt_kontigent"));
                    tempMembers.put(tempcounter, temp);

                } else {
                    PensionistMedlem temp = new PensionistMedlem(rs.getInt("member_idd"), rs.getString("member_name"), rs.getString("member_email"), rs.getInt("member_telefon"), rs.getDate("member_fødselsdag").toLocalDate(), rs.getString("member_favSvømmeArt"), rs.getString("member_hold"), rs.getDouble("member_kontingent"), rs.getBoolean("betalt_kontigent"));
                    tempMembers.put(tempcounter, temp);

                }

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        tempcounter = 0;
        return tempMembers;
    }

    public void getDisciplin() {

    }

    public void getResult() {

    }

    public Map<Integer, Members> checkRestance()  {
        tempMembers = new HashMap<>();
        try (
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM Delfinen.Membership WHERE betalt_kontigent = '0' ")
        ) {
            while (rs.next()) {
                String teamType = rs.getString("member_hold");
                tempcounter++;
                if (teamType.equals("Junior")) {
                    JuniorMedlem temp = new JuniorMedlem(rs.getInt("member_idd"), rs.getString("member_name"), rs.getString("member_email"), rs.getInt("member_telefon"), rs.getDate("member_fødselsdag").toLocalDate(), rs.getString("member_favSvømmeArt"), rs.getString("member_hold"), rs.getDouble("member_kontingent"), rs.getBoolean("betalt_kontigent"));
                    tempMembers.put(tempcounter, temp);

                } else if (teamType.equals("Senior")) {
                    SeniorMedlem temp = new SeniorMedlem(rs.getInt("member_idd"), rs.getString("member_name"), rs.getString("member_email"), rs.getInt("member_telefon"), rs.getDate("member_fødselsdag").toLocalDate(), rs.getString("member_favSvømmeArt"), rs.getString("member_hold"), rs.getDouble("member_kontingent"), rs.getBoolean("betalt_kontigent"));
                    tempMembers.put(tempcounter, temp);

                } else {
                    PensionistMedlem temp = new PensionistMedlem(rs.getInt("member_idd"), rs.getString("member_name"), rs.getString("member_email"), rs.getInt("member_telefon"), rs.getDate("member_fødselsdag").toLocalDate(), rs.getString("member_favSvømmeArt"), rs.getString("member_hold"), rs.getDouble("member_kontingent"), rs.getBoolean("betalt_kontigent"));
                    tempMembers.put(tempcounter, temp);

                }


            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        tempcounter = 0;
        return tempMembers;

    }
}
