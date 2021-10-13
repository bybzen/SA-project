package shop.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ChangePasswordController {

    @FXML private TextField username_textField1 , id_textField2, newpassword_textField ;
    Connection con;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null ;
    String username_input;
    String id_input;
    String newpassword;

    public ChangePasswordController(){
        con = ConnectDatabase.connectDB();
    }

    //@FXML
    //private void click(MouseEvent event) throws Exception{
        //change();
    //}

    @FXML public void handleChangeButton(ActionEvent event) throws Exception  {
        Alert alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alert.setHeight(100);
        alert.setWidth(200);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to change password ? ");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()== ButtonType.OK) {
            try {
                change();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/AdminLogin.fxml"));
                Scene scene = new Scene(loader.load());

                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();

                LoginController controller = loader.getController();
                controller.initialize();

                stage.setTitle("Admin Login");
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void showPassword(){
        username_input = username_textField1.getText();
        id_input = id_textField2.getText();
        newpassword = newpassword_textField.getText();
    }

    public void change(){
        showPassword();
        try {
            System.out.println(username_input);
            System.out.println(id_input);
            System.out.println(newpassword);
            String sql = "UPDATE user SET User_password_admin = ?  WHERE User_id_admin = ? AND ID_personnal = ? ";
            preparedStatement = con.prepareStatement(sql);;
            preparedStatement.setString(1,newpassword);
            preparedStatement.setString(2,username_input);
            preparedStatement.setString(3,id_input);
            preparedStatement.executeUpdate();
            System.out.println("Changed password success");
        }catch (SQLException ex){
            ex.printStackTrace();
        }catch (RuntimeException ex){
            ex.printStackTrace();
        }
    }





    @FXML public void handleBackButton (ActionEvent actionEvent) throws IOException {
        try {
            FXRouter.goTo("Home");
        } catch (IOException var3) {
            System.err.println("ไปที่หน้า Home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

}
