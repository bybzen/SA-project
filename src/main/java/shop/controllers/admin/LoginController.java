package shop.controllers.admin;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import shop.controllers.ConnectDatabase;
import shop.models.Owner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginController {

    @FXML TextField username_textfield;
    @FXML PasswordField password_field;

    Alert alert;
    ArrayList<Owner> arrayCount = new ArrayList<>();

    // Field ที่ใช้ set data ----> Object ***********************************
    Owner ow = new Owner();
    Connection con;
    PreparedStatement preparedStatement, preparedStatement_all;
    ResultSet resultSet;
    // ****************************************

    public LoginController(){
        con = ConnectDatabase.connectDB();
    }


    public void initialize() throws SQLException {

        String sql_all = "SELECT * FROM User WHERE ID_personal = ?";    // Set data admin from database
        try {
            preparedStatement_all = con.prepareStatement(sql_all);
            preparedStatement_all.setString(1, "00");
            resultSet = preparedStatement_all.executeQuery();
            //System.out.println(resultSet);
            if (resultSet.next()) {

                ow.setRole(resultSet.getString(1));
                ow.setIdPersonal(resultSet.getString(2));
                ow.setName(resultSet.getString(3));
                ow.setUsername(resultSet.getString(4));
                ow.setPassword(resultSet.getString(5));
                arrayCount.add(ow);

                System.out.println(ow.toString());
                //System.out.println(arrayCount);
            }
            System.out.println("Set all");

        } catch (SQLException e) {
            e.fillInStackTrace();
        }


//        String sql = "INSERT INTO User(Role_user, ID_personal, Name_personal) VALUES (?,?,?)";
//        try {
//            preparedStatement = con.prepareStatement(sql);
//            preparedStatement.setString(1, "Employee");
//            preparedStatement.setString(2, "04");
//            preparedStatement.setString(3, "Wachara Arbging");
//            preparedStatement.executeUpdate();
//            System.out.println("Employee save in DB");
//        } catch (SQLException se) {
//            //se.printStackTrace();
//        }
//
//        String sql1 = "INSERT INTO User(Role_user, ID_personal, Name_personal) VALUES (?,?,?)";
//        try {
//            preparedStatement = con.prepareStatement(sql1);
//            preparedStatement.setString(1, "Employee");
//            preparedStatement.setString(2, "01");
//            preparedStatement.setString(3, "Manat Noisai");
//            preparedStatement.executeUpdate();
//        } catch (SQLException se) {
//            //se.printStackTrace();
//        }
//
//        String sql2 = "INSERT INTO User(Role_user, ID_personal, Name_personal) VALUES (?,?,?)";
//        try {
//            preparedStatement = con.prepareStatement(sql2);
//            preparedStatement.setString(1, "Employee");
//            preparedStatement.setString(2, "02");
//            preparedStatement.setString(3, "Amornthep Anaphitak");
//            preparedStatement.executeUpdate();
//        } catch (SQLException se) {
//            //se.printStackTrace();
//        }
//
//        String sql3 = "INSERT INTO User(Role_user, ID_personal, Name_personal) VALUES (?,?,?)";
//        try {
//            preparedStatement = con.prepareStatement(sql3);
//            preparedStatement.setString(1, "Employee");
//            preparedStatement.setString(2, "03");
//            preparedStatement.setString(3, "Warakorn Bootsing");
//            preparedStatement.executeUpdate();
//        } catch (SQLException se) {
//            //se.printStackTrace();
//        }


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

                    String sql = "INSERT INTO user(User_username_admin, User_password_admin) VALUES (?,?)";
                    try {
                    preparedStatement = con.prepareStatement(sql);
                    preparedStatement.setString(1, username_input);
                    preparedStatement.setString(2, password_input);
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
    public void changePasswordButton(ActionEvent actionEvent)  throws IOException {
        try {
            FXRouter.goTo("ChangePassword");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ChangePassword ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}