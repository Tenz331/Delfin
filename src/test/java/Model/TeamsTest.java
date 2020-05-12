package Model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class TeamsTest {
    Map<Integer,Members>map;
    LocalDate age = LocalDate.now();
   // JuniorMedlem juniorMedlem = new JuniorMedlem("Ole","MGdelux@owo.com", 324234234,age, "Crawl", "Junior");
    //SeniorMedlem senior = new SeniorMedlem("Ale","MGdelux@owo.com", 324234234,age, "Crawl", "Senior");
   // PensionistMedlem pensionist = new PensionistMedlem("Aly","aly@owo.com", 4232423,age, "Crawl", "Pensionist");


    @Before
    public void setUp() throws Exception {
        map = new HashMap<>();
    }

    @Test
    public void addJuniorTeam() {
        System.out.println("sout all:"+map);
        map.remove(1);
        System.out.println("delete 1:");
        System.out.println(map);
        System.out.println("get aly");
        map.get("Ale");

    }
}