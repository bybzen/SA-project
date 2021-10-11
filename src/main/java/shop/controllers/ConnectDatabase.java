package shop.controllers;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDatabase {

    static String db_name = "team";
    static String user = "root";
    static String pass = "";
    static String hostName = "localhost:3306";
    static String db_driver = "com.mysql.jdbc.Driver";
    static String SSL = "?verifyServerCertificate=false&useSSL=true";

    public static Connection connectDB() {
        try {
            Class.forName(db_driver);
            String url = "jdbc:mysql://" + hostName + "/" + db_name + SSL;
            Connection connect = DriverManager.getConnection(url, user, pass);
            System.out.println("เชื่อมต่อฐานข้อมูลเรียบร้อย");
            return connect;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        connectDB();
    }


}
