package shop.controllers.admin;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import shop.controllers.ConnectDatabase;

import java.io.IOException;
import java.sql.Connection;

public class CreateWorkorderController {

    @FXML
    TextField name_costomer_textfield;
    @FXML TextField address_textfield;
    @FXML TextField tel_textfield;
    @FXML TextField time_textfield;
    @FXML TextField assign_person_name_textfield;
    @FXML TextField price_textfield;
    @FXML
    DatePicker date_picker;
    @FXML
    ComboBox status_combobox;

    //ปุ่ม confirm
    @FXML public void ConfirmButton(ActionEvent actionEvent) {

    }

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
