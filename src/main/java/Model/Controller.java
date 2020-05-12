package Model;


import Datamapper.MemberRead;
import Datamapper.MemberWrite;


import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    MemberRead MemberRead = new MemberRead();
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
            JuniorMedlem newJunior = new JuniorMedlem(uId,tempNavn,tempEmail,tempTlfNr,birthDate,tempFavSvommeArt,tempSvommeHold,tempkontingent);
            teams.addNewMember(newJunior);
            memberWrite.setMember(newJunior);

        } else if (age <= 59) {
            tempSvommeHold = "Senior";
            SeniorMedlem newSenior = new SeniorMedlem(uId,tempNavn,tempEmail,tempTlfNr,birthDate,tempFavSvommeArt,tempSvommeHold,tempkontingent);
            teams.addNewMember(newSenior);
            memberWrite.setMember(newSenior);

        } else {
            tempSvommeHold = "Pensonist";
            PensionistMedlem  newPensionist = new PensionistMedlem(uId,tempNavn,tempEmail,tempTlfNr,birthDate,tempFavSvommeArt,tempSvommeHold,tempkontingent);
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

    public void seeRestance() {
    }

    public void deleteMember() {

    }

    public void editMember() {
        System.out.println("\nWhat Member Do you want to change?");
        int tempNewID = Integer.parseInt(scanner.nextLine());
        System.out.println("\nMember To be changed:");
        getSpecificMember(tempNewID); //finder den member brugeren inputter
        System.out.println("\nWhat do you want to do?");
        System.out.println("[1] Update member team\n[2] Update member kontingent");
        int input = Integer.parseInt(scanner.nextLine());
        System.out.println("\nEnter Change: ");
        int change = Integer.parseInt(scanner.nextLine());
        System.out.println("Updating member..#" + tempNewID);
        modifyMember(tempNewID, input, change);
    }

    public void getSpecificMember(int tempNewID) {
        Map<Integer, Members> teams = new HashMap<>();
       teams = MemberRead.getMember();

        for(Map.Entry<Integer, Members> entry: teams.entrySet()){
            Integer test1 = entry.getKey();
            Members test2 = entry.getValue();
            if (test2.unicID == tempNewID) {
                System.out.println("#" + test2.unicID + " " + test2.kontingent + " KR.");
            }
        }
    }

    public void modifyMember(int tempNewID, int input, int change) {
        switch (input) {
            case 0://update member team
                try {
                    //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
                    String query = "UPDATE Delfinen.Membership SET member_hold = '" + change + "' WHERE member_idd =" + tempNewID;
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.execute();
                    connection.close();
                    System.out.println("Member-ID #" + tempNewID + " Has been changed with the value: " + change);
                } catch (Exception e) {
                    System.out.println("ERROR! :" + e);
                    System.out.println("Make sure your input is correct");
                }
                break;
            case 1://update kontingent
                try {
                    int kontingent = Integer.parseInt(String.valueOf(change));
                    //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
                    String query = "UPDATE Delfinen.Membership SET member_kontingent = '" + kontingent + "' WHERE member_idd =" + tempNewID;
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.execute();
                    connection.close();
                    System.out.println("Member ID #" + tempNewID + " Has been changed with the value: " + change);
                } catch (Exception e) {
                    System.out.println("ERROR! :" + e);
                    System.out.println("Make sure your input is correct");
                }
                break;
        }
    }
    public void setMember() {
        Teams.teams = MemberRead.getMember();
        }
    }
