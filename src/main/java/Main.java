import View.MainView;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        //Starting MainController og vores MainMenu
        MainView mainmenu = new MainView(); // Instanciere main controller
        mainmenu.getUser(); // åbner main menu / main controller
    }
}
