package shop.controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shop.models.Device;

public class ListDeviceController {

    private Device device, selectDevice;

    @FXML private TableView<Device> tableDevices;
    private ObservableList<Device> deviceList;

    @FXML public void initialize(){
//        device = new Device("01", "Air", 10);
//        device.addDeviceToStock(new Device("01", "Air", 10));


//        blockBtn.setDisable(true);
//        unblockBtn.setDisable(true);

        Platform.runLater(() -> {

            showTableView();

        });

        tableDevices.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedStaff(newValue);
            }
        });

    }
//
    public void showTableView(){

        deviceList = FXCollections.observableArrayList(device.getDeviceList());

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

    private void showSelectedStaff(Device staff) {
        selectDevice = staff;

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

}
