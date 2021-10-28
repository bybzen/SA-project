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

        TableColumn idBillLading = new TableColumn("ID Bill");
        TableColumn nameAndQuantityDevice = new TableColumn("Detail");
        TableColumn date = new TableColumn("Date");
        TableColumn time = new TableColumn("Time");
        TableColumn pickName = new TableColumn("Name");


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



        tableBillOfLading.getColumns().addAll(idBillLading, nameAndQuantityDevice, date
                                                , time, pickName);


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
