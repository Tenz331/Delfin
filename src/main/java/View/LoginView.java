package View;
import Util.DBConnect;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginView { //Vores main controller og Main Menu
    public static String dBPassword;
    public static String dBUser;
    boolean sqlCheck = true; //boolean der bruges til: at søre for hvergang programmet retunere til vores main controller så  den ikke køre vores SQL check og update. Altså vi kan kontrolelre hvornår det skal ske 'initalizeSQLDB'
    Scanner userInput = new Scanner(System.in);
    public void LoginSQLDB() {
        if (sqlCheck == true) { // check for at gøre at koden kun bliver kørt en gang per start.
            try {
                String loGo =
                        "            YAao,\n"+
                        "            Y8888b,\n"+
                        "           ,oA8888888b,\n"+
                        "         ,aaad8888888888888888bo,\n"+
                        "        ,d888888888888888888888888888b,\n"+
                        "       ,888888888888888888888888888888888b,\n"+
                        "      d8888888888888888888888888888888888888,\n"+
                        "     d888888888888888888888888888888888888888b\n"+
                        "    d888888P'                    `Y888888888888,\n"+
                        "   88888P'                      Ybaaaa8888888888l\n"+
                        "   a8888'                        `Y8888P' `V888888\n"+
                        "  d8888888a                                  `Y8888\n"+
                        " AY/'' `\\Y8b                                  ``Y8b\n"+
                        " Y'      `YP                                       ~~\n"+
                        "Gruppe '1': Emil, Janus, Mathias\n"+
                        " Formand login: user: formand password: formand\n træner: u: træner p: træner \n kassêr: u:kasser p: kasser";
                sqlCheck = false;
                System.out.println(loGo);
                String tempuser;
                System.out.println("Please Login:\n");
                System.out.println("username:");
                tempuser = userInput.nextLine();
                System.out.println("password:");
                dBPassword = userInput.nextLine();
                System.out.println("Attempting to login...\n");
                dBUser = DBConnect.Login(tempuser, dBPassword); // Checking Connection
            } catch (Exception e) {
                System.out.println("ERROR! :" + e);
                dBUser = "";
                dBPassword = "";
                sqlCheck = true;
            }


        }


    }

    public void getUser() throws SQLException {
        LoginSQLDB();
        DBConnect.getInstance();
        switch (dBUser.toLowerCase()) {
            case "formand":
                FormandUI formand = new FormandUI();
                formand.printMenu();
                break;
            case "træner":
                TrænerUI trænerUI = new TrænerUI();
                trænerUI.printMenu();
                break;
            case "kasser":
                KasserUI kasserUI = new KasserUI();
                kasserUI.printMenu();
            default:
                sqlCheck = true;
                LoginSQLDB();
                break;

        }
    }

}
