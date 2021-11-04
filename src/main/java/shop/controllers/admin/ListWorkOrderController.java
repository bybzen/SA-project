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
import shop.models.Owner;
import shop.models.Workorder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    ArrayList<String> arrayCount = new ArrayList<>();


    Connection con;
    PreparedStatement preparedStatement, preparedStatement_all;
    ResultSet resultSet;





    @FXML public void initialize() throws SQLException {


        editBtn.setDisable(true); //ปิดปุ่ม
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


            workorder = new Workorder(resultSet.getString(1), resultSet.getString(5)
                    , resultSet.getString(2), resultSet.getString(3)
                    ,resultSet.getFloat(6), resultSet.getString(7)
                    , resultSet.getString(8), resultSet.getString(4)
                    , resultSet.getString(9));

            workorderList.addWorkOrderToList(workorder);

        }
        System.out.println(workorderList.getWorkbook());
        System.out.println("Set all workorder");

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
            leaderCBB.getItems().addAll(arrayCount.get(i));

        }

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


        tableWorkorder.getColumns().addAll(onOrder, nameCustomer, addressCustomer
                                        , phoneCustomer, price, date, time, statusOrder
                                        , liable);
    }

    private void showSelectedWorkorder(Workorder workorder) {

        selectWorkorder = workorder;

        editBtn.setDisable(false); //เปิดปุ่ม

        nameText.setText(selectWorkorder.getNameCustomer());
        addressText.setText(selectWorkorder.getAddressCustomer());
        phoneText.setText(selectWorkorder.getPhoneCustomer());
        timeText.setText(selectWorkorder.getTime());
        priceText.setText(String.valueOf(selectWorkorder.getPrice()));
        datePicker.getEditor().setText(selectWorkorder.getDate());
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
        editBtn.setDisable(true); //ปิดปุ่ม

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
                        && !(datePicker.getEditor().getText().equals(""))
                        && !(priceText.getText().equals("")) && statusCBB != null || leaderCBB != null)
                {
                    selectWorkorder.setNameCustomer(nameText.getText());
                    selectWorkorder.setAddressCustomer(addressText.getText());
                    selectWorkorder.setPhoneCustomer(phoneText.getText());
                    selectWorkorder.setDate(datePicker.getEditor().getText());
                    selectWorkorder.setTime(timeText.getText());
                    selectWorkorder.setPrice(Float.parseFloat(priceText.getText()));
                    selectWorkorder.setStatusOrder(statusCBB.getValue());
                    selectWorkorder.setLiable(leaderCBB.getValue());

                    String sql = "UPDATE Work_order SET Address_customer_workorder = ?, Tel_customer_workorder = ?, Status_workorder = ?" +
                            "                           , Name_customer_workorder = ?, Price_workorder = ?, Installation_date_workorder = ?, Installation_time_workorder = ?" +
                            "                           , Leader_workorder = ? WHERE ID_customer_workorder = ?  ";

                    try {

                        preparedStatement = con.prepareStatement(sql);
                        preparedStatement.setString(1, addressText.getText());
                        preparedStatement.setString(2, phoneText.getText());
                        preparedStatement.setString(3, statusCBB.getValue());
                        preparedStatement.setString(4, nameText.getText());
                        preparedStatement.setString(5, priceText.getText());
                        preparedStatement.setString(6, datePicker.getEditor().getText());
                        preparedStatement.setString(7, timeText.getText());
                        preparedStatement.setString(8, leaderCBB.getValue());
                        preparedStatement.setString(9, selectWorkorder.getOnOrder());

                        preparedStatement.executeUpdate();
                        System.out.println("Edit data of WO in DB");

                    }catch (SQLException ex){
                        ex.printStackTrace();
                    }

//                    workorder = new Workorder(nameText.getText(), addressText.getText()
//                            , phoneText.getText(), Float.parseFloat(priceText.getText())
//                            , timeText.getText(), statusCBB.getValue(),leaderCBB.getValue());
//
//                    workorderList.addWorkOrderToList(workorder);

                    System.out.println("ใน list " + workorderList.getWorkbook());
                    System.out.println(workorderList.getLengthArrayList());

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
