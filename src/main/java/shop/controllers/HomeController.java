package shop.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class HomeController {

    @FXML private ImageView p1;

    @FXML public void initialize() {
        p1.setImage(new Image("/imageAll/air1.jpg"));

    }


    @FXML
    public void adminLoginButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("AdminLogin");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า AdminLogin ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    @FXML
        public void EmployeeMenuButton(ActionEvent actionEvent) {
            try {
                FXRouter.goTo("EmployeeMenu");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า EmployeeMenu ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }

    @FXML
    public void TestButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("Test");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า Test ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
