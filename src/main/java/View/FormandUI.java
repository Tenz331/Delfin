package View;

import Model.Controller;

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
                        "\nInput:";
        System.out.println(PrintMainMenuText);
        int input = Integer.parseInt(scanner.nextLine());
        switch (input) {
            case 1:
                Controller.addMember();
                printMenu();
                break;
            case 2:
                Controller.editMember();
                printMenu();

                break;
            case 3:
                Controller.deleteMember();
                printMenu();

                break;
            case 4:
                Controller.seeRestance();
                printMenu();
                break;
            case 5:
                Controller.seeKontigenter();
                printMenu();
                break;
            case 6:
                Controller.editKontigenter();
                printMenu();
                break;
            case 7:
                Controller.top5Junior();
                printMenu();
                break;
            case 8:
                Controller.top5Senior();
                printMenu();
                break;
            case 9:
                Logout();
                printMenu();
                break;
            default:
                System.out.println("Error in input: '" + input + "'");
                printMenu();
        }
        System.out.println(PrintMainMenuText); //printer vores MainMenu
    }

    private void Logout() {

    }
}
