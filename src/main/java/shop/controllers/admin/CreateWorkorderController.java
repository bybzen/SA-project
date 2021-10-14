package shop.controllers.admin;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import shop.controllers.ConnectDatabase;

import java.io.IOException;
import java.sql.Connection;

public class CreateWorkorderController {

    Connection con;

    public CreateWorkorderController (){

        con = ConnectDatabase.connectDB();

    }

    @FXML
        public void BackButton(ActionEvent actionEvent) {
            try {
                FXRouter.goTo("AdminMenu");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า AdminMenu ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
}
