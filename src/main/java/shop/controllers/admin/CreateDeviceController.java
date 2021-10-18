package shop.controllers.admin;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import shop.controllers.ConnectDatabase;
import shop.models.Device;
import shop.models.Owner;
import shop.services.CheckAndAlert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class CreateDeviceController {

    @FXML private TextField id_deviceTextField, name_deviceTextField, quantity_TextField;

    Alert alert;
    Owner ow = new Owner();
    CheckAndAlert ch = new CheckAndAlert();
    Connection con;
    PreparedStatement preparedStatement, preparedStatement_all;
    ResultSet resultSet;

    Device obj = new Device();

    ArrayList<Device> deviceList = new ArrayList<>();

    public CreateDeviceController(){
        con = ConnectDatabase.connectDB();
    }

    public void initialize() throws SQLException {

        String sql_all = "SELECT * FROM User WHERE ID_personal = ?";    // Set data admin from database
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

            System.out.println(ow.toString());
        }
        System.out.println("Set all");

    }

    @FXML public void ConfirmCreateDeviceAction(ActionEvent event) throws IOException {

        if (this.id_deviceTextField.getText().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please enter ID device");
            alert.showAndWait();
        } else if (this.name_deviceTextField.getText().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please enter name of device");
            alert.showAndWait();

        } else if (this.quantity_TextField.getText().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please enter quantity of device");
            alert.showAndWait();

        }else if(!ch.validateId(this.id_deviceTextField.getText())){

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please check your pattern of ID device!");
            alert.showAndWait();

        }else if(!ch.validateName(this.name_deviceTextField.getText())){

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please check your pattern of name of device!");
            alert.showAndWait();

        }else{

            for(int i = 0 ; i < deviceList.size() ; ++i) {
                if ((deviceList.get(i)).getNameDevice().equals(this.name_deviceTextField.getText())) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(" ");
                    alert.setContentText("Duplicate name of device !");
                    alert.showAndWait();
                    break;
                }
                if ((deviceList.get(i)).getIdDevice().equals(this.id_deviceTextField.getText())) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(" ");
                    alert.setContentText("Duplicate ID of device !");
                    alert.showAndWait();
                    break;
                }
            }

            Alert alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
            alert.setHeight(100);
            alert.setWidth(200);
            alert.setTitle("CONFIRMATION");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to create device ? ");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                    create();
            }

        }

    }
    public void showDataOfDevice(){

        String id_device = id_deviceTextField.getText();
        String name_device = name_deviceTextField.getText();
        int qty = Integer.parseInt(quantity_TextField.getText());

    }

    public void create(){
        showDataOfDevice();
        String sql = "INSERT INTO device(ID_device, Device_name, Quantity_of_device) VALUES (?,?,?)";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, id_deviceTextField.getText());
            preparedStatement.setString(2, name_deviceTextField.getText());
            preparedStatement.setString(3, quantity_TextField.getText());
            preparedStatement.executeUpdate();
            System.out.println("Save data of device in DB");
            if (resultSet.next()) {
                obj.setIdDevice(resultSet.getString(1));
                obj.setNameDevice(resultSet.getString(2));
                obj.setQuantity(Integer.parseInt(resultSet.getString(3)));
                deviceList.add(obj);
                System.out.println(obj.toString());
                System.out.println(deviceList);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }catch (RuntimeException ex){
            ex.printStackTrace();
        }
    }

    @FXML public void BackButton(ActionEvent actionEvent) {
            try {
                FXRouter.goTo("AdminMenu");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า AdminMenu ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
}
