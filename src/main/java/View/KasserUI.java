package View;

import java.util.Scanner;

public class KasserUI {
    public void printMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Logged in som: Kassêr\n");
        System.out.println("Kassêr Menu:\n");
        String PrintMainMenuText = " Skriv: [1] Se restance \n" +
                        " Skriv: [2] Se kontingenter. \n" +
                        " Skriv: [3] Ændre kontigenter. \n" +
                        "Skriv: [4] Logout.\n " +
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
            default:
                break;
        }
    }
}
