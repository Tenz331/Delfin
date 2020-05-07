package View;
import Util.DBConnect;
import java.util.Scanner;

public class MainView { //Vores main controller og Main Menu
    public static String dBPassword;
    public static String dBUser;
    boolean sqlCheck = true; //boolean der bruges til: at søre for hvergang programmet retunere til vores main controller så  den ikke køre vores SQL check og update. Altså vi kan kontrolelre hvornår det skal ske 'initalizeSQLDB'
    Scanner userInput = new Scanner(System.in);

    public void initalizeSQLDB() {
        if (sqlCheck == true) { // check for at gøre at koden kun bliver kørt en gang per start.
            try {
                sqlCheck = false;
                String tempuser;
                System.out.println("login screen.");
                System.out.println("username:");
                tempuser = userInput.nextLine();
                System.out.println("password:");
                dBPassword = userInput.nextLine();
                System.out.println("Attempting to login...\n");
                dBUser = DBConnect.Login(tempuser, dBPassword); // Checking Connection
                DBConnect.getInstance();
            } catch (Exception e) {
                System.out.println("MYSQL ERROR! :" + e);
                dBUser = ""; dBPassword = "";
                sqlCheck = true;
            }


        }


    }

    public void getUser() {
        initalizeSQLDB();
        switch (dBUser) {
            case "fullroot":
                FormandUI formand = new FormandUI();
                formand.printMenu();
                break;
            case "traener":
                TrænerUI trænerUI = new TrænerUI();
                trænerUI.printMenu();
                break;
            case "kasser":
                KasserUI kasserUI = new KasserUI();
                kasserUI.printMenu();
            default:
                System.out.println("error in Username");
                break;

        }
    }
}
