package shop.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class HomeController {

    @FXML private ImageView image1;
    @FXML private ImageView image2;

    @FXML public void initialize() {
        image1.setImage(new Image("/image/2.png"));
        image2.setImage(new Image("/image/3.png"));
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

}
