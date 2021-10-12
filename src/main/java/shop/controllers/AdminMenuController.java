package shop.controllers;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import shop.models.Owner;

import java.io.IOException;

public class AdminMenuController {

    private Owner ow ;

//    @FXML public void initialize() {
//
//        System.out.print(ow.toString());
//    }



@FXML
    public void CreateworkorderButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("CreateWorkorder");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า CreateWorkorder ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
