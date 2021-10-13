package shop.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import shop.models.Owner;

import java.io.IOException;
import java.sql.*;

public class LoginController {

    @FXML TextField username_textfield;
    @FXML PasswordField password_field;

    Owner ow = new Owner();
    Alert alert;


    Connection con;
    PreparedStatement preparedStatement, preparedStatement_role, preparedStatement_id, preparedStatement_name,
            preparedStatement_username, preparedStatement_password;

    ResultSet resultSet;


    public LoginController(){
        con = ConnectDatabase.connectDB();
    }


    public void initialize() throws SQLException {

        //String sql_role = "SELECT Role_user FROM User ";
        //String sql_id = "SELECT ID_personal FROM User ";
        //String sql_name = "SELECT Name_personal FROM User ";
        //***String sql_username = "SELECT User_id_admin FROM User WHERE ID_personal = ?";
        //String sql_password = "SELECT User_password_admin FROM User";
        //try {
            //preparedStatement_role = con.prepareStatement(sql_role);
            //preparedStatement_role.setString(1,username_textfield.getText());
            //preparedStatement_id = con.prepareStatement(sql_id);
            //preparedStatement_name = con.prepareStatement(sql_name);
            //***   preparedStatement_username = con.prepareStatement(sql_username);
            //***   preparedStatement_username.setString(1,username_textfield.getText());
            //***   resultSet = preparedStatement_name.executeQuery();
            //***   while (resultSet.next()){
                //***   arrayCount.add(resultSet.getString(1));
            //***}
            //preparedStatement_password = con.prepareStatement(sql_password);
            //preparedStatement_password.setString(,password_textfield.getText());

        //***}catch (SQLException e){

        //***}

        //ResultSet resultSet_role = preparedStatement_role.executeQuery();
//        ResultSet resultSet_id = preparedStatement_id.executeQuery();
//        ResultSet resultSet_name = preparedStatement_name.executeQuery();
//        ResultSet resultSet_username = preparedStatement_username.executeQuery();
//        ResultSet resultSet_password = preparedStatement_password.executeQuery();
//
//        ow = new Owner(resultSetMetaData_id,resultSetMetaData_username,resultSet_password,resultSetMetaData_role,resultSetMetaData_name);
//        System.out.println(ow.getUsername());

        String sql = "INSERT INTO User(Role_user, ID_personal, Name_personal) VALUES (?,?,?)";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, "Employee");
            preparedStatement.setString(2, "04");
            preparedStatement.setString(3, "Wachara Arbging");
            preparedStatement.executeUpdate();
            System.out.println("Employee save in DB");
        } catch (SQLException se) {
            //se.printStackTrace();
        }

        String sql1 = "INSERT INTO User(Role_user, ID_personal, Name_personal) VALUES (?,?,?)";
        try {
            preparedStatement = con.prepareStatement(sql1);
            preparedStatement.setString(1, "Employee");
            preparedStatement.setString(2, "01");
            preparedStatement.setString(3, "Manat Noisai");
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            //se.printStackTrace();
        }

        String sql2 = "INSERT INTO User(Role_user, ID_personal, Name_personal) VALUES (?,?,?)";
        try {
            preparedStatement = con.prepareStatement(sql2);
            preparedStatement.setString(1, "Employee");
            preparedStatement.setString(2, "02");
            preparedStatement.setString(3, "Amornthep Anaphitak");
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            //se.printStackTrace();
        }

        String sql3 = "INSERT INTO User(Role_user, ID_personal, Name_personal) VALUES (?,?,?)";
        try {
            preparedStatement = con.prepareStatement(sql3);
            preparedStatement.setString(1, "Employee");
            preparedStatement.setString(2, "03");
            preparedStatement.setString(3, "Warakorn Bootsing");
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            //se.printStackTrace();
        }


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
                    System.out.print(ow.toString());

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