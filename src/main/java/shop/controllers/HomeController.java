package shop.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HomeController {

    @FXML TextField nameTextField;
    @FXML TextField callNumberTextField;
    @FXML TextField id_studentTextField;

    PreparedStatement preparedStatement , preparedStatement2 , preparedStatement5;
    ResultSet resultSet;

    Connection con ;

    public HomeController() {
        //con = ConnectDatabase.connectDB();
    }

    @FXML
    public void adminLoginButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("AdminLogin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า AdminLogin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    //public void handleAddConfirmButton(ActionEvent actionEvent) throws IOException {
        //String name = nameTextField.getText();
        //String call_number = callNumberTextField.getText();
        //String id_student = id_studentTextField.getText();

        //Input Input_1 = new Input(name, call_number, id_student);
        //System.out.println(Input_1.toString());

        //String sql = "INSERT INTO sa(id,name,tel) VALUES (?,?,?)";

        //try {
            //preparedStatement = con.prepareStatement(sql);
            //preparedStatement.setString(1, id_student);
            //preparedStatement.setString(2, name);
            //preparedStatement.setString(3, call_number);
            //preparedStatement.executeUpdate();
            //System.out.println("ข้อมูลเข้าฐานข้อมูลแล้วจ้าอิอิ");

        //} catch (SQLException se) {
            //se.printStackTrace();
        //}
    //}
}
