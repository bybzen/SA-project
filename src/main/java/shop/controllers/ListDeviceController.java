package shop.controllers;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;


public class ListDeviceController {

    //@FXML private TableView<> tableDevices;

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
