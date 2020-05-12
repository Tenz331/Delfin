import View.LoginView;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        //Starting MainController og vores MainMenu
        LoginView mainmenu = new LoginView(); // Instanciere main controller
        mainmenu.getUser(); // åbner main menu / main controller

    }
}
