package View;

import java.util.Scanner;

public class FormandController {
    public void printMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Logged in som: Formand\n");
        System.out.println("Formand Menu:\n");
        String PrintMainMenuText =
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
    }
}
