package View;

import java.util.Scanner;

public class TrænerUI {
    public void printMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Logged in som: Træner\n");
        System.out.println("Træner Menu:\n");
        String PrintMainMenuText = " Skriv: [1] Top 5 Juinior. \n" +
                " Skriv: [2] Top 5 Senior. \n" +
                " Skriv: [3] Register best time.\n " +
                " Skriv: [4] exit.\n " +
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
                break;
            default:

                printMenu();
                break;
        }
    }
}
