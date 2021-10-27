package shop.controllers.admin;
import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class ListWorkOrderController {
    //@FXML private TableView<> table_Work_Order;




    @FXML public void BackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("AdminMenu");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า AdminMenu ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    
    @FXML public void EditButton(ActionEvent actionEvent) {
            try {
                FXRouter.goTo("EditWorkOrder");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า EditWorkOrder ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
}
