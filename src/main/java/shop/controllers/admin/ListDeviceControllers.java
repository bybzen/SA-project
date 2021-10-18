package shop.controllers.admin;
import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import shop.controllers.ConnectDatabase;
import shop.models.Device;
import shop.models.Owner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListDeviceControllers {


    private Device selectDevice;
    private ObservableList<Device> deviceList;
    private Device dList = new Device();

    private PreparedStatement preparedStatement, preparedStatement_all;
    private ResultSet resultSet;
    private Connection con;
    private Owner ow = new Owner();
    private Device obj;

    @FXML private TableView<Device> tableDevices;
    @FXML TextField Withdraw_Of_Device_Textfield;  // ช่องลด device
    @FXML TextField add_device_textfield; // ช่องเพิ่ม device
    @FXML Label Name_device_label;


    public ListDeviceControllers(){
        con = ConnectDatabase.connectDB();
    }

    @FXML public void initialize() throws SQLException {

//        System.out.println("len = " + devices.getLengthArrayList()); // ความยาว ArrayList


        Platform.runLater(() -> {

            showTableView();

        });

        tableDevices.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedDevice(newValue);
            }
        });

        String sql_all = "SELECT * FROM User WHERE ID_personal = ?";    // Set data admin from database
        preparedStatement_all = con.prepareStatement(sql_all);
        preparedStatement_all.setString(1, "00");
        resultSet = preparedStatement_all.executeQuery();

        if (resultSet.next()) {

            ow.setRole(resultSet.getString(1));
            ow.setIdPersonal(resultSet.getString(2));
            ow.setName(resultSet.getString(3));
            ow.setUsername(resultSet.getString(4));
            ow.setPassword(resultSet.getString(5));

        }

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

        tableDevices.setItems(deviceList);

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

        tableDevices.getColumns().addAll(idDevice, nameDevice, quantity);


    }

    private void showSelectedDevice(Device device) {
        selectDevice = device;

        Name_device_label.setText(selectDevice.getNameDevice());
        System.out.println(selectDevice.toString());

    }

    private void clearSelectedDevice() {
        Withdraw_Of_Device_Textfield.clear(); // เคลียร์ช่องลด
        add_device_textfield.clear(); // เคลียร์ช่องเพิ่ม
    }

    @FXML
    public void BackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("AdminMenu");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า AdminMenu ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML //ปุ่ม update
    public void update_button(ActionEvent actionEvent) {   // *** ยังเช็ค String ไม่ได้ ***
        int input = 0;

        if (selectDevice == null ){ // ยังไม่เลือกรายการ

             if (Withdraw_Of_Device_Textfield.getText().equals("") && add_device_textfield.getText().equals("")){
                System.out.println("กรุณาเลือกรายการก่อน Update");
            }
             else {
                 System.out.println("inputผิดพลาด");
             }
        }

        else {  // เลือกรายการแล้ว
            if (Withdraw_Of_Device_Textfield.getText().isEmpty() && add_device_textfield.getText().isEmpty()){
                System.out.println("กรุณาระบุจำนวน");
            }

            else if ((!Withdraw_Of_Device_Textfield.getText().equals("")) && add_device_textfield.getText().equals("")
                    || Integer.parseInt(add_device_textfield.getText()) == 0) {

                input = Integer.parseInt(Withdraw_Of_Device_Textfield.getText()); // แปลง str --> int

                if (input < 0) {
                    System.out.println("inputติดลบ ไม่สามารถเบิก device ได้");
                }
                else if (selectDevice.getQuantity() == 0) {
                    System.out.println("ไม่สามารถเบิก device ได้เพราะในคลังไม่มี device");
                }
                else if (input > selectDevice.getQuantity()) {
                    System.out.println("ไม่สามารถเบิก deviec ได้เพราะในคลังมี device ไม่พอ");
                }

                else {
                    System.out.println("ก่อนลด = " + selectDevice.getQuantity());
                    selectDevice.decreaseDevice(input);      // Test ลดจน.อุปกรณ์
                    System.out.println("หลังลด = " + selectDevice.getQuantity());

                    clearSelectedDevice();
                    tableDevices.refresh();
                    tableDevices.getSelectionModel().clearSelection();
                }
            }

            else if (!(add_device_textfield.getText().equals("")) && Withdraw_Of_Device_Textfield.getText().equals("")
                    || Integer.parseInt(Withdraw_Of_Device_Textfield.getText()) == 0) {

                input = Integer.parseInt(add_device_textfield.getText()); // แปลง str --> int

                if (input < 0) {
                    System.out.println("inputติดลบ เพิ่ม device ไม่ได้");
                } else {
                    System.out.println("ก่อนเพิ่ม = " + selectDevice.getQuantity());
                    selectDevice.increaseDevice(input);      // Test เพิ่มจน.อุปกรณ์
                    System.out.println("หลังเพิ่ม = " + selectDevice.getQuantity());

                    clearSelectedDevice();
                    tableDevices.refresh();
                    tableDevices.getSelectionModel().clearSelection();
                }
            }
        }
    }

}
