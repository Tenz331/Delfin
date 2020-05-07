package Datamapper;



import java.sql.*;
import java.util.ArrayList;

public class MemberRead {
    ArrayList<String> tempPizzas = new ArrayList<String>();
    private static final String USERNAME = "fullroot";
    private static final String PASSWORD = "fullroot";
    private static final String CONN_STR = "jdbc:mysql://cphb-gruppe1.c4mqzn3xlkdy.us-east-2.rds.amazonaws.com/";
    public ArrayList<String> getQueryJDBC() {
        //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
        try (
                Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = ((Statement) stmt).executeQuery("SELECT * FROM MariosPizza.pizzasMenu")
        ) {
            while (rs.next()) {

                StringBuffer buffer = new StringBuffer();
                buffer.append("Pizza: " + rs.getInt("pizza_id") + " : " + rs.getString("pizza_name") + ", Price: " + rs.getDouble("pizza_price"));
                String temp = new String(("pizza_name"));
                tempPizzas.add(temp);
            }
        } catch (SQLException e) {
            //Different error messages
            System.out.println(e);
        }
        return tempPizzas;
    }
}
