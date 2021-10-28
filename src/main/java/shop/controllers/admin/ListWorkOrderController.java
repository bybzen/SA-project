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
import shop.models.Device;
import shop.models.Workorder;

import java.io.IOException;
import java.sql.SQLException;

public class ListWorkOrderController {

    private Workorder selectWorkorder;
    private ObservableList<Workorder> workbook;
    private Workorder workorder = new Workorder();

    @FXML private TableView<Workorder> tableWorkorder;


    @FXML public void initialize() throws SQLException {

        Platform.runLater(() -> {

            showTableView();

        });

        tableWorkorder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedDevice(newValue);
            }
        });



    }


    public void showTableView(){

        workbook = FXCollections.observableArrayList(workorder.getWorkbook());

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

    private void showSelectedDevice(Workorder workorder) {
        selectWorkorder = workorder;


        System.out.println(selectWorkorder.toString());

    }

    private void clearSelectedDevice() {
//        Withdraw_Of_Device_Textfield.clear(); // เคลียร์ช่องลด
//        add_device_textfield.clear(); // เคลียร์ช่องเพิ่ม
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
            try {
                FXRouter.goTo("EditWorkOrder");
            } catch (IOException e) {
                System.err.println("ไปที่หน้า EditWorkOrder ไม่ได้");
                System.err.println("ให้ตรวจสอบการกำหนด route");
            }
        }
}
