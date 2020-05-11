package Datamapper;

import Model.JuniorMedlem;
import Util.DBConnect;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberReadTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test

    public ArrayList<JuniorMedlem> MemberReadTest() {
        Connection connection = DBConnect.getInstance().getConnection();

        ArrayList<JuniorMedlem> tempMedlem = new ArrayList<JuniorMedlem>();
        public void getQueryJDBC () {
            try (
                    Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM Delfinen.Membership")
            ) {
                while (rs.next()) {

                    StringBuffer buffer = new StringBuffer();
                    buffer.append("Medlem: " + rs.getInt("medlems_id") + rs.getString("member_name") + rs.getString("member_email") + rs.getInt("member_telefon") + rs.getInt("member_fødselsdag" + rs.getString("member_favSvømmeart") + rs.getString("member_hold") + rs.getInt("member_kontingent")));
                    JuniorMedlem temp = new JuniorMedlem(rs.getString("name"), rs.getString("email"), rs.getInt("tlfNr"), rs.getDate("fodselsdag").toLocalDate(), rs.getString("favSvommeArt"), rs.getString("svommeHold"));
                    tempMedlem.add(temp);
                }

            } catch (SQLException e) {
                //Different error messages
                System.out.println(e);
            }
            return tempMedlem;
        }
    }
}
