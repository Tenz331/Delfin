package View;

import java.util.Scanner;

public class FormandUI {
    public void printMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Logged in som: Formand\n");
        System.out.println("Formand Menu:\n");
        String PrintMainMenuText =
                " Skriv: [1] Tilmeld medlem. \n" +
                        " Skriv: [2] Rediger medlem. \n" +
                        " Skriv: [3] Slet medlem \n" +
                        " Skriv: [4] Se restance \n" +
                        " Skriv: [5] Se kontingenter. \n" +
                        " Skriv: [6] Ændre kontigenter. \n" +
                        " Skriv: [7] Top 5 Juinior. \n " +
                        "Skriv: [8] Top 5 Senior. \n " +
                        "Skriv: [9] Logout.\n " +
                        "Skriv: [404] FOR DEV OPTIONS" +
                        "\nInput:";
        System.out.println(PrintMainMenuText);
        int input = Integer.parseInt(scanner.nextLine());
        switch (input) {
            case 1:

                printMenu();
                break;
            case 2:

                printMenu();

                break;
            case 3:

                printMenu();

                break;
            case 4:

                printMenu();
                break;
            case 5:

                printMenu();
                break;
            case 6:

                printMenu();
                break;
            case 7:

                printMenu();
                break;
            case 8:

                printMenu();
                break;
            case 9:

                printMenu();
                break;
            default:
                System.out.println("Error in input: '"+ input+"'");
                printMenu();
        }
        System.out.println(PrintMainMenuText); //printer vores MainMenu
    }
}
