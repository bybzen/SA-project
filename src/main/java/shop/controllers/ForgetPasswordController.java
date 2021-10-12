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

public class ForgetPasswordController {

    @FXML private TextField username_textField1 , id_textField2 ;
    Connection con;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null ;
    String username_input;
    String id_input;

    public ForgetPasswordController(){
        con = ConnectDatabase.connectDB();
    }

    @FXML
    private void click(MouseEvent event) throws Exception{
        change();
    }

    @FXML public void handleChangeButton(MouseEvent event)  {
        Alert alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
        alert.setHeight(100);
        alert.setWidth(200);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to show password ? ");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()== ButtonType.OK) {
            try {
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
    }

    public void change(){
        showPassword();
        try {
            String sql = "Select User_password_admin FROM User WHERE User_id_admin, ID_personnal";
            preparedStatement = con.prepareStatement(sql);;
            preparedStatement.setString(1,username_input);
            preparedStatement.setString(2,id_input);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeight(100);
                alert.setWidth(200);
                alert.setTitle(null);
                alert.setHeaderText(null);
                alert.setContentText("Your Password is " + resultSet.getString("Password"));
                alert.showAndWait();
                resultSet.getString("Password");
                username_textField1.setText("");
                id_textField2.setText("");
            }else if (!resultSet.next()){
                username_textField1.setText("");
                id_textField2.setText("");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setHeight(100);
                alert.setWidth(200);
                alert.setTitle("Input Error");
                alert.setHeaderText(null);
                alert.setContentText("Enter again");
                alert.showAndWait();
            }
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
