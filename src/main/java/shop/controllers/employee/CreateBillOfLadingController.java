package shop.controllers.employee;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import shop.controllers.ConnectDatabase;
import shop.models.BillLading;
import shop.models.Owner;
import shop.models.Workorder;
import shop.services.CheckAndAlert;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class CreateBillOfLadingController {

    @FXML TextArea nameAndQuantityText;
    @FXML DatePicker datePicker;
    @FXML TextField timeText;
    @FXML ComboBox<String> namePickerCBB;;

    BillLading bill;
    BillLading billList = new BillLading();
    ArrayList<String> arrayCount = new ArrayList<>();


    PreparedStatement preparedStatement, preparedStatement_all;
    ResultSet resultSet;

    Owner ow = new Owner();

    Connection con;

    Workorder workorder;
    Workorder workorderList = new Workorder();

    Alert alert;
    CheckAndAlert ch = new CheckAndAlert();




    public void initialize() throws SQLException {


        String sql_all = "SELECT * FROM User WHERE ID_personal = ?";    // Set data admin from database
        preparedStatement_all = con.prepareStatement(sql_all);
        preparedStatement_all.setString(1, "00");
        resultSet = preparedStatement_all.executeQuery();
        //System.out.println(resultSet);
        if (resultSet.next()) {

            ow.setRole(resultSet.getString(1));
            ow.setIdPersonal(resultSet.getString(2));
            ow.setName(resultSet.getString(3));
            ow.setUsername(resultSet.getString(4));
            ow.setPassword(resultSet.getString(5));

//            System.out.println(ow.toString());
        }

        String sql_all_workorder = "SELECT * FROM Work_order ";
        preparedStatement_all = con.prepareStatement(sql_all_workorder);
        resultSet = preparedStatement_all.executeQuery();

        while (resultSet.next()) {

            workorder = new Workorder(resultSet.getString(1), resultSet.getString(5), resultSet.getString(2), resultSet.getString(3),
                    resultSet.getFloat(6), resultSet.getString(7), resultSet.getString(8), resultSet.getString(4));

            workorderList.addWorkOrderToList(workorder);

        }
        System.out.println(workorderList.getWorkbook());
        System.out.println("Set all workorder");

        String sql_all_bill = "SELECT * FROM bill_of_lading ";
        preparedStatement_all = con.prepareStatement(sql_all_bill);
        resultSet = preparedStatement_all.executeQuery();

        while (resultSet.next()) {

            bill = new BillLading(resultSet.getString(1),resultSet.getString(3)
                    ,resultSet.getString(4),resultSet.getString(5),
                    resultSet.getString(2),resultSet.getString(6));

            billList.addBillToList(bill);

        }
        System.out.println(billList.getBillList());
        System.out.println("Set all bill of lading");


        String sql_picker = "SELECT Name_personal FROM User WHERE Role_user = ? ";
        preparedStatement_all = con.prepareStatement(sql_picker);
        preparedStatement_all.setString(1, "Employee");
        resultSet = preparedStatement_all.executeQuery();

        Owner staff = new Owner();

        while (resultSet.next()){
            arrayCount.add(resultSet.getString(1));
            //System.out.println(arrayCount);
        }
        System.out.println(arrayCount);

       for(int i = 0; i < arrayCount.size(); i++) {
            namePickerCBB.getItems().addAll(arrayCount.get(i));

       }



    }
    public CreateBillOfLadingController() {

        con = ConnectDatabase.connectDB();

    }




    @FXML public void ConfirmButton(ActionEvent actionEvent)throws IOException {

        if (this.nameAndQuantityText.getText().isEmpty()  || datePicker.getEditor().getText().isEmpty() ||
        timeText.getText().isEmpty() || namePickerCBB == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please enter all data of bill of lading to complete");
            alert.showAndWait();

        }else if(!ch.validateName(this.nameAndQuantityText.getText())){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please check your pattern of name device!");
            alert.showAndWait();


        }else if (!ch.checkTime(this.timeText.getText())){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please check your pattern of time bill!");
            alert.showAndWait();
        }
        else if (!(nameAndQuantityText.getText().equals("")) && datePicker != null
            && !(timeText.getText().equals("")) && namePickerCBB != null){

            System.out.println("สร้าง Bill ได้");

            Alert alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
            alert.setHeight(100);
            alert.setWidth(200);
            alert.setTitle("CONFIRMATION");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to create work order ? ");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                createBill();
                nameAndQuantityText.clear();
                datePicker.getEditor().clear();
                timeText.clear();

            }

        } else {
            System.out.println("สร้างไม่ได้");
        }

    }

    public void showDataOfBill(){

        String id_bill = "B" + billList.getLengthArrayList();
        String name_device = nameAndQuantityText.getText();
        String date = datePicker.getEditor().getText();
        String time = timeText.getText();
        String pickername = namePickerCBB.getValue();

    }

    public void createBill(){

        showDataOfBill();

        String sql = "INSERT INTO Bill_of_lading(ID_bill_of_lading, picker_name, Device_nameAndQty, pick_date, pick_time, Status_bill, Note_bill) VALUES (?,?,?,?,?,?,?)";
        try {

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, "B" + billList.getLengthArrayList());
            preparedStatement.setString(2, namePickerCBB.getValue());
            preparedStatement.setString(3, nameAndQuantityText.getText());
            preparedStatement.setString(4, datePicker.getEditor().getText());
            preparedStatement.setString(5, timeText.getText());
            preparedStatement.setString(6,"Pending approval");
            preparedStatement.setString(7,"-");

            preparedStatement.executeUpdate();
            System.out.println("Save data of bill in DB");

        }catch (SQLException ex){
            ex.printStackTrace();
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


