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

import javax.xml.soap.Name;
import java.io.IOException;
import java.util.ArrayList;

public class ListDeviceControllers {


    private Device devices , selectDevice;

    @FXML private TableView<Device> tableDevices;
    @FXML TextField Withdraw_Of_Device_Textfield;
    @FXML Label Name_device_label;

    private ObservableList<Device> deviceList;

    @FXML public void initialize(){
        devices = new Device("01", "Air", 10);
        devices.addDeviceToStock(devices);
        System.out.println(devices.getDeviceList());
        Device device1 = new Device("02", "PVC", 20);
        devices.addDeviceToStock(device1);
        System.out.println(devices.getDeviceList());



//        blockBtn.setDisable(true);
//        unblockBtn.setDisable(true);

        Platform.runLater(() -> {

            showTableView();

        });

        tableDevices.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedDevice(newValue);
            }
        });

    }
//
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

       if (selectDevice.getNameDevice().equals("Air")){
        System.out.println("True");

        selectDevice.setQuantity(selectDevice.getQuantity()-1);      // Test ลดจน.อุปกรณ์

        System.out.println(selectDevice.getQuantity());

//       clearSelectedDevice();
//       tableDevices.refresh();
//       tableDevices.getSelectionModel().clearSelection();
       }
       else {
           System.out.println("False");
       }



//        countLabel.setText(String.valueOf(staff.getCount()));
//
//        if (selectStaff.getStatus().equals("Available")) {
//            blockBtn.setDisable(false); // เปิดปุ่ม
//            unblockBtn.setDisable(true);
//        }
//        else {
//            unblockBtn.setDisable(false);
//            blockBtn.setDisable(true);
//        }

    }

    private void clearSelectedDevice() {

        Withdraw_Of_Device_Textfield.clear();
//        countLabel.setText("-");
//        if (selectStaff.getStatus().equals("Available")) {
//            unblockBtn.setDisable(true); // ปิดปุ่ม
//        }
//        else {
//            blockBtn.setDisable(true);
//        }

//        blockBtn.setDisable(true); //ปิดปุ่ม
//        unblockBtn.setDisable(true);
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

}
