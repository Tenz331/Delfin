package View;

import Model.Controller;

import java.sql.SQLException;
import java.util.Scanner;

public class KasserUI {
    Controller controller = new Controller();
    public void populateMember(){
        controller.setMember();
    }
    public void printMenu() throws SQLException {
        populateMember();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Logged in som: Kassêr\n");
        System.out.println("Kassêr Menu:\n");
        String PrintMainMenuText = " Skriv: [1] Se restance \n" +
                        " Skriv: [2] Rediger medlem. \n" +
                        "Skriv: [3] exit.\n " +
                        "\nInput:";
        System.out.println(PrintMainMenuText);
        int input = Integer.parseInt(scanner.nextLine());
        switch (input) {
            case 1:
                controller.seeRestance();
                printMenu();
                break;
            case 2:
                controller.editMember();
                printMenu();
                break;
            case 3:
                System.out.println("Goodbye");
                System.exit(0);
                break;
            default:
                System.out.println("Error in input: '" + input + "'");
                printMenu();

        }
    }
}
