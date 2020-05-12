package Model;
import java.util.HashMap;
import java.util.Map;

public class Teams {
    static int counter = 0;
    static public Map<Integer, Members> teams = new HashMap<>();

    public void addNewMember(Members team ) {
        counter++;
        teams.put(counter,team);
    }
    public void removeMember(int id){
        teams.remove(id);
    }

}
