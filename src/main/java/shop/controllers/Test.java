package shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Test {

    @FXML
    TextField id_personal;
    Connection con;
    PreparedStatement preparedStatement, preparedStatement_username;
    ResultSet resultSet;

    ArrayList<String> arrayCount;

    public Test()
    {
        con = ConnectDatabase.connectDB();
    }
    @FXML
    public void Confirm(ActionEvent event) throws SQLException {
        String sql_username = "SELECT User_id_admin FROM User WHERE ID_personal = ?";
        try {
               preparedStatement_username = con.prepareStatement(sql_username);
               preparedStatement_username.setString(1, id_personal.getText());
               resultSet = preparedStatement_username.executeQuery();
               while (resultSet.next()){
                   System.out.println(resultSet.getString(1));
                   if(!resultSet.getString(1).equals(null)){
                        arrayCount.add(resultSet.getString(1));
                        System.out.println(arrayCount);
                   }
                   System.out.println("******");
            }
            System.out.println("-------");


        }catch (SQLException e){
            e.fillInStackTrace();
        }

    }

    //public void initialize() throws SQLException {

//

}
