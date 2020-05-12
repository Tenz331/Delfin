package Model;

import java.time.LocalDate;

public class JuniorMedlem extends Members {

    public JuniorMedlem(int unicID,String name, String email, int tlfNr, LocalDate fodselsdag, String favSvommeArt, String svommeHold,double kontingent) {
        super(unicID,name, email, tlfNr, fodselsdag, favSvommeArt, svommeHold,kontingent);
    }

    @Override
    public double kontigentBeregner() {
        double kontingent = 100.00;

        if (svommeHold == "Junior") {
            kontingent = 1000;
            return kontingent;
        }
        if (svommeHold == "Senior") {
            kontingent = 1600;
            return kontingent;
        }
        if (svommeHold  == "pensionist") {
            kontingent = 1600*75/100;
            return kontingent;
        }
        return kontingent;
    }
}