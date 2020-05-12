package Model;

import java.time.LocalDate;
import java.util.Date;

public class SeniorMedlem extends Members {

    public SeniorMedlem(int unicID, String name, String email, int tlfNr, LocalDate fodselsdag, String favSvommeArt, String svommeHold, double kontingent, boolean restance) {
        super(unicID, name, email, tlfNr, fodselsdag, favSvommeArt, svommeHold, kontingent, restance);
    }

    @Override
    public double kontigentBeregner() {
        double kontingent = 1900;

        if (svommeHold == "Junior") {
            kontingent = 1000.00;
            return kontingent;
        }
        if (svommeHold == "Senior") {
            kontingent = 1600.00;
            return kontingent;
        }
        if (svommeHold == "Pensonist") {
            kontingent = 1600.00 * 75.00 / 100.00;
            return kontingent;
        }
        return kontingent;
    }
}

