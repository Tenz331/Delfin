package Model;

public abstract class Members {
    int medlemsID;
    String name;
    String email;
    int tlfNr;
    String fodselsdag;
    String favSvommeArt;
    String svommeHold;
    double kontigent;

    public Members(int medlemsID, String name, String email, int tlfNr, String fodselsdag, String favSvommeArt, String svommeHold, double kontigent) {
        this.medlemsID = medlemsID;
        this.name = name;
        this.email = email;
        this.tlfNr = tlfNr;
        this.fodselsdag = fodselsdag;
        this.favSvommeArt = favSvommeArt;
        this.svommeHold = svommeHold;
        this.kontigent = kontigent;
    }
    public int getMedlemsID() {
        return medlemsID;
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

    public String getFodselsdag() {
        return fodselsdag;
    }

    public String getFavSvommeArt() {
        return favSvommeArt;
    }

    public String getSvommeHold() {
        return svommeHold;
    }

    public double getKontigent() {
        return kontigent;
    }

abstract public double kontigentBeregner();

    @Override
    public String toString() {
        return "Members{" +
                "medlemsID=" + medlemsID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", tlfNr=" + tlfNr +
                ", fodselsdag='" + fodselsdag + '\'' +
                ", favSvommeArt='" + favSvommeArt + '\'' +
                ", svommeHold='" + svommeHold + '\'' +
                ", kontigent=" + kontigent +
                '}';
    }
}
