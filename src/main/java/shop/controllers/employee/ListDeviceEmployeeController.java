package shop.controllers.employee;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import shop.models.Device;

import java.io.IOException;

public class ListDeviceEmployeeController {

    @FXML private TableView<Device> tableDeviceEmp;


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
