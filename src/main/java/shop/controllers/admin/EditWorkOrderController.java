package shop.controllers.admin;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class EditWorkOrderController {

@FXML
    public void BackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("ListWorkOrder");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ListWorkOrder ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
