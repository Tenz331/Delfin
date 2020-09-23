package Datamapper;

import Model.Members;
import Util.DBConnect;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberReadTest {
Connection connection = DBConnect.getInstance().getConnection();
ArrayList<Members> tempMember = new ArrayList<>();
    @Before
    public void setUp() throws Exception {
        try (
                Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM MariosPizza.pizzasMenu")
        ) {
            while (rs.next()) {
            }
        }
    }
    @Test
    public void getMember() {
    }
}