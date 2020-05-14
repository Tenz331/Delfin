package Model;


import Datamapper.MemberRead;
import Datamapper.MemberWrite;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class Controller {
    static int uId = 0;
    Teams teams = new Teams();
    Scanner scanner = new Scanner(System.in);
    String tempNavn, tempEmail, tempFavSvommeArt, tempSvommeHold;
    int age, tempTlfNr, tempInput;
    double tempkontingent = 0;
    LocalDate birthDate;
    MemberWrite memberWrite = new MemberWrite();
    Connection connection;
    MemberRead memberRead = new MemberRead();

    private int generateUnicId() {
        uId++;
        return uId;
    }

    public void addMember() {
        uId = generateUnicId();

        System.out.println("Tilføj et nyt medlem:\n");
        System.out.println("Medlem navn:\n");
        tempNavn = scanner.nextLine();
        System.out.println("\nEmail:");
        tempEmail = scanner.nextLine();
        System.out.println("\nFødelsdato: ÅÅ-MM-DD:");
        birthDate = LocalDate.parse(scanner.nextLine());
        age = LocalDate.now().getYear() - birthDate.getYear();
        System.out.println("Svømme Art: \n [1]. Crawl, [2]. Brystsvømming, [3]. Rygcrawl, [4]. Butterfly");
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
        memberRead.top5Senior();
    }

    public void top5Junior() {
        memberRead.top5Junior();
    }

    public void editKontigenter() {
    }

    public void seeRestance() throws SQLException {
        System.out.println(memberRead.checkRestance());
    }

    public void deleteMember() {
        System.out.println(memberRead.getMember());
        System.out.println("\nWhat Member Do you want to change? >ID#");
        int tempNewID = scanner.nextInt();
        System.out.println("\nMember To be changed:");
        getSpecificMember(tempNewID); //finder den member brugeren inputter
        System.out.println("\nCorrect?\n[1] Yes,\n[2] No, \n[3] Exit.");
        int userInput = scanner.nextInt();
        switch (userInput) {
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
        int tempNewID = scanner.nextInt();
        System.out.println("\nMember To be changed:");
        System.out.println(getSpecificMember(tempNewID)); //finder den member brugeren inputter
        System.out.println("\nWhat do you want to do?");
        System.out.println("[1] Update member team\n[2] Update member kontingent\n[3] update member active/inactive");
        int input = scanner.nextInt();
        Members members = getSpecificMember(tempNewID);
        switch (input) {
            case 1:
                LocalDate age = members.fodselsdag;
                int ageCalc = LocalDate.now().getYear() - age.getYear();
                if (ageCalc <= 18) {
                    tempSvommeHold = "Junior";
                    memberWrite.updateMember(members, tempSvommeHold, 1);

                } else if (ageCalc <= 59) {
                    tempSvommeHold = "Senior";
                    memberWrite.updateMember(members, tempSvommeHold, 1);

                } else {
                    tempSvommeHold = "Pensonist";
                    memberWrite.updateMember(members, tempSvommeHold, 1);
                }
                System.out.println(members.name + "er blevet ændret til " + tempSvommeHold);
                break;

            case 2:
                System.out.println("Tryk 0 for ikke betalt kontingent. Tryk 1 for betalt kontingent.");
                int userInput = scanner.nextInt();

                memberWrite.updateKontingentActive(members, userInput);
                break;
            case 3:
                System.out.println("Tryk 0 for at gøre bruger inaktiv. Tryk 1 for at gøre bruger aktiv");
                 userInput = scanner.nextInt();
                memberWrite.updateMemberActive(members, userInput);

                break;
            default:
                break;
        }


    }

    public void updateKonkurrence() {
        System.out.println(memberRead.getMember());
        System.out.println("\nHvilken medlem skal tilføjes til konkurrence >ID#");
        int tempNewID = scanner.nextInt();
        System.out.println("\nMember To be changed:");
        Members member = getSpecificMember(tempNewID); //finder den member brugeren inputter
        System.out.println("\nHvilken type konkurrence er det?");
        scanner.nextLine();
        String type = scanner.nextLine();
        System.out.println("\nHvor blev konkurrencen afholdt?");
        String konkurrencenLocation = scanner.nextLine();
        System.out.println("\nHvad var " + member.name + " tid?");
        String tid = scanner.nextLine();
        memberWrite.addKonkurrence(tid, type, konkurrencenLocation, member);
        System.out.println("added");


    }

    public void registerBestTime() {

        System.out.println(memberRead.getMember());
        System.out.println("\nHvilket medlem vil du opdatere?");
        int tempNewID = scanner.nextInt();
        Members tempmemberlol = getSpecificMember(tempNewID); //finder den member brugeren inputter
        System.out.println("Hvilken turnerings disciplin?");
        String disciplin = scanner.nextLine();
        System.out.println("Hvor blev turneringen afholdt?");
        String lokation = scanner.nextLine();
        System.out.println("\nHvad er medlemmets bedste tid?");
        double bestTime = scanner.nextDouble();
        memberWrite.updateTeam(tempNewID, bestTime, tempmemberlol, disciplin, lokation);


    }

    public Members getSpecificMember(int tempNewID) {
        Map<Integer, Members> teams;
        teams = memberRead.getSpecificMember(tempNewID);

        for (Map.Entry<Integer, Members> entry : teams.entrySet()) {
            Members userInfomation = entry.getValue();
            if (tempNewID == userInfomation.unicID) {
                System.out.println("#" + userInfomation.unicID + ", navn: " + userInfomation.name + ", Kontigent: " + userInfomation.kontingent + " KR.");
            }
            return userInfomation;
        }
        return null;
    }

    public void setMember() {
        Teams.teams = memberRead.getMember();
        uId = memberRead.getMaxUid();

    }

}
