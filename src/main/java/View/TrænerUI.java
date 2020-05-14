package View;

import Model.Controller;

import java.util.Scanner;

public class TrænerUI {
    Controller controller = new Controller();
    public void populateMember(){
        controller.setMember();
    }
    public void printMenu() {
        populateMember();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Logged in som: Træner\n");
        System.out.println("Træner Menu:\n");
        String PrintMainMenuText = " Skriv: [1] Top 5 Juinior. \n" +
                " Skriv: [2] Top 5 Senior. \n" +
                " Skriv: [3] For at tilføjse konkurrence data.\n " +
                " Skriv: [4] exit.\n " +
                "\nInput:";
        System.out.println(PrintMainMenuText);
        int input = Integer.parseInt(scanner.nextLine());
        switch (input) {
            case 1:
                controller.top5Junior();
                printMenu();
                break;
            case 2:
                controller.top5Senior();
                printMenu();
                break;
            case 3:
                controller.updateKonkurrence();
                printMenu();
                break;
            case 4:
                System.out.println("Goodbye");
                System.exit(0);
                break;
            default:
                System.out.println("Error in input: '" + input + "'");
                printMenu();
        }
    }
}
