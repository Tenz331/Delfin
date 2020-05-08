package Model;

import java.util.ArrayList;

public class Teams {
    public static ArrayList<JuniorMedlem> juniorTeam = new ArrayList<>();
    public static ArrayList<SeniorMedlem> seniorTeam = new ArrayList<>();
    public static ArrayList<PensionistMedlem> pensionTeam = new ArrayList<>();

    public void addJuniorTeam(JuniorMedlem team) {
        juniorTeam.add(team);
    }

    public void addSeniorTeam(SeniorMedlem team) {
        seniorTeam.add(team);
    }

    public void addPensionTeam(PensionistMedlem team) {
        pensionTeam.add(team);
    }
}
