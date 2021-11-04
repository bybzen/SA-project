package shop.controllers.employee;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import shop.controllers.ConnectDatabase;
import shop.models.Device;
import shop.models.Owner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListDeviceEmployeeController {

    @FXML private TableView<Device> tableDeviceEmp;


    private Device selectDevice;
    private ObservableList<Device> deviceList;
    private Device dList = new Device();

    private PreparedStatement preparedStatement, preparedStatement_all;
    private ResultSet resultSet;
    private Connection con;
//    private Owner ow = new Owner();
    private Device obj;




    public ListDeviceEmployeeController(){
        con = ConnectDatabase.connectDB();
    }

    @FXML public void initialize() throws SQLException {

//        System.out.println("len = " + devices.getLengthArrayList()); // ความยาว ArrayList


        Platform.runLater(() -> {

            showTableView();

        });

        tableDeviceEmp.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedDevice(newValue);
            }
        });

//        String sql_all = "SELECT * FROM User WHERE ID_personal = ?";    // Set data admin from database
//        preparedStatement_all = con.prepareStatement(sql_all);
//        preparedStatement_all.setString(1, "00");
//        resultSet = preparedStatement_all.executeQuery();
//
//        if (resultSet.next()) {
//
//            ow.setRole(resultSet.getString(1));
//            ow.setIdPersonal(resultSet.getString(2));
//            ow.setName(resultSet.getString(3));
//            ow.setUsername(resultSet.getString(4));
//            ow.setPassword(resultSet.getString(5));
//
//        }

        String sql_all_device = "SELECT * FROM Device ";
        preparedStatement_all = con.prepareStatement(sql_all_device);
        resultSet = preparedStatement_all.executeQuery();

        while (resultSet.next()) {

            obj = new Device(resultSet.getString(2),
                    resultSet.getString(1),
                    resultSet.getInt(3));
            dList.addDeviceToStock(obj);

        }
        System.out.println(dList.getDeviceList());
        System.out.println("Set all device");


    }

    public void showTableView(){

        deviceList = FXCollections.observableArrayList(dList.getDeviceList());

        tableDeviceEmp.setItems(deviceList);

        TableColumn idDevice = new TableColumn("ID device");
        TableColumn nameDevice = new TableColumn("Name");
        TableColumn quantity = new TableColumn("Quantity");


        idDevice.setCellValueFactory(
                new PropertyValueFactory<Device, String>("idDevice")
        );
        nameDevice.setCellValueFactory(
                new PropertyValueFactory<Device, String>("nameDevice")
        );
        quantity.setCellValueFactory(
                new PropertyValueFactory<Device, String>("quantity")
        );

        tableDeviceEmp.getColumns().addAll(idDevice, nameDevice, quantity);


    }

    private void showSelectedDevice(Device device) {
        selectDevice = device;
//
//        Name_device_label.setText(selectDevice.getNameDevice());
//        System.out.println(selectDevice.toString());

    }


    @FXML
    public void BackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("EmployeeMenu");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า EmployeeMenu ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
