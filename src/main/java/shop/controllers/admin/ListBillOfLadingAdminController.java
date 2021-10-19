package shop.controllers.admin;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.io.IOException;

public class ListBillOfLadingAdminController {
    @FXML
    ComboBox status_combobox;


    //ตาราง
    //@FXML private TableView<> table_list_bill_of_lading_A;

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
