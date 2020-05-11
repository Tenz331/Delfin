package Model;
import java.util.HashMap;
import java.util.Map;

public class Teams {
    static int counter = 0;
    static Map<Integer, Members> teams = new HashMap<>();

    public void addNewMember(Members team ) {
        counter++;
        teams.put(counter,team);
    }
    public void removeMember(String name){
        int tempLoopCounter =0;
        for (Members i: teams.values()){
            tempLoopCounter++;
            if(name.equals(i.getName())){
                System.out.println("\nfound:"+i.getName());
                teams.remove(tempLoopCounter);
            }
            else {
                System.out.println("could not find member");
            }
            tempLoopCounter=0;
        }

    }


}
