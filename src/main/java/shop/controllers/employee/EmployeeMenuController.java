package shop.controllers.employee;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class EmployeeMenuController {

    @FXML private ImageView p4;

    @FXML public void initialize() {
        p4.setImage(new Image("/imageAll/staff1.png"));

    }


    @FXML public void CreateBillOfLadingButton(ActionEvent actionEvent) {
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
                e.printStackTrace();
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
