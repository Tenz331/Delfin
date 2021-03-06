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
                ResultSet rs = stmt.executeQuery("SELECT * FROM Delfinen.Membership");
                Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs2 = stmt2.executeQuery("SELECT * FROM Delfinen.Restance")
        ) {
            while(rs.next() && rs2.next()) {
                tempcounter++;
                StringBuffer buffer = new StringBuffer();
                String teamType = rs.getString("member_hold");
                if (teamType.equals("Junior")) {
                    //StringBuffer buffer = new StringBuffer();
                    //buffer.append(rs.getInt("member_idd"), rs.getString("member_name"), rs.getString("member_email"), rs.getInt("member_telefon"), rs.getDate("member_fodselsdag").toLocalDate(), rs.getString("member_favSvommeArt"), rs.getString("member_svommeHold"));
                    JuniorMedlem temp = new JuniorMedlem(rs.getInt("member_idd"), rs.getString("member_name"), rs.getString("member_email"), rs.getInt("member_telefon"), rs.getDate("member_fødselsdag").toLocalDate(), rs.getString("member_favSvømmeArt"), rs.getString("member_hold"), rs2.getDouble("member_kontigent"), rs.getBoolean("betalt_kontigent"));
                    tempMembers.put(tempcounter, temp);

                } else if (teamType.equals("Senior")) {
                    SeniorMedlem temp = new SeniorMedlem(rs.getInt("member_idd"), rs.getString("member_name"), rs.getString("member_email"), rs.getInt("member_telefon"), rs.getDate("member_fødselsdag").toLocalDate(), rs.getString("member_favSvømmeArt"), rs.getString("member_hold"), rs2.getDouble("member_kontigent"), rs.getBoolean("betalt_kontigent"));
                    tempMembers.put(tempcounter, temp);

                } else {
                    PensionistMedlem temp = new PensionistMedlem(rs.getInt("member_idd"), rs.getString("member_name"), rs.getString("member_email"), rs.getInt("member_telefon"), rs.getDate("member_fødselsdag").toLocalDate(), rs.getString("member_favSvømmeArt"), rs.getString("member_hold"), rs2.getDouble("member_kontigent"), rs.getBoolean("betalt_kontigent"));
                    tempMembers.put(tempcounter, temp);

                }

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        tempcounter = 0;
        return tempMembers;
    }
    public void top5Junior(){
        try (
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM Delfinen.StatistikDB WHERE member_hold = 'Junior' ORDER BY DB_tid ASC LIMIT 5")
        ) {
            System.out.println("Top 5 List Junior:\n ");
            while (rs.next()) {
                StringBuffer buffer = new StringBuffer();
                String fastestTime = rs.getString(2);
                String fastestTime2 = rs.getString(3);
                String fastestTime3 = rs.getString(4);
                String fastestTime4 = rs.getString(7);

                System.out.println("#" + fastestTime + " Navn: " + fastestTime2 + "Disciplin: " + fastestTime3 + " Time: " + fastestTime4 + "\n");
            }}
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void top5Senior(){
        try (
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM Delfinen.StatistikDB WHERE member_hold = 'Senior' ORDER BY DB_tid ASC LIMIT 5")
        ) {
            System.out.println("Top 5 List Senior:\n ");
            while (rs.next()) {
                StringBuffer buffer = new StringBuffer();
                String fastestTime = rs.getString(2);
                String fastestTime2 = rs.getString(3);
                String fastestTime3 = rs.getString(4);
                String fastestTime4 = rs.getString(7);

                System.out.println("#" + fastestTime + " Navn: " + fastestTime2 + "Disciplin: " + fastestTime3 + " Time: " + fastestTime4 + "\n");
            }}
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMaxUid() {
        int tempUID = 0;
        //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
        try (
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT MAX(member_idd) FROM Delfinen.Membership")
        ) {
            while (rs.next()) {
                tempUID = rs.getInt("MAX(member_idd)");

            }


        } catch (SQLException e) {
            //Different error messages
            System.out.println(e);
        }
        return tempUID;
    } //finder højeste

    public Map<Integer, Members> checkRestance() {
        tempMembers = new HashMap<>();
        try (
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM Delfinen.Membership WHERE betalt_kontigent = '0' ")

        ) {
            while(rs.next()) {
                String teamType = rs.getString("member_hold");
                tempcounter++;
                if (teamType.equals("Junior")) {
                    JuniorMedlem temp = new JuniorMedlem(rs.getInt("member_idd"), rs.getString("member_name"), rs.getString("member_email"), rs.getInt("member_telefon"), rs.getDate("member_fødselsdag").toLocalDate(), rs.getString("member_favSvømmeArt"), rs.getString("member_hold"), rs.getDouble("betalt_kontigent"), rs.getBoolean("betalt_kontigent"));
                    tempMembers.put(tempcounter, temp);

                } else if (teamType.equals("Senior")) {
                    SeniorMedlem temp = new SeniorMedlem(rs.getInt("member_idd"), rs.getString("member_name"), rs.getString("member_email"), rs.getInt("member_telefon"), rs.getDate("member_fødselsdag").toLocalDate(), rs.getString("member_favSvømmeArt"), rs.getString("member_hold"), rs.getDouble("betalt_kontigent"), rs.getBoolean("betalt_kontigent"));
                    tempMembers.put(tempcounter, temp);

                } else {
                    PensionistMedlem temp = new PensionistMedlem(rs.getInt("member_idd"), rs.getString("member_name"), rs.getString("member_email"), rs.getInt("member_telefon"), rs.getDate("member_fødselsdag").toLocalDate(), rs.getString("member_favSvømmeArt"), rs.getString("member_hold"), rs.getDouble("betalt_kontigent"), rs.getBoolean("betalt_kontigent"));
                    tempMembers.put(tempcounter, temp);

                }


            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        tempcounter = 0;
        return tempMembers;

    }
    public Map<Integer, Members> getSpecificMember(int id) {
        tempMembers = new HashMap<>();

        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM Delfinen.Membership");
            Statement stmt2 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); //hacked fix lol
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM Delfinen.Restance");
            while (rs.next() && rs2.next()) {
                String teamType = rs.getString("member_hold");
                int uid = rs.getInt("member_idd");
                if (teamType.equals("Junior")) {
                    if (uid == id) {
                        JuniorMedlem temp = new JuniorMedlem(rs.getInt("member_idd"), rs.getString("member_name"), rs.getString("member_email"), rs.getInt("member_telefon"), rs.getDate("member_fødselsdag").toLocalDate(), rs.getString("member_favSvømmeArt"), rs.getString("member_hold"), rs2.getDouble("member_kontigent"), rs.getBoolean("betalt_kontigent"));
                        tempMembers.put(id, temp);
                        return tempMembers;

                    }
                } else if (teamType.equals("Senior")) {
                    if (uid == id) {
                        SeniorMedlem temp = new SeniorMedlem(rs.getInt("member_idd"), rs.getString("member_name"), rs.getString("member_email"), rs.getInt("member_telefon"), rs.getDate("member_fødselsdag").toLocalDate(), rs.getString("member_favSvømmeArt"), rs.getString("member_hold"), rs2.getDouble("member_kontigent"), rs.getBoolean("betalt_kontigent"));
                        tempMembers.put(id, temp);
                        return tempMembers;

                    }
                } else {
                    if (uid == id) {
                        PensionistMedlem temp = new PensionistMedlem(rs.getInt("member_idd"), rs.getString("member_name"), rs.getString("member_email"), rs.getInt("member_telefon"), rs.getDate("member_fødselsdag").toLocalDate(), rs.getString("member_favSvømmeArt"), rs.getString("member_hold"), rs2.getDouble("member_kontigent"), rs.getBoolean("betalt_kontigent"));
                        tempMembers.put(id, temp);
                        return tempMembers;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
