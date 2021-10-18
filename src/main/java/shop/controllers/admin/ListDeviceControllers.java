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
import shop.models.Device;

import java.io.IOException;

public class ListDeviceControllers {


    private Device devices , selectDevice;

    @FXML private TableView<Device> tableDevices;
    @FXML TextField Withdraw_Of_Device_Textfield;  // ช่องลด device
    @FXML TextField add_device_textfield; // ช่องเพิ่ม device
    @FXML Label Name_device_label;

    private ObservableList<Device> deviceList;



    @FXML public void initialize(){
        devices = new Device("01", "Air", 10);
        devices.addDeviceToStock(devices);
        System.out.println(devices.getDeviceList());
        Device device1 = new Device("02", "PVC", 20);
        devices.addDeviceToStock(device1);
        System.out.println(devices.getDeviceList());

        System.out.println("len = " + devices.getLengthArrayList()); // ความยาว ArrayList


        Platform.runLater(() -> {

            showTableView();

        });

        tableDevices.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedDevice(newValue);
            }
        });

    }

    public void showTableView(){

        deviceList = FXCollections.observableArrayList(devices.getDeviceList());

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
