package shop.controllers.admin;
import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import shop.controllers.ConnectDatabase;
import shop.models.Owner;
import shop.models.Workorder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ListWorkOrderController {

    private Workorder selectWorkorder;
    private ObservableList<Workorder> workbook;
    private Workorder workorder = new Workorder();
    private Owner ow = new Owner();

    Workorder workorderList = new Workorder();

    @FXML TableView<Workorder> tableWorkorder;
    @FXML TextField nameText ;
    @FXML TextArea addressText;
    @FXML TextField phoneText;
    @FXML DatePicker datePicker;
    @FXML TextField timeText;
    @FXML ComboBox<String> leaderCBB;
    @FXML ComboBox<String> statusCBB;
    @FXML TextField priceText;
    @FXML private Button editBtn;

    Connection con;
    PreparedStatement preparedStatement, preparedStatement_all;
    ResultSet resultSet;





    @FXML public void initialize() throws SQLException {


        statusCBB.getItems().addAll("Pending", "Confirm", "Pending edit", "Go out to install", "Complete");

        Platform.runLater(() -> {

            showTableView();

        });

        tableWorkorder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedWorkorder(newValue);
            }
        });

        String sql_all = "SELECT * FROM User WHERE ID_personal = ?";    // Set data admin from database
        preparedStatement_all = con.prepareStatement(sql_all);
        preparedStatement_all.setString(1, "00");
        resultSet = preparedStatement_all.executeQuery();

        if (resultSet.next()) {

            ow.setRole(resultSet.getString(1));
            ow.setIdPersonal(resultSet.getString(2));
            ow.setName(resultSet.getString(3));
            ow.setUsername(resultSet.getString(4));
            ow.setPassword(resultSet.getString(5));

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



    }

    public ListWorkOrderController(){
        con = ConnectDatabase.connectDB();
    }


    public void showTableView(){

        workbook = FXCollections.observableArrayList(workorderList.getWorkbook());

        tableWorkorder.setItems(workbook);

        TableColumn onOrder = new TableColumn("ID Order");
        TableColumn nameCustomer = new TableColumn("Name");
        TableColumn addressCustomer = new TableColumn("Address");
        TableColumn phoneCustomer = new TableColumn("Phone");
        TableColumn price = new TableColumn("Price");
        TableColumn date = new TableColumn("Date");
        TableColumn time = new TableColumn("Time");
        TableColumn statusOrder = new TableColumn("Status");
        TableColumn liable = new TableColumn("Liable");


        onOrder.setCellValueFactory(
                new PropertyValueFactory<Workorder, String>("onOrder")
        );
        nameCustomer.setCellValueFactory(
                new PropertyValueFactory<Workorder, String>("nameCustomer")
        );
        addressCustomer.setCellValueFactory(
                new PropertyValueFactory<Workorder, String>("addressCustomer")
        );
        phoneCustomer.setCellValueFactory(
                new PropertyValueFactory<Workorder, String>("phoneCustomer")
        );
        price.setCellValueFactory(
                new PropertyValueFactory<Workorder, String>("price")
        );
        date.setCellValueFactory(
                new PropertyValueFactory<Workorder, String>("date")
        );
        time.setCellValueFactory(
                new PropertyValueFactory<Workorder, String>("time")
        );
        statusOrder.setCellValueFactory(
                new PropertyValueFactory<Workorder, String>("statusOrder")
        );
        liable.setCellValueFactory(
                new PropertyValueFactory<Workorder, String>("liable")
        );


        tableWorkorder.getColumns().addAll(onOrder, nameCustomer, addressCustomer
                                        , phoneCustomer, price, date, time, statusOrder
                                        , liable);
    }

    private void showSelectedWorkorder(Workorder workorder) {
        selectWorkorder = workorder;

        nameText.setText(selectWorkorder.getNameCustomer());
        addressText.setText(selectWorkorder.getAddressCustomer());
        phoneText.setText(selectWorkorder.getPhoneCustomer());
        timeText.setText(selectWorkorder.getTime());
        priceText.setText(String.valueOf(selectWorkorder.getPrice()));

        leaderCBB.setValue(selectWorkorder.getLiable());
        statusCBB.setValue(selectWorkorder.getStatusOrder());


        System.out.println(selectWorkorder.toString());

    }

    private void clearSelectedWorkorder() {
        nameText.clear();
        addressText.clear();
        phoneText.clear();
        datePicker.getEditor().clear();
        timeText.clear();
        priceText.clear();
        leaderCBB.setValue(null);
        statusCBB.setValue(null);

    }


    @FXML public void BackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("AdminMenu");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า AdminMenu ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
    
    @FXML public void EditButton(ActionEvent actionEvent) {
//            try {

                if (!(nameText.getText().equals("")) && !(addressText.getText().equals(""))
                        && !(phoneText.getText().equals("")) && !(timeText.getText().equals(""))
                        && !(priceText.getText().equals("")) && statusCBB != null && leaderCBB != null)
                {
                    selectWorkorder.setNameCustomer(nameText.getText());
                    selectWorkorder.setAddressCustomer(addressText.getText());
                    selectWorkorder.setPhoneCustomer(phoneText.getText());
                    selectWorkorder.setTime(timeText.getText());
                    selectWorkorder.setPrice(Float.parseFloat(priceText.getText()));
                    selectWorkorder.setStatusOrder(statusCBB.getValue());
                    selectWorkorder.setLiable(leaderCBB.getValue());

                    if (!(datePicker.getEditor().getText().equals(""))){
                        selectWorkorder.setDate(datePicker.getEditor().getText());
                        System.out.println("วันที่ไม่ null ต้อง set");
                    }

                    else if (datePicker.getEditor().getText().isEmpty()){
                        System.out.println("วันที่ null ไม่ต้อง set");
                    }




                    System.out.println("แก้ไขได้");

                    clearSelectedWorkorder();
                    tableWorkorder.refresh();
                    tableWorkorder.getSelectionModel().clearSelection();

                }

            }


//                FXRouter.goTo("EditWorkOrder");

//            }
//            catch (IOException e)
//            {
//                System.err.println("ไปที่หน้า EditWorkOrder ไม่ได้");
//                System.err.println("ให้ตรวจสอบการกำหนด route");
//                e.printStackTrace();
//            }
//        }
}
