package shop.controllers.admin;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class ListBillOfLadingAdminController {


    private BillLading selectBill;
    private ObservableList<BillLading> billList;
    BillLading bill;
    BillLading bList = new BillLading();;

    Owner ow = new Owner();

    Workorder workorder;
    Workorder workorderList = new Workorder();


    @FXML private TableView<BillLading> tableBillOfLading;

    Connection con;

    PreparedStatement preparedStatement,preparedStatement_all;
    ResultSet resultSet;


    @FXML public void initialize() throws SQLException {

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
                    resultSet.getString(2));

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

        TableColumn onOrder = new TableColumn("ID Bill");
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


        tableBillOfLading.getColumns().addAll(onOrder, nameCustomer, addressCustomer
                , phoneCustomer, price, date, time, statusOrder
                , liable);


    }

    private void showSelectedBill(BillLading bill) {
        selectBill = bill;


        System.out.println(selectBill.toString());

    }

    private void clearSelectedBill() {
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
