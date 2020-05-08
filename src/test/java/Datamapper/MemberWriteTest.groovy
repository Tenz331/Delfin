package Datamapper;

import Model.JuniorMedlem;
import Model.Members
import Model.PensionistMedlem
import Model.SeniorMedlem;
import org.junit.Before;
import org.junit.Test

import java.lang.reflect.Member;
import java.sql.*;


import java.util.ArrayList;

public class MemberWriteTest {
    MemberWrite memberWrite;
    JuniorMedlem Members = new JuniorMedlem("Aly","123@gmail.com",4242432,"2005-08-12","crawl","Junior", 242.52);
    JuniorMedlem Members1 = new JuniorMedlem("Janus","Janus@gmail.com",10101010,"2004-09-01","Brystsvømning","Junior", 242.52)
    JuniorMedlem Members6 = new JuniorMedlem("Andreas","Andreas@gmail.com",90458271,"2006-03-12","Butterfly","Junior", 242.52)
    SeniorMedlem Members2 = new SeniorMedlem("Emil","Emil@gmail.com",31560570,"1995-03-25","Butterfly","Senior", 500.00);
    SeniorMedlem Members7 = new SeniorMedlem("Simone","Simone@gmail.com",74548595,"1993-05-28","Rygcrawl","Senior", 500.00);
    SeniorMedlem Members3 = new SeniorMedlem("Mathias","mathias@gmail.com",51525152,"1998-08-06","crawl","Senior", 500.00);
    PensionistMedlem Members4 = new PensionistMedlem("Grete","Grete@gmail.com",55561110,"1957-10-10","Brystsvømning","Pensionist", 350.52);
    PensionistMedlem Members5 = new PensionistMedlem("Hans","Hans@gmail.com",10125480,"1945-12-12","Butterfly","Pensionist", 350.52);
    PensionistMedlem Members8 = new PensionistMedlem("Marie","Marie@gmail.com",60604554,"1943-09-16","Rygcrawl","Pensionist", 350.52);

    Connection DBConnect = Util.DBConnect.getInstance().getConnection();

    ArrayList<Members> tempmembers;


    @Before
    public void setUp() throws Exception {
        tempmembers = new ArrayList<>();
        tempmembers.add(Members);
        tempmembers.add(Members1);
        tempmembers.add(Members2);
        tempmembers.add(Members3);
        tempmembers.add(Members4);
        tempmembers.add(Members5);
        tempmembers.add(Members6);
        tempmembers.add(Members7);
        tempmembers.add(Members8);
        for (Members i: tempmembers){
            System.out.println(i.getName().toString());
            memberWrite = new MemberWrite();
            final String USERNAME = "fullroot";
            final String PASSWORD = "fullroot";
            final String CONN_STR = "jdbc:mysql://cphb-gruppe1.c4mqzn3xlkdy.us-east-2.rds.amazonaws.com/";
            String query = "INSERT INTO Delfinen.Membership (member_name, member_email, member_telefon, member_fødselsdag, member_favSvmømmeart, member_hold, member_kontingent)" + "values(?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = DBConnect.prepareStatement(query);
            preparedStatement.setString(1,i.getName());
            preparedStatement.setString(2,i.getEmail());
            preparedStatement.setInt(3,i.getTlfNr());
            preparedStatement.setString(4,i.getFodselsdag());
            preparedStatement.setString(5,i.getFavSvommeArt());
            preparedStatement.setString(6,i.getSvommeHold());
            preparedStatement.setDouble(7,i.getKontingent());
            preparedStatement.execute();
        }

    }

    @Test
    public void setMember() {
    }

}
