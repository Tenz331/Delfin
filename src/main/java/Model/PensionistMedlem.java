package Model;

import java.time.LocalDate;
import java.util.Date;

public class PensionistMedlem extends Members {

    public PensionistMedlem(int unicID, String name, String email, int tlfNr, LocalDate fodselsdag, String favSvommeArt, String svommeHold,double kontingent) {
        super(unicID, name, email, tlfNr, fodselsdag, favSvommeArt, svommeHold,kontingent);
    }

    @Override
    public double kontigentBeregner() {
        double kontingent = 100;

        if (svommeHold == "Junior") {
            kontingent = 1000.00;
            return kontingent;
        }
        if (svommeHold == "Senior") {
            kontingent = 1600.00;
            return kontingent;
        }
        if (svommeHold  == "Pensonist") {
            kontingent = 1600.00*75.00/100.00;
            return kontingent;
        }
        return kontingent;
    }
}

