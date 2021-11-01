package shop.controllers.employee;

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
import shop.models.Owner;
import shop.models.Workorder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListWorkOrderEmployeeController {

    private Workorder selectWorkorder;
    private ObservableList<Workorder> workbook;
    private Workorder workorder = new Workorder();
    private Owner ow = new Owner();

    Workorder workorderList = new Workorder();

    Connection con;

    PreparedStatement preparedStatement, preparedStatement_all;
    ResultSet resultSet;

    @FXML private TableView<Workorder> table_work_order_staff1;

    public ListWorkOrderEmployeeController(){
        con = ConnectDatabase.connectDB();
    }

    @FXML public void initialize() throws SQLException {

        Platform.runLater(() -> {

            showTableView();

        });

        table_work_order_staff1.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
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

    public void showTableView(){

        workbook = FXCollections.observableArrayList(workorderList.getWorkbook());

        table_work_order_staff1.setItems(workbook);

        TableColumn onOrder = new TableColumn("ID Order");
        TableColumn nameCustomer = new TableColumn("Name");
        TableColumn addressCustomer = new TableColumn("Address");
        TableColumn phoneCustomer = new TableColumn("Phone");
        TableColumn price = new TableColumn("Price");
        TableColumn date = new TableColumn("Date");
        TableColumn time = new TableColumn("Time");
        TableColumn statusOrder = new TableColumn("Status");
        TableColumn liable = new TableColumn("Leader");


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


        table_work_order_staff1.getColumns().addAll(onOrder, nameCustomer, addressCustomer
                , phoneCustomer, price, date, time, statusOrder
                , liable);
    }

    private void showSelectedWorkorder(Workorder workorder) {
        selectWorkorder = workorder;


        System.out.println(selectWorkorder.toString());

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
