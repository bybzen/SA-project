package shop.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import shop.models.Owner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML TextField username_textfield;
    @FXML PasswordField password_field;

    Owner ow = new Owner();
    Alert alert;

    Connection con;
    PreparedStatement preparedStatement, preparedStatement2;
    ResultSet resultSet, resultSet2;

    public void initialize(){

    }

    public LoginController(){
        con = ConnectDatabase.connectDB();
    }

    @FXML
    public void ConfirmlogInAction(ActionEvent event) throws IOException {

        String username_input = username_textfield.getText();
        String password_input = password_field.getText();

        if (this.username_textfield.getText().equals("") && this.password_field.getText().equals("") ) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please enter username and password");
            alert.showAndWait();
        }
        else {

            if (username_input.equals(ow.getUsername()) && password_input.equals(ow.getPassword())){
                try {
                    String sql = "INSERT INTO user(Role_user, ID_personnal, Name_personal, User_id_admin, User_password_admin) VALUES (?,?,?,?,?)";
                    try {
                    preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, "Owner");
                    preparedStatement.setString(2, "00");
                    preparedStatement.setString(3, "ธนากร");
                    preparedStatement.setString(4, username_input);
                    preparedStatement.setString(5, password_input);
                    preparedStatement.executeUpdate();
                    System.out.println("Username and password save in DB");
                    } catch (SQLException se) {
                        System.out.println("Duplicate username and password");
                        //se.printStackTrace();
                    }
                    FXRouter.goTo("AdminMenu");
                } catch (IOException e) {
                    System.err.println("ไปที่หน้า AdminMenu ไม่ได้");
                    System.err.println("ให้ตรวจสอบการกำหนด route");
                }

            } else if (this.username_textfield.getText().equals("") && !(this.password_field.getText().equals(""))) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(" ");
                alert.setContentText("Please enter username");
                alert.showAndWait();

            }else if (this.password_field.getText().equals("") && !(this.username_textfield.getText().equals(""))) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(" ");
                alert.setContentText("Please enter password");
                alert.showAndWait();

            } else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(" ");
                alert.setContentText("Please check your username or password");
                alert.showAndWait();
            }

        }
    }


    @FXML public void handleBackButton (ActionEvent actionEvent) throws IOException{
        try {
            FXRouter.goTo("Home");
        } catch (IOException var3) {
            System.err.println("ไปที่หน้า Home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

    @FXML
    public void forgetPasswordButton(ActionEvent actionEvent)  throws IOException {
        try {
            FXRouter.goTo("ForgetPassword");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ForgetPassword ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}