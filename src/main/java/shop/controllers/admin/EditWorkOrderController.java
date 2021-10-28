package shop.controllers.admin;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import shop.models.Workorder;

import java.io.IOException;
import java.sql.SQLException;

public class EditWorkOrderController {

    @FXML TextField name_costomer_textfield;
    @FXML TextField address_textfield;
    @FXML TextField tel_textfield;
    @FXML TextField time_textfield;
    @FXML TextField assign_person_name_textfield;
    @FXML TextField price_textfield;
    @FXML DatePicker date_picker;
    @FXML ComboBox status_combobox;

    private Workorder workorder;

    @FXML
    public void initialize() throws SQLException {
        status_combobox.getItems().addAll("Pending", "Confirm", "Pending edit", "Go out to install", "Complete");

        System.out.println("***" + workorder.toString() + "****");

    }














        //ปุ่ม confirm
    @FXML public void ConfirmButton(ActionEvent actionEvent) {

    }

@FXML
    public void BackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("ListWorkOrder");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ListWorkOrder ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


    public void setWorkorder(Workorder wo){
        this.workorder = wo;
    }
}
