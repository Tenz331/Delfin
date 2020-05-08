package Model;


import Datamapper.MemberWrite;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

public class controller {
    Scanner scanner = new Scanner(System.in);
    String tempNavn, tempEmail, tempFavSvommeArt, tempSvommeHold;
    int age, tempTlfNr, tempInput;
    double tempKontigent;
    LocalDate birthDate;
    MemberWrite memberWrite = new MemberWrite();

    public void addMember() {
        System.out.println(LocalDate.now());


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
        tempTlfNr =  Integer.parseInt(scanner.nextLine());
        setTeam(age);

    }



    private void setTeam(int age) {
        System.out.println(age);
        if (age <=18){
            tempSvommeHold = "Junior";
            JuniorMedlem newJunior = new JuniorMedlem(tempNavn,tempEmail,tempTlfNr,birthDate,tempFavSvommeArt,tempSvommeHold);
            memberWrite.setMember(newJunior);

        }
        else if(age <=59){
            tempSvommeHold = "Senior";
            SeniorMedlem newSenior = new SeniorMedlem(tempNavn,tempEmail,tempTlfNr,birthDate,tempFavSvommeArt,tempSvommeHold);
            memberWrite.setMember(newSenior);

        }
        else {
            tempSvommeHold = "Pensonist";
            PensionistMedlem  newPensionist = new PensionistMedlem(tempNavn,tempEmail,tempTlfNr,birthDate,tempFavSvommeArt,tempSvommeHold);
            memberWrite.setMember(newPensionist);
        }

        System.out.println("ny "+ tempSvommeHold+ " tilføjet:\nNavn: "+tempNavn + ", Email: "+ tempEmail +", TLF: "+ tempTlfNr +", fødselsdag: "+ birthDate + ", Svømme art: "+ tempFavSvommeArt + ", Hold: "+ tempSvommeHold);


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
    }
}
