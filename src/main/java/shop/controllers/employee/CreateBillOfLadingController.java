package shop.controllers.employee;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import shop.models.BillLading;
import shop.models.Employee;

import java.io.IOException;
import java.util.ArrayList;

public class CreateBillOfLadingController {

    @FXML
    TextField nameText;
    @FXML TextField quantityText;
    @FXML DatePicker datePicker;
    @FXML TextField timeText;
    @FXML ComboBox<String> namePickerCBB;

    BillLading bill;
    BillLading billList = new BillLading();



    public void initialize(){






    }




    @FXML public void ConfirmButton(ActionEvent actionEvent)throws IOException {


        if (!(nameText.getText().equals("")) && !(quantityText.getText().equals("")) && datePicker != null
            && !(timeText.getText().equals("")) && namePickerCBB != null){

            bill = new BillLading("B" + billList.getLengthArrayList() , nameText.getText(),
                    Integer.parseInt(quantityText.getText()),datePicker.getEditor().getText(),
                    timeText.getText(), namePickerCBB.getValue());

            System.out.println("สร้าง Bill ได้");

            billList.addBillToList(bill);

            System.out.println(billList.getBillList());

        }

        else {
            System.out.println("สร้างไม่ได้");
        }






    }









@FXML
    public void BackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("EmployeeMenu");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า EmployeeMenu ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
