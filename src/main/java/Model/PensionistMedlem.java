package Model;

public class PensionistMedlem extends Members {

    public PensionistMedlem(int medlemsID, String name, String email, int tlfNr, String fodselsdag, String favSvommeArt, String svommeHold, double kontigent) {
        super(medlemsID, name, email, tlfNr, fodselsdag, favSvommeArt, svommeHold, kontigent);
    }

    @Override
    public double kontigentBeregner() {
        double kontigent = 0;

        if (svommeHold == "Junior") {
            kontigent = 1000;
            return kontigent;
        }
        if (svommeHold == "Senior") {
            kontigent = 1600;
            return kontigent;
        }
        if (svommeHold  == "Pensionist") {
            kontigent = 1600*75/100;
            return kontigent;
        }
        return kontigent;
    }
}

