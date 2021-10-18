package shop.controllers.admin;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import shop.controllers.ConnectDatabase;
import shop.models.Owner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ChangePasswordController {

    @FXML private TextField username_textField1 , id_textField2, newpassword_textField ;


    String username_input;
    String id_input;
    String newpassword;
    ArrayList<Owner> arrayCount = new ArrayList<>();

    Owner ow = new Owner();
    Connection con;
    private PreparedStatement preparedStatement_all;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void initialize(){

        System.out.println("-----------------  เข้าหน้าเปลี่ยนรหัส -------------------------");

        String sql_all = "SELECT * FROM User WHERE ID_personal = ?";    // Set data admin from database
        try {
            preparedStatement_all = con.prepareStatement(sql_all);
            preparedStatement_all.setString(1, "00");
            resultSet = preparedStatement_all.executeQuery();           // ดึงงข้อมูลจาก database
            //System.out.println(resultSet);
            if (resultSet.next()) {

                ow.setRole(resultSet.getString(1));
                ow.setIdPersonal(resultSet.getString(2));
                ow.setName(resultSet.getString(3));
                ow.setUsername(resultSet.getString(4));
                ow.setPassword(resultSet.getString(5));
                System.out.println(ow.toString());

                arrayCount.add(ow);
                //System.out.println(arrayCount);
            }
            System.out.println("Set all");

        } catch (SQLException e) {
            e.fillInStackTrace();
        }

    }

    public ChangePasswordController(){
        con = ConnectDatabase.connectDB();
    }


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
                changePassword();
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

    public void changePassword(){
        showPassword();
        try {

            preparedStatement = null;
            System.out.println(username_input);
            System.out.println(id_input);
            System.out.println(newpassword);

            String sql_password = "UPDATE user SET User_password_admin = ?  WHERE User_id_admin = ? AND ID_personal = ? ";

            System.out.println(preparedStatement = con.prepareStatement(sql_password));

            preparedStatement.setString(1,newpassword);
            preparedStatement.setString(2,username_input);
            preparedStatement.setString(3,id_input);

            ow.setPassword(sql_password);     // set ค่าได้ไงไม่รู้งงเหมือนกัน ???
//            System.out.println(sql_password);

            preparedStatement.executeUpdate();   // update password in database
            System.out.println("----------------------------- Changed password success -----------------------");
        }

        catch (SQLException ex){
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
