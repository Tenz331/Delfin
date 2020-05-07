package Model;

public abstract class Members {
    int medlemsID;
    String name;
    String email;
    int tlfNr;
    String fodselsdag;
    String favSvommeArt;
    String svommeHold;
    double kontingent;

    public Members(int medlemsID, String name, String email, int tlfNr, String fodselsdag, String favSvommeArt, String svommeHold, double kontingent) {
        this.medlemsID = medlemsID;
        this.name = name;
        this.email = email;
        this.tlfNr = tlfNr;
        this.fodselsdag = fodselsdag;
        this.favSvommeArt = favSvommeArt;
        this.svommeHold = svommeHold;
        this.kontingent = kontingent;
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
        return kontingent;
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
                ", kontigent=" + kontingent +
                '}';
    }
}
