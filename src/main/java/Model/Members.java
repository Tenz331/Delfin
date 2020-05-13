package Model;

import java.time.LocalDate;
import java.util.Date;

public abstract class Members {
    int unicID;
    String name;
    String email;
    int tlfNr;
    LocalDate fodselsdag;
    String favSvommeArt;
    String svommeHold;
    double kontingent;
    boolean restance;

    public Members(int unicID, String name, String email, int tlfNr, LocalDate fodselsdag, String favSvommeArt, String svommeHold,double kontingent, boolean restance) {
        this.unicID = unicID;
        this.name = name;
        this.email = email;
        this.tlfNr = tlfNr;
        this.fodselsdag = fodselsdag;
        this.favSvommeArt = favSvommeArt;
        this.svommeHold = svommeHold;
        this.kontingent = kontingent;
        this.restance = restance;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getTlfNr() {
        return tlfNr;
    }

    public int getUnicID() {
        return unicID;
    }

    public LocalDate getFodselsdag() {

        return fodselsdag;
    }

    public boolean isRestance() {
        return restance;
    }

    public void setRestance(boolean restance) {
        this.restance = restance;
    }

    public String getFavSvommeArt() {
        return favSvommeArt;
    }

    public String getSvommeHold() {
        return svommeHold;
    }

    public double getKontingent() {
        return kontingent;
    }

    abstract public double kontigentBeregner();

    @Override
    public String toString() {
        return ":\n" +
                "MedlemsID#" + unicID +
                ",\nNavn: " + name + '\'' +
                ", Email: '" + email + '\'' +
                ", Telefon: " + tlfNr +
                ", Fødselsdag dato: " + fodselsdag +
                ", Svømme art: '" + favSvommeArt + '\'' +
                ", Hold: " + svommeHold + '\'' +
                ", kontingent: " + kontingent +
                ", restance: " + restance+"\n";
    }
}
