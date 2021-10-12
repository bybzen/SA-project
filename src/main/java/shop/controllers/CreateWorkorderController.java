package shop.controllers;

import java.sql.Connection;

public class CreateWorkorderController {

    Connection con;

    public CreateWorkorderController (){

        con = ConnectDatabase.connectDB();

    }
}
