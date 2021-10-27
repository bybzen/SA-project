package shop.controllers.admin;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import shop.controllers.ConnectDatabase;
import shop.models.Workorder;

import java.io.IOException;
import java.sql.Connection;

public class CreateWorkorderController {

    @FXML TextField name_costomer_textfield;
    @FXML TextField address_textfield;
    @FXML TextField tel_textfield;
    @FXML TextField time_textfield;
    @FXML TextField assign_person_name_textfield;
    @FXML TextField price_textfield;
    @FXML DatePicker date_picker;
    @FXML ComboBox<String> status_combobox;

    Workorder workorder;
    Workorder workorderList = new Workorder();


    @FXML public void initialize() {

        status_combobox.getItems().addAll("รอดำเนินการ", "ยอมรับ", "รอแก้ไข", "ออกไปติดตั้ง", "เสร็จสิ้น");


    }

    //ปุ่ม confirm
    @FXML public void ConfirmButton(ActionEvent actionEvent) {

        // ยังไม่มีพนักงานรับผิดชอบ
        if (!(name_costomer_textfield.getText().equals("")) && !(address_textfield.getText().equals(""))
            && !(tel_textfield.getText().equals("")) && date_picker != null && !(time_textfield.getText().equals(""))
            && !(price_textfield.getText().equals("")) && status_combobox != null){

            System.out.println("สร้างใบงานได้");

//            System.out.println(date_picker.getEditor().getText()); get เวลาจาก DatePicker

            workorder = new Workorder( "W" + workorderList.getLengthArrayList(), name_costomer_textfield.getText(), address_textfield.getText()
                        , tel_textfield.getText(), Float.parseFloat(price_textfield.getText())
                        , date_picker.getEditor().getText(), time_textfield.getText()
                        , status_combobox.getValue());

//            System.out.println(workorder.toString());
            workorderList.addWorkOrderToList(workorder);

            System.out.println("ใน list " + workorderList.getWorkbook());
            System.out.println(workorderList.getLengthArrayList());

        }

        else {
            System.out.println("สร้างไม่ได้");
        }


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
