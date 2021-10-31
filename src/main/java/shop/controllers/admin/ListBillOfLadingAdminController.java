package shop.controllers.admin;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import shop.controllers.ConnectDatabase;
import shop.models.BillLading;
import shop.models.Owner;
import shop.models.Workorder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ListBillOfLadingAdminController {


    private BillLading selectBill;
    private ObservableList<BillLading> billList;
    BillLading bill;
    BillLading bList = new BillLading();;

    Owner ow = new Owner();

    Workorder workorder;
    Workorder workorderList = new Workorder();

    @FXML ComboBox<String> status_combobox;


    @FXML private TableView<BillLading> tableBillOfLading;

    Connection con;

    PreparedStatement preparedStatement,preparedStatement_all;
    ResultSet resultSet;


    @FXML public void initialize() throws SQLException {

        status_combobox.getItems().addAll("Allowed", "Not allowed", "Wait allow");

        Platform.runLater(() -> {

            showTableView();

        });

        tableBillOfLading.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedBill(newValue);
            }
        });

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

            bList.addBillToList(bill);

        }
        System.out.println(bList.getBillList());
        System.out.println("Set all bill of lading");



    }

    public ListBillOfLadingAdminController(){
        con = ConnectDatabase.connectDB();
    }


    public void showTableView(){

        billList = FXCollections.observableArrayList(bList.getBillList());

        tableBillOfLading.setItems(billList);

        TableColumn idBillLading = new TableColumn("ID Bill");
        TableColumn nameAndQuantityDevice = new TableColumn("Detail");
        TableColumn date = new TableColumn("Date");
        TableColumn time = new TableColumn("Time");
        TableColumn pickName = new TableColumn("Name");
        TableColumn status = new TableColumn("Status");


        idBillLading.setCellValueFactory(
                new PropertyValueFactory<Workorder, String>("idBillLading")
        );
        nameAndQuantityDevice.setCellValueFactory(
                new PropertyValueFactory<Workorder, String>("nameAndQuantityDevice")
        );

        date.setCellValueFactory(
                new PropertyValueFactory<Workorder, String>("date")
        );
        time.setCellValueFactory(
                new PropertyValueFactory<Workorder, String>("time")
        );
        pickName.setCellValueFactory(
                new PropertyValueFactory<Workorder, String>("pickName")
        );
        status.setCellValueFactory(
                new PropertyValueFactory<Workorder, String>("status")
        );



        tableBillOfLading.getColumns().addAll(idBillLading, nameAndQuantityDevice, date
                                                , time, pickName, status);


    }

    private void showSelectedBill(BillLading bill) {
        selectBill = bill;

        //selectBill.setStatus(status_combobox.getValue());


        System.out.println(selectBill.toString());

    }


    @FXML public void updateBtn(ActionEvent actionEvent) throws SQLException {

            Alert alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
            alert.setHeight(100);
            alert.setWidth(200);
            alert.setTitle("CONFIRMATION");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to edit status of bill of lading ? ");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                editBill();
            }

        }


    public void editBill() throws SQLException {

        selectBill.setStatus(status_combobox.getValue());

        String sql = "UPDATE bill_of_lading SET Status_bill = ? WHERE ID_bill_of_lading = ?  ";

        try {

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, status_combobox.getValue());
            preparedStatement.setString(2, selectBill.getIdBillLading());

            preparedStatement.executeUpdate();
            System.out.println("Edit status of bill of lading in DB");

        }catch (SQLException ex){
            ex.printStackTrace();
        }

        System.out.println(bList.getBillList());

        clearSelectedBill();
        tableBillOfLading.refresh();
        tableBillOfLading.getSelectionModel().clearSelection();
    }

    private void clearSelectedBill() {

        status_combobox.setValue(null);
//        Withdraw_Of_Device_Textfield.clear(); // เคลียร์ช่องลด
//        add_device_textfield.clear(); // เคลียร์ช่องเพิ่ม
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
