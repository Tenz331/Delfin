package View;

import Model.Controller;

import java.sql.SQLException;
import java.util.Scanner;

public class FormandUI {
    Controller controller = new Controller();

    public void printMenu() throws SQLException {
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
                        " Skriv: [6] Top 5 Juinior. \n " +
                        "Skriv: [7] Top 5 Senior. \n " +
                        "Skriv: [8] For at tilføjse konkurrence data. \n " +
                        "Skriv: [9] exit.\n " +
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
                controller.registerBestTime();
                printMenu();
                break;
            case 5:
                controller.seeRestance();
                printMenu();
                break;
            case 6:
                controller.top5Junior();
                printMenu();
                break;
            case 7:
                controller.top5Senior();
                printMenu();
                break;
            case 8:
                controller.updateKonkurrence();
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

    public void populateMember() {
        controller.setMember();
    }

}
