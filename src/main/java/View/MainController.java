package View;

import Util.DBConnect;

import java.sql.SQLException;
import java.util.Scanner;

public class MainController { //Vores main controller og Main Menu
    boolean sqlCheck = true; //boolean der bruges til: at søre for hvergang programmet retunere til vores main controller så  den ikke køre vores SQL check og update. Altså vi kan kontrolelre hvornår det skal ske 'initalizeSQLDB'
    Scanner userInput = new Scanner(System.in);
    public void printMainMenu() throws SQLException {
        initalizeSQLDB();//åbner og checker mysql forbindelse
        String PrintMainMenuText =
                     "YAao,\n"+
                    "Y8888b,\n"+
                   ",oA8888888b,\n"+
                 ",aaad8888888888888888bo,\n"+
               ",d888888888888888888888888888b,\n"+
              ",888888888888888888888888888888888b,\n"+
              "d8888888888888888888888888888888888888,\n"+
             "d888888888888888888888888888888888888888b\n"+
           " d888888P'                    `Y888888888888,\n"+
           "88888P'                      Ybaaaa8888888888l\n"+
          "a8888'                        `Y8888P' `V888888\n"+
         "d8888888a                                  `Y8888\n"+
        "AY/'' `\\Y8b                                  ``Y8b\n"+
        "Y'      `YP                                       ~~\n"+
               "\n Svømmeklubben Delfinen Menu\n" +
                " Press: [1] Tilmeld medlem. \n" +
                " Press: [2] Rediger medlem. \n" +
                " Press: [3] Slet medlem \n" +
                " Press: [4] To change an active order to status 'complete'. \n" +
                " Press: [5] To delete an order. \n" +
                " Press: [6] To calculate total earnings. \n" +
                " Press: [7] To Show statistics. \n " +
                "Press: [8] To add a new pizza to the menu. \n " +
                "Press: [9] To update a pizza from the menu.\n " +
                "Press: [404] FOR DEV OPTIONS";

        System.out.println(PrintMainMenuText); //printer vores MainMenu
        System.out.print("\nEnter your option: ");
        String userOption = userInput.nextLine();
        int choice = Integer.parseInt(userOption);
        switch (choice) {
            case 1://lav ordre
                printMainMenu();
                break;
            case 2://vis alle ordre unanset status '0' - '1' (minus slettet 'flagged' '2')
                printMainMenu();
                break;
            case 3://vis alle ordre som er aktive '0' (minus slettet 'flagged' '2')
                printMainMenu();
                break;
            case 4://ændre vores ordre altså setter den til 1 for complete
                printMainMenu();
                break;
            case 5://slet ordre aka flag den til '2'
                printMainMenu();
                break;
            case 6: //Beregner det totale inkom baseret på data i vores DB
                printMainMenu();
                break;
            case 7: //finder forskellige statisiker baseret på vores DB DATA

                printMainMenu();
                break;
            case 8: //Laver en ny pizza til vores db menukort

                printMainMenu();
                break;
            case 9: //gør at brugeren kan updatere en pizza fra menukortet

                printMainMenu();
                break;
            case 404: //Skal slettes

                break;

            default:
                printMainMenu();
                break;


        }

    }

    private void initalizeSQLDB() {
        if (sqlCheck == true) { // check for at gøre at koden kun bliver kørt en gang per start.
            try {
                sqlCheck = false;
                System.out.println("Attempting to Instance Database...");
                DBConnect.getInstance(); // Checking Connection
                System.out.println("SQL connected and Checks complete");
            } catch (Exception e) {
                System.out.println("MYSQL ERROR! :" + e);
                sqlCheck = true;
            }


        }


    }
}
