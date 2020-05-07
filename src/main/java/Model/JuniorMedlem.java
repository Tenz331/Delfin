package Model;

public class JuniorMedlem extends Members {

    public JuniorMedlem(int medlemsID, String name, String email, int tlfNr, String fodselsdag, String favSvommeArt, String svommeHold, double kontingent) {
        super(medlemsID, name, email, tlfNr, fodselsdag, favSvommeArt, svommeHold, kontingent);
    }

    @Override
    public double kontigentBeregner() {
        double kontingent = 0;

        if (svommeHold == "Junior") {
            kontingent = 1000;
            return kontingent;
        }
        if (svommeHold == "Senior") {
            kontingent = 1600;
            return kontingent;
        }
        if (svommeHold  == "Pensionist") {
            kontingent = 1600*75/100;
            return kontingent;
        }
        return kontingent;
    }
}