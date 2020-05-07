package View;

import Util.DBConnect;

import java.sql.SQLException;
import java.util.Scanner;

public class MainController { //Vores main controller og Main Menu
    boolean sqlCheck = true; //boolean der bruges til: at søre for hvergang programmet retunere til vores main controller så  den ikke køre vores SQL check og update. Altså vi kan kontrolelre hvornår det skal ske 'initalizeSQLDB'
    Scanner userInput = new Scanner(System.in);
    OrdreController ordreController = new OrdreController();
    MenuController menucontroller = new MenuController();

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
                " Press: [1] To create member. \n" +
                " Press: [2] To show all orders. \n" +
                " Press: [3] To show only active orders. \n" +
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
                ordreController.getPizzas();
                ordreController.makeOrder();
                printMainMenu();
                break;
            case 2://vis alle ordre unanset status '0' - '1' (minus slettet 'flagged' '2')
                ordreController.showAllOrders();
                printMainMenu();
                break;
            case 3://vis alle ordre som er aktive '0' (minus slettet 'flagged' '2')
                ordreController.showAllActiveOrders();
                printMainMenu();
                break;
            case 4://ændre vores ordre altså setter den til 1 for complete
                ordreController.changeOrder();
                printMainMenu();
                break;
            case 5://slet ordre aka flag den til '2'
                ordreController.deleteOrder();
                printMainMenu();
                break;
            case 6: //Beregner det totale inkom baseret på data i vores DB
                ordreController.calculateIncome();
                printMainMenu();
                break;
            case 7: //finder forskellige statisiker baseret på vores DB DATA
                ordreController.Getstatistics();
                printMainMenu();
                break;
            case 8: //Laver en ny pizza til vores db menukort
                ordreController.getPizzas(); //for current pizzaere i vores menukort
                menucontroller.createPizza(); //laver pizza via svar fra bruger
                ordreController.clearMenuKort(); // sletter vores lokale data efter pizzaen er blevet lavet
                menucontroller.populateMenuKort(); // henter ny data fra DB til vores lokale data
                printMainMenu();
                break;
            case 9: //gør at brugeren kan updatere en pizza fra menukortet
                ordreController.getPizzas();//for current pizzaere i vores menukort
                menucontroller.updatePizza();//ændre en pizza via svar fra bruger
                ordreController.clearMenuKort(); // sletter vores lokale data efter pizzaen er blevet lavet
                menucontroller.populateMenuKort();// henter ny data fra DB til vores lokale data
                printMainMenu();
                break;
            case 404: //Skal slettes
                ordreController.devOption(); //404 ???? :^) hvis tess eller thor kommer til at køre denne metode sletter de vores database lol
                printMainMenu();
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
                menucontroller.populateMenuKort(); //Tilføjere pizzaere fra vores Db til program
                ordreController.mySQLUIDCheck(); // checker den højeste 'UID' og setter den som default value
                System.out.println("SQL connected and Checks complete");
            } catch (Exception e) {
                System.out.println("MYSQL ERROR! :" + e);
                sqlCheck = true;
            }


        }


    }
}
