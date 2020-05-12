package Datamapper;

import Model.JuniorMedlem;
import Model.Members;
import Model.PensionistMedlem;
import Model.SeniorMedlem;
import Util.DBConnect;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class MemberRead {
    Connection connection = DBConnect.getInstance().getConnection();
    int tempcounter = 0;


    Map<Integer, Members> tempMembers = new HashMap<>();

    public Map<Integer, Members> getMember() {
        try (
                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM Delfinen")
        ) {
            while (rs.next()) {
                tempcounter++;
                StringBuffer buffer = new StringBuffer();
                String teamType = rs.getString("member_hold");
                if (teamType.equals("Junior")) {
                    JuniorMedlem temp = new JuniorMedlem(rs.getInt("unicID"), rs.getString("name"), rs.getString("email"), rs.getInt("tlfNr"), rs.getDate("fodselsdag").toLocalDate(), rs.getString("favSvommeArt"), rs.getString("svommeHold"));
                    tempMembers.put(tempcounter, temp);

                } else if (teamType.equals("Senior")) {
                    SeniorMedlem temp = new SeniorMedlem(rs.getInt("unicID"), rs.getString("name"), rs.getString("email"), rs.getInt("tlfNr"), rs.getDate("fodselsdag").toLocalDate(), rs.getString("favSvommeArt"), rs.getString("svommeHold"));
                    tempMembers.put(tempcounter, temp);

                } else {
                    PensionistMedlem temp = new PensionistMedlem(rs.getInt("unicID"), rs.getString("name"), rs.getString("email"), rs.getInt("tlfNr"), rs.getDate("fodselsdag").toLocalDate(), rs.getString("favSvommeArt"), rs.getString("svommeHold"));
                    tempMembers.put(tempcounter, temp);

                }

                buffer.append("Member:" + rs.getInt("member_idd") + rs.getString("member_name") + rs.getString("member_email") + rs.getInt("member_telefon") + rs.getInt("member-fødselsdag") + rs.getString("member_favSvømmeart") + rs.getString("member_hold") + rs.getInt("member_kontingent"));


            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return tempMembers;
    }

    public void getDisciplin() {

    }

    public void getResult() {

    }


}