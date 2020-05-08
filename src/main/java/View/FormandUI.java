package View;

import Model.controller;

import java.util.Scanner;

public class FormandUI {

    public void printMenu() {
        controller controller = new controller();
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
                        "\nInput:";
        System.out.println(PrintMainMenuText);
        int input = Integer.parseInt(scanner.nextLine());
        switch (input) {
            case 1:
                controller.addMember();
                printMenu();
                break;
            case 2:
                controller.editMember();
                printMenu();

                break;
            case 3:
                controller.deleteMember();
                printMenu();

                break;
            case 4:
                controller.seeRestance();
                printMenu();
                break;
            case 5:
                controller.seeKontigenter();
                printMenu();
                break;
            case 6:
                controller.editKontigenter();
                printMenu();
                break;
            case 7:
                controller.top5Junior();
                printMenu();
                break;
            case 8:
                controller.top5Senior();
                printMenu();
                break;
            case 9:
                System.out.println("Goodbye");
                System.exit(0);
                break;
            default:
                System.out.println("Error in input: '" + input + "'");
                printMenu();
        }
        System.out.println(PrintMainMenuText); //printer vores MainMenu
    }

}
