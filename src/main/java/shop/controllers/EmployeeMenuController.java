package shop.controllers;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class EmployeeMenuController {

@FXML
    public void CreateBillOfLadingButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("CreateBillOfLading");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า CreateBillOfLading ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
