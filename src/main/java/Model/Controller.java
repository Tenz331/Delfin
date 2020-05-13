package Model;


import Datamapper.MemberRead;
import Datamapper.MemberWrite;


import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Controller {
    Teams teams = new Teams();
    Scanner scanner = new Scanner(System.in);
    String tempNavn, tempEmail, tempFavSvommeArt, tempSvommeHold;
    int age, tempTlfNr, tempInput;
    double tempkontingent = 0;
    LocalDate birthDate;
    MemberWrite memberWrite = new MemberWrite();
    Connection connection;
    MemberRead memberRead = new MemberRead();
    static int uId = 0;

    private int generateUnicId() {
        uId++;
        return uId;
    }

    public void addMember() {
        System.out.println(LocalDate.now());
        uId = generateUnicId();

        System.out.println("Tilføj et nyt medlem:\n");
        System.out.println("Meldem navn:\n");
        tempNavn = scanner.nextLine();
        System.out.println("\nEmail:");
        tempEmail = scanner.nextLine();
        System.out.println("\nFødelsdato: ÅÅ-MM-DD:");
        birthDate = LocalDate.parse(scanner.nextLine());
        age = LocalDate.now().getYear() - birthDate.getYear();
        System.out.println("Svømme Art: \n [1]. Crawl, [2]. brøstsvømming, [3]. rykcrawl, [4]. butterfly");
        try {
            tempInput = Integer.parseInt(scanner.nextLine());
            switch (tempInput) {
                case 1:
                    tempFavSvommeArt = "Crawl";
                    break;
                case 2:
                    tempFavSvommeArt = "BrystSvømning";
                    break;
                case 3:
                    tempFavSvommeArt = "RykCrawl";
                    break;
                case 4:
                    tempFavSvommeArt = "Butterfly";
                    break;
                default:
                    addMember();
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
            addMember();
        }
        System.out.println("\ntelefon Nr:");
        tempTlfNr = Integer.parseInt(scanner.nextLine());
        setTeam(age);

    }


    private void setTeam(int age) {
        System.out.println(age);
        if (age <= 18) {
            tempSvommeHold = "Junior";
            JuniorMedlem newJunior = new JuniorMedlem(uId, tempNavn, tempEmail, tempTlfNr, birthDate, tempFavSvommeArt, tempSvommeHold, tempkontingent, false);
            teams.addNewMember(newJunior);
            memberWrite.setMember(newJunior);

        } else if (age <= 59) {
            tempSvommeHold = "Senior";
            SeniorMedlem newSenior = new SeniorMedlem(uId, tempNavn, tempEmail, tempTlfNr, birthDate, tempFavSvommeArt, tempSvommeHold, tempkontingent, false);
            teams.addNewMember(newSenior);
            memberWrite.setMember(newSenior);

        } else {
            tempSvommeHold = "Pensonist";
            PensionistMedlem newPensionist = new PensionistMedlem(uId, tempNavn, tempEmail, tempTlfNr, birthDate, tempFavSvommeArt, tempSvommeHold, tempkontingent, false);
            teams.addNewMember(newPensionist);
            memberWrite.setMember(newPensionist);
        }

        System.out.println("ny " + tempSvommeHold + " tilføjet:\nNavn: " + tempNavn + ", Email: " + tempEmail + ", TLF: " + tempTlfNr + ", fødselsdag: " + birthDate + ", Svømme art: " + tempFavSvommeArt + ", Hold: " + tempSvommeHold);


    }

    public void top5Senior() {
    }

    public void top5Junior() {
    }

    public void editKontigenter() {
    }

    public void seeKontigenter() {
    }

    public void seeRestance() throws SQLException {
        System.out.println(memberRead.checkRestance());
    }

    public void deleteMember() {
        System.out.println(memberRead.getMember());
        System.out.println("\nWhat Member Do you want to change? >ID#");
        int tempNewID = Integer.parseInt(scanner.nextLine());
        System.out.println("\nMember To be changed:");
        getSpecificMember(tempNewID); //finder den member brugeren inputter
        System.out.println("\nCorrect?\n[1] Yes,\n[2] No, \n[3] Exit.");
        int userInput = Integer.parseInt(scanner.nextLine());
        switch (userInput){
            case 1:
                memberWrite.deleteMember(tempNewID);
                break;
            case 2:
                deleteMember();
                break;
            default:
                System.out.println("error in input~");
                break;
        }

    }

    public void editMember() {
        System.out.println(memberRead.getMember());
        System.out.println("\nWhat Member Do you want to change? >ID#");
        int tempNewID = Integer.parseInt(scanner.nextLine());
        System.out.println("\nMember To be changed:");
        getSpecificMember(tempNewID); //finder den member brugeren inputter
        System.out.println("\nWhat do you want to do?");
        System.out.println("[1] Update member team\n[2] Update member kontingent");
        int input = Integer.parseInt(scanner.nextLine());
        System.out.println("\nEnter Change: ");
        String change = scanner.nextLine();
        System.out.println("Updating member..#" + tempNewID);
        modifyMember(tempNewID, input, change);
    }

    public void registerBestTime() {

        System.out.println(memberRead.getMember());
        System.out.println("\nWhat Member Do you want to update?");
        int tempNewID = Integer.parseInt(scanner.nextLine());
        Members tempmemberlol = getSpecificMember(tempNewID); //finder den member brugeren inputter
        System.out.println("\nWhat is the members best time?");
        String bestTime = scanner.nextLine();
        memberWrite.updateTeam(tempNewID, bestTime, tempmemberlol);


    }

    public Members getSpecificMember(int tempNewID) {
        Map<Integer, Members> teams;
        teams = memberRead.getMember();

        for (Map.Entry<Integer, Members> entry : teams.entrySet()) {
            Members userInfomation = entry.getValue();
            if (userInfomation.unicID == tempNewID) {
                System.out.println("#" + userInfomation.unicID + ", navn: " + userInfomation.name + ", Kontigent: " + userInfomation.kontingent + " KR.");
            }
            return userInfomation;
        }
        return null;
    }

    public void modifyMember(int tempNewID, int input, String change) {
        switch (input) {
            case 1://update member team
                //memberWrite.updateMember(tempNewID, change);

                break;
            case 2://update kontingent
                memberWrite.updateKontigent(tempNewID, change);
                break;
        }
    }

    public void setMember() {
        Teams.teams = memberRead.getMember();
        uId = memberRead.getMember().size();

    }


}
