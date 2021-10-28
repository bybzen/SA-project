package shop.controllers.admin;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import shop.models.BillLading;
import shop.models.Workorder;

import java.io.IOException;
import java.sql.SQLException;

public class ListBillOfLadingAdminController {
    @FXML ComboBox status_combobox;


    private BillLading selectBill;
    private ObservableList<BillLading> billList;
    private BillLading billLading = new BillLading();

    @FXML private TableView<BillLading> tableBillOfLading;


    @FXML public void initialize() throws SQLException {

        Platform.runLater(() -> {

            showTableView();

        });

        tableBillOfLading.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedBill(newValue);
            }
        });



    }


    public void showTableView(){

        billList = FXCollections.observableArrayList(billLading.getBillList());

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
