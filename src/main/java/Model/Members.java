package Model;

import java.time.LocalDate;
import java.util.Date;

public abstract class Members {
    String name;
    String email;
    int tlfNr;
    LocalDate fodselsdag;
    String favSvommeArt;
    String svommeHold;
    double kontingent;

    public Members( String name, String email, int tlfNr, LocalDate fodselsdag, String favSvommeArt, String svommeHold, double kontingent) {
        this.name = name;
        this.email = email;
        this.tlfNr = tlfNr;
        this.fodselsdag = fodselsdag;
        this.favSvommeArt = favSvommeArt;
        this.svommeHold = svommeHold;
        this.kontingent = kontingent;
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

    public LocalDate getFodselsdag() {

        return fodselsdag;
    }

    public String getFavSvommeArt() {
        return favSvommeArt;
    }

    public String getSvommeHold() {
        return svommeHold;
    }

    public double getKontingent() { return kontingent;}

    abstract public double kontigentBeregner();

    @Override
    public String toString() {
        return "Members{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", tlfNr=" + tlfNr +
                ", fodselsdag='" + fodselsdag + '\'' +
                ", favSvommeArt='" + favSvommeArt + '\'' +
                ", svommeHold='" + svommeHold + '\'' +
                ", kontigent=" + kontingent +
                '}';
    }
}
