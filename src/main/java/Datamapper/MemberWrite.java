package Datamapper;

import Model.Ordre;
import Model.Pizza;

import java.sql.*;
import java.util.ArrayList;

public class MemberWrite {
    ArrayList<Ordre> temporder;
    int tempUID;
    int tempPizzaCounter = 0;
    private static final String USERNAME = "fullroot";
    private static final String PASSWORD = "fullroot";
    private static final String CONN_STR = "jdbc:mysql://cphb-gruppe1.c4mqzn3xlkdy.us-east-2.rds.amazonaws.com/";
    //"SELECT * FROM MariosPizza.Ordre WHERE pizza_pizza_ordreNR ="+UID)

    public void exportData(Ordre orders) throws SQLException {
        temporder = new ArrayList<>(); //temp array til at holde den data vi har modtaget fra bruger
        temporder.add(orders);
        if (orders.isHomeDelivery() == true) { //2 metoder basert hvilken type af "Ordre det er"
            try {
                Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
                for (Pizza p : orders.getPizzasInOrdre()) {
                    tempPizzaCounter++;
                    String query = " INSERT INTO MariosPizza.Ordre ( pizza_OrdreID, pizza_name, ordre_Total_Price, Order_Customer_Name, pizza_price, home_delivery)"
                            + " values (?, ?, ?, ?, ?, ?)"; //Vores query til databasen
                    for (Ordre o : temporder) { //Dårlig måde at få  Ordre data ud kan dog ikke huske hvordan man gør det bedre
                        String pizzas = orders.getPizzasInOrdre().get(tempPizzaCounter - 1).toString(); //-1 for vi starter ved 0
                        Pizza pizza = o.getPizzasInOrdre().get(tempPizzaCounter - 1); //as above
                        Double getpris = pizza.getPizzaPrice();
                        PreparedStatement preparedStatement = conn.prepareStatement(query);
                        preparedStatement.setInt(1, o.getOrderUID());
                        preparedStatement.setString(2, pizzas);
                        preparedStatement.setDouble(3, o.getTotalOrdrePrice());
                        preparedStatement.setString(4, o.getCustomerName());
                        preparedStatement.setDouble(5, getpris);
                        preparedStatement.setBoolean(6, o.isHomeDelivery());
                        preparedStatement.execute(); //Indsætter vores data til Ordre tabellen i Mario databasen
                        tempUID = o.getOrderUID();
                    }
                }
                String query = " INSERT INTO MariosPizza.Customer_Infomation (Customer_Name, Customer_PhoneNr, Customer_Adresse)"
                        + " values (?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(query);

                preparedStatement = conn.prepareStatement(query); //samae asove tager den data vi har fået i putter i databasen
                preparedStatement.setString(1, orders.getCustomerName());
                preparedStatement.setInt(2, orders.getPhoneNumber());
                preparedStatement.setString(3, orders.getHomeAdress());
                preparedStatement.execute();  //Indsætter vores data til Customer_Infomation tabellen i Mario databasen
                int tempPizzaCounter = 0;

            } catch (SQLException e) {
                System.out.println(e);

            }
            System.out.println("Order: UID#" + tempUID + " Confirmed! with " + tempPizzaCounter + " Pizzas in the order!"); //output af metoden er done uden problemer

        } else if (orders.isHomeDelivery() == false) { //same as above dog er ordren ikke en hjemme levering

            try {
                Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
                for (Pizza p : orders.getPizzasInOrdre()) {
                    tempPizzaCounter++;
                    String query = " INSERT INTO MariosPizza.Ordre ( pizza_OrdreID, pizza_name, ordre_Total_Price, Order_Customer_Name, pizza_price, home_delivery)"
                            + " values (?, ?, ?, ?, ?, ?)";
                    for (Ordre o : temporder) {
                        String pizzas = orders.getPizzasInOrdre().get(tempPizzaCounter - 1).toString();
                        Pizza pizza = o.getPizzasInOrdre().get(tempPizzaCounter - 1);
                        Double getpris = pizza.getPizzaPrice();
                        PreparedStatement preparedStatement = conn.prepareStatement(query);
                        preparedStatement.setInt(1, o.getOrderUID());
                        preparedStatement.setString(2, pizzas);
                        preparedStatement.setDouble(3, o.getTotalOrdrePrice());
                        preparedStatement.setString(4, o.getCustomerName());
                        preparedStatement.setDouble(5, getpris);
                        preparedStatement.setBoolean(6, o.isHomeDelivery());
                        preparedStatement.execute();
                        tempUID = o.getOrderUID();
                        // conn.close();
                    }

                }
                System.out.println("Order: UID#" + tempUID + " Confirmed! with " + tempPizzaCounter + " Pizzas in the order!");
                tempPizzaCounter = 0;
            } catch (Exception e) {
                System.out.println("Database MYSQL ERROR! " + e);
            }
            Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);

            String query = " INSERT INTO MariosPizza.Customer_Infomation (Customer_Name, Customer_PhoneNr, Customer_Adresse)"
                    + " values (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, orders.getCustomerName());
            preparedStatement.setInt(2, orders.getPhoneNumber());
            preparedStatement.setString(3, orders.getHomeAdress());
            preparedStatement.execute();
            int tempPizzaCounter = 0;

        }
        tempPizzaCounter = 0;

    } // Exportere vores ny ordre til vores database

    public void addNewPizzaMenu(int iD, String navn, double pris) throws SQLException {
        try {
            Date CreatedDate = new Date(System.currentTimeMillis());
            Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
            String query = " INSERT INTO MariosPizza.pizzasMenu ( pizza_id, pizza_name, pizza_price)"
                    + " values (?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, iD);
            preparedStatement.setString(2, navn);
            preparedStatement.setDouble(3, pris);
            preparedStatement.execute();
            conn.close();
            System.out.println("PIZZA ADDED:\n#" + iD + " " + navn + " " + pris + " DDK");

        } catch (Exception e) {
            System.out.println("Database MYSQL ERROR! " + e);
        }

    } //Tilføjer en pizza til menukorted

    public void changeOrderStatus(int UID) throws SQLException {
        try {


            //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
            Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);

            String query = " UPDATE MariosPizza.Ordre SET pizza_ordre_Status = 1 WHERE pizza_ordreID =" + UID;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
            // conn.close();
            System.out.println("Order #" + UID + " Has been changed to complete");
        } catch (Exception e) {
            System.out.println("ERROR! :" + e);
            System.out.println("Make sure your input is correct");
        }

    } //flagger en ordre til en anden ordre status så den er "Complete"

    public void flagOrdre(int UID) {
        try {
            //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
            Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);

            String query = "UPDATE MariosPizza.Ordre SET pizza_ordre_Status = 2 WHERE pizza_OrdreID =" + UID;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
            // conn.close();
            System.out.println("Order #" + UID + " Has been flagged");
        } catch (Exception e) {
            System.out.println("ERROR! :" + e);
            System.out.println("Make sure your input is correct");
        }
    }//samme som changeOrderStatus men i stedet for 1 for den er 'færdig' sætter vi 2 for den er slettet

    public void turnCateTable() {
        try {
            //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
            Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
            System.out.println("Connection to DB ESTABLISHED...");
            String query = "TRUNCATE TABLE MariosPizza.Ordre";
            System.out.println("truncating in progress..");
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
            conn.close();
            System.out.println("Table data wipe complete..\nConnection closed..");
        } catch (Exception e) {
            System.out.println("ERROR! :" + e);
        }
    } //Sletter hele databasen kun for us som devs

    public void modifyPizza(int tempNewID, int input, String change) {
        switch (input) {
            case 0://update pizzaname
                try {
                    //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
                    Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
                    String query = "UPDATE MariosPizza.pizzasMenu SET pizza_name = '"+change+"' WHERE pizza_id =" + tempNewID;
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.execute();
                    conn.close();
                    System.out.println("Pizza #" + tempNewID + " Has been changed with the value: "+change);
                } catch (Exception e) {
                    System.out.println("ERROR! :" + e);
                    System.out.println("Make sure your input is correct");
                }
                break;
            case 1://update price
                try {
                    int price = Integer.parseInt(change);
                    //'Connection', 'Statement' and 'ResultSet' are AUTO-CLOSABLE when with TRY-WITH-RESOURCES BLOCK (...)
                    Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
                    String query = "UPDATE MariosPizza.pizzasMenu SET pizza_price = '"+price+"' WHERE pizza_id =" + tempNewID;
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.execute();
                    conn.close();
                    System.out.println("Pizza #" + tempNewID + " Has been changed with the value: "+change);
                } catch (Exception e) {
                    System.out.println("ERROR! :" + e);
                    System.out.println("Make sure your input is correct");
                }
                break;
            case 2://update menu status
                try {
                    int status = Integer.parseInt(change);
                    Connection conn = DriverManager.getConnection(CONN_STR, USERNAME, PASSWORD);
                    String query = " UPDATE MariosPizza.pizzasMenu SET pizza_status = '"+change +"' WHERE pizza_id =" + tempNewID;
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.execute();
                    conn.close();
                    System.out.println("\nPizza #" + tempNewID + " status has been changed to: "+change);
                } catch (Exception e) {
                    System.out.println("ERROR! :" + e);
                    System.out.println("Make sure your input is correct");
                }
                break;
            default:

                break;
        }
    } //gør at brugeren kan modificere hans pizza I menukortet hvis den fx skal havde en ny pris eller navn
}