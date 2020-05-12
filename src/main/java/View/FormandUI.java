package View;

import Model.Controller;

import java.util.Scanner;

public class FormandUI {
    Controller controller = new Controller();
    public void printMenu() {
        populateMember();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Logged in som: Formand\n");
        System.out.println("Formand Menu:\n");
        String PrintMainMenuText =
                " Skriv: [1] Tilmeld medlem. \n" +
                        " Skriv: [2] Rediger medlem. \n" +
                        " Skriv: [3] Slet medlem. \n" +
                        " Skriv: [4] Register best time.\n" +
                        " Skriv: [5] Se restance. \n" +
                        " Skriv: [6] Se kontingenter. \n" +
                        " Skriv: [7] Ændre kontigenter. \n" +
                        " Skriv: [8] Top 5 Juinior. \n " +
                        "Skriv: [9] Top 5 Senior. \n " +
                        "Skriv: [10] exit.\n " +
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
                System.out.println("Goodbye");
                System.exit(0);
                break;
            case 10:
                System.out.println("Goodbye");
                System.exit(0);
                break;
            default:
                System.out.println("Error in input: '" + input + "'");
                printMenu();
        }
        System.out.println(PrintMainMenuText); //printer vores MainMenu
    }
    public void populateMember(){
        controller.setMember();
    }

}
