package Datamapper;

import Model.*;
import Util.DBConnect;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.*;

public class MemberWriteTest {
    ArrayList<Members> tempmembers = new ArrayList<>();
    private static final String USERNAME = "fullroot";
    private static final String PASSWORD = "fullroot";
    private static final String CONN_STR = "jdbc:mysql://cphb-gruppe1.c4mqzn3xlkdy.us-east-2.rds.amazonaws.com/";
    int amountoftestmembers = 16;
    double randomValue;

    @Before
    public void setUp() throws Exception {
        SeniorMedlem n1 = new SeniorMedlem(1, "Pernille Blume", "PernilleBlume@mail.com", 21415161, LocalDate.now(), "Crawl", "Senior", 1600, false);
        SeniorMedlem n2 = new SeniorMedlem(2, "Julie Kepp Jensen", "JulieKeppJensen@mail.com", 32313, LocalDate.now(), "RykCrawl", "Senior", 1600, true);
        SeniorMedlem n3 = new SeniorMedlem(3, "Emily Gantriis", "Emily_Gantriis@mail.com", 423424234, LocalDate.parse("1999-04-03"), "RykCrawl", "Senior", 1600, false);
        SeniorMedlem n4 = new SeniorMedlem(4, "Emilie Beckmann", "Emilie_Beckmann@mail.com", 12345678, LocalDate.parse("1997-04-03"), "Butterfly", "Senior", 1600, false);
        SeniorMedlem n5 = new SeniorMedlem(5, "Signe Bro", "Signe_Bro@mail.com", 432423332, LocalDate.parse("1999-04-03"), "BrystSvømning", "Senior", 1600, false);
        SeniorMedlem n6 = new SeniorMedlem(6, "Jeanette Ottesen", "Jeanette_Ottesen@mail.com", 9999999, LocalDate.parse("1987-04-03"), "BrystSvømning", "Senior", 1600, false);
        JuniorMedlem nj1 = new JuniorMedlem(7, "Mie Ø. Nielsen", "Mie_Nielsen@gmail.com", 342343322, LocalDate.parse("2001-05-03"), "BrystSvømning", "Junior", 1000, true);
        JuniorMedlem nj2 = new JuniorMedlem(8, "Karoline Barrett", "Karoline_Barrett@mail.com", 213112312, LocalDate.parse("2002-05-07"), "Crawl", "Junior", 1000, false);
        JuniorMedlem nj3 = new JuniorMedlem(9, "Caroline Erichsen", "Caroline_Erichsen@mail.com", 12345678, LocalDate.parse("2010-05-07"), "Crawl", "Junior", 1000, true);
        JuniorMedlem nj4 = new JuniorMedlem(10, "Amalie Mikkelsen", "Amalie_Mikkelsen@mail.com", 33323123, LocalDate.parse("2009-05-07"), "Butterfly", "Junior", 1000, false);
        JuniorMedlem nj5 = new JuniorMedlem(11, "Schastine Tabor", "Schastine_Tabor@mail.com", 546435345, LocalDate.parse("2012-05-07"), "Butterfly", "Junior", 1000, false);
        PensionistMedlem newPensionist = new PensionistMedlem(12, "Lærke Toft Ruby", "Ruby@mail.com", 32432234, LocalDate.parse("1993-05-07"), "BrystSvømning", "Pensonist", 1200, true);
        PensionistMedlem newPensionist2 = new PensionistMedlem(13, "Sophie Amalie Schøn", "Sophie@mail.com", 342232131, LocalDate.parse("1991-05-07"), "BrystSvømning", "Pensonist", 1200, true);
        PensionistMedlem newPensionist3 = new PensionistMedlem(14, "Line Jørgensen Bruun", "Jørgensen@mail.com", 231213129, LocalDate.parse("1995-05-07"), "Butterfly", "Pensonist", 1200, true);
        PensionistMedlem newPensionist4 = new PensionistMedlem(15, "Maria Grandt", "Maria@mail.com", 234122133, LocalDate.parse("1981-05-07"), "Butterfly", "Pensonist", 1200, false);
        PensionistMedlem newPensionist5 = new PensionistMedlem(16, "Anna Wrist-Elkjær", "Anna Wrist-Elkjær@mail.com", 3453223, LocalDate.parse("1998-05-07"), "Butterfly", "Pensonist", 1200, false);
        tempmembers.add(n1);
        tempmembers.add(n2);
        tempmembers.add(n3);
        tempmembers.add(n4);
        tempmembers.add(n5);
        tempmembers.add(n6);
        tempmembers.add(nj1);
        tempmembers.add(nj2);
        tempmembers.add(nj3);
        tempmembers.add(nj4);
        tempmembers.add(nj5);
        tempmembers.add(newPensionist);
        tempmembers.add(newPensionist2);
        tempmembers.add(newPensionist3);
        tempmembers.add(newPensionist4);
        tempmembers.add(newPensionist5);
    }


    @Test
    public void setMember() throws SQLException {
        int count = 0;
        Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
        try {
            for (Members i : tempmembers) {
                count++;
                if (count <= amountoftestmembers) {
                    System.out.println(i.getName().toString());
                    String query = "INSERT INTO Delfinen.Membership (member_idd, member_name, member_email, member_telefon, member_fødselsdag, member_favSvømmeart, member_hold, betalt_kontigent, member_aktiv)" + "values(?,?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.setInt(1, i.getUnicID());
                    preparedStatement.setString(2, i.getName());
                    preparedStatement.setString(3, i.getEmail());
                    preparedStatement.setInt(4, i.getTlfNr());
                    preparedStatement.setDate(5, Date.valueOf(i.getFodselsdag()));
                    preparedStatement.setString(6, i.getFavSvommeArt());
                    preparedStatement.setString(7, i.getSvommeHold());
                    preparedStatement.setBoolean(8, i.isRestance());
                    preparedStatement.setBoolean(9, true);
                    preparedStatement.execute();
                } else {
                    System.out.println("done");
                }
            }
            System.out.println("\n Members Test data done!\n");
            setKonti();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void setKonti() throws SQLException {
        Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
        try {
            for (Members i : tempmembers) {
                System.out.println(i.getName().toString() + " >< " + i.kontigentBeregner());

                String query = "INSERT INTO Delfinen.Restance (member_idd, member_name, member_kontigent)" + "values(?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, i.getUnicID());
                preparedStatement.setString(2, i.getName());
                preparedStatement.setDouble(3, i.kontigentBeregner());
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("\nKontigents Test data done!");
        setKonk();

    }



    public void setKonk() throws SQLException {
        Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
        String[] type = {"50 fir","100 fri","200 fri", "50 ryg"};
        String[] konkurrencenLocation = {"København","Hillerød","Randers","Lyngby"};

        try {
            for (Members i : tempmembers) {
                Random r = new Random();
                int random = r.nextInt(type.length);
                double min = 9.05;
                double max = 50;
                randomValue = min + (max - min) * r.nextDouble();
                DecimalFormat df = new DecimalFormat("#.00");
                System.out.println(i.getName() + " t: " + df.format(randomValue));
                String query = "INSERT INTO Delfinen.StatistikDB (member_idd, member_name, DB_tournament, DB_placement, member_hold, DB_tid )" + "values(?,?,?,?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, i.getUnicID());
                preparedStatement.setString(2, i.getName());
                preparedStatement.setString(3, type[random]);
                preparedStatement.setString(4, konkurrencenLocation[random]);
                preparedStatement.setString(5, i.getSvommeHold());
                preparedStatement.setString(6,df.format(randomValue));
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("\nKonkurence Test data done!");

    }

}