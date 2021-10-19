package shop.controllers.employee;
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

    @FXML
        public void ListWorkOrderEmployeeButton(ActionEvent actionEvent) {
            try {
                FXRouter.goTo("ListWorkOrderEmployee");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า ListWorkOrderEmployee ไม่ได้");
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


    @FXML public void ListBillOfLadingEmployeeButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("ListBillOfLadingEmployee");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ListBillOfLadingEmployee ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}
