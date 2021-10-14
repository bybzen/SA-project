package shop.controllers.admin;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AdminMenuController {

    @FXML public void CreateworkorderButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("CreateWorkorder");
            } catch (IOException e) {
            System.err.println("ไปที่หน้า CreateWorkorder ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
             }
    }
    
    @FXML
        public void CreateDeviceButton(ActionEvent actionEvent) {
            try {
                FXRouter.goTo("CreateDevice");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า CreateDevice ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    @FXML
        public void ListDeviceButton(ActionEvent actionEvent) {
            try {
                FXRouter.goTo("ListDevice");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า ListDeviceButton ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }

        @FXML
            public void BackButton(ActionEvent actionEvent) {
                try {
                    FXRouter.goTo("Home");
                } catch (IOException e) {
                    System.err.println("ไปที่หน้า Home ไม่ได้");
                    System.err.println("ให้ตรวจสอบการกำหนด route");
                }
            }


    @FXML public void ListWorkOrderButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("ListWorkOrder");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ListWorkOrder ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
