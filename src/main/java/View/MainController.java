package View;

import Util.DBConnect;

import java.util.Scanner;

public class MainController { //Vores main controller og Main Menu
    String user;
    boolean sqlCheck = true; //boolean der bruges til: at søre for hvergang programmet retunere til vores main controller så  den ikke køre vores SQL check og update. Altså vi kan kontrolelre hvornår det skal ske 'initalizeSQLDB'
Scanner userInput = new Scanner(System.in);


    private void initalizeSQLDB(){
        if (sqlCheck == true) { // check for at gøre at koden kun bliver kørt en gang per start.
            try {
                sqlCheck = false;
                String username;
                String password;
                System.out.println("login screen.");
                System.out.println("username:");
                username = userInput.nextLine();
                System.out.println("password:");
                password = userInput.nextLine();
                System.out.println("Attempting to login...\n");
                user = DBConnect.Login(username, password); // Checking Connection
                DBConnect.getInstance();
            } catch (Exception e) {
                System.out.println("MYSQL ERROR! :" + e);
                sqlCheck = true;
            }


        }


    }

    public void getUser() {
        initalizeSQLDB();
        switch (user){
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
                System.out.println("error in user"+ user);
                break;

        }
    }
}
