package Model;

import java.time.LocalDate;
import java.util.Date;

public class PensionistMedlem extends Members {

    public PensionistMedlem(int unicID, String name, String email, int tlfNr, LocalDate fodselsdag, String favSvommeArt, String svommeHold) {
        super(unicID, name, email, tlfNr, fodselsdag, favSvommeArt, svommeHold);
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
        if (svommeHold  == "Pensonist") {
            kontingent = 1600*75/100;
            return kontingent;
        }
        return kontingent;
    }
}

