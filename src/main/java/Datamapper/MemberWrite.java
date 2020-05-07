package Datamapper;

import Util.DBConnect;

import java.sql.*;
public class MemberWrite {
    Connection connection = DBConnect.getInstance().getConnection();
} //gør at brugeren kan modificere hans pizza I menukortet hvis den fx skal havde en ny pris eller navn
