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
    ResultSet resultSet, resultSet2;

    Device obj ;
    Device deviceList = new Device();

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

//            System.out.println(ow.toString());
        }
//        System.out.println("Set all owner");

        String sql_all_device = "SELECT * FROM Device ";
        preparedStatement_all = con.prepareStatement(sql_all_device);
        resultSet = preparedStatement_all.executeQuery();



        while (resultSet.next()) {

            obj = new Device(resultSet.getString(2),
                    resultSet.getString(1),
                    resultSet.getInt(3));

//            obj.setNameDevice(resultSet.getString(1));
//            obj.setIdDevice(resultSet.getString(2));
//            obj.setQuantity(Integer.parseInt(resultSet.getString(3)));

            deviceList.addDeviceToStock(obj);

        }
        System.out.println(deviceList.getDeviceList());
        System.out.println("Set all device");

    }

    @FXML public void ConfirmCreateDeviceAction(ActionEvent event) throws IOException {

        if (this.id_deviceTextField.getText().isEmpty() || this.name_deviceTextField.getText().isEmpty() ||
                this.quantity_TextField.getText().isEmpty()) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please enter all data of device to complete");
            alert.showAndWait();

        }else if(!ch.validateId(this.id_deviceTextField.getText())){   // ???????????? pattern ID

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please check your pattern of ID device!");
            alert.showAndWait();

        }else if(!ch.validateName(this.name_deviceTextField.getText())){   // ???????????? pattern ???????????? device

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please check your pattern of name of device!");
            alert.showAndWait();
        }else{



            ArrayList<String> nameListDevice = new ArrayList<>();
            ArrayList<String> idListDevice = new ArrayList<>();

            for (Device n : deviceList.getDeviceList()) {
                nameListDevice.add(n.getNameDevice());
                idListDevice.add(n.getIdDevice());
            }

            System.out.println("list id = " + idListDevice);
            System.out.println("list ???????????? = " + nameListDevice);

            int check = 0;
            for (Device i : deviceList.getDeviceList()) {
                if (i.getIdDevice().contentEquals(id_deviceTextField.getText()) &&
                        i.getNameDevice().contentEquals(name_deviceTextField.getText())) {
                    check = 1 ;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(" ");
                    alert.setContentText("ID and device name also duplicate!");
                    alert.showAndWait();
                    break;
                }
                else if (i.getNameDevice().contentEquals(name_deviceTextField.getText()) &&
                        !(i.getIdDevice().contentEquals(id_deviceTextField.getText()))) {
                    check = 1;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(" ");
                    alert.setContentText("Device name duplicate!");
                    alert.showAndWait();
                    break;
                }
                else if (!(i.getNameDevice().contentEquals(name_deviceTextField.getText())) &&
                        i.getIdDevice().contentEquals(id_deviceTextField.getText())) {
                    check = 1;
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(" ");
                    alert.setContentText("ID duplicate!");
                    alert.showAndWait();
                    break;
                }
            }

              if (check == 0) {
                    Alert alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
                    alert.setHeight(100);
                    alert.setWidth(200);
                    alert.setTitle("CONFIRMATION");
                    alert.setHeaderText(null);
                    alert.setContentText("Do you want to create device ? ");
                    Optional<ButtonType> result = alert.showAndWait();

                    if (result.get() == ButtonType.OK) {
                        create();
                        id_deviceTextField.clear();
                        name_deviceTextField.clear();
                        quantity_TextField.clear();
                    }
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
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @FXML public void BackButton(ActionEvent actionEvent) {
            try {
                FXRouter.goTo("AdminMenu");
            } catch (IOException e) {
                System.err.println("??????????????????????????? AdminMenu ??????????????????");
                System.err.println("?????????????????????????????????????????????????????? route");
            }
        }
}
