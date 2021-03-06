package shop.controllers.admin;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import shop.controllers.ConnectDatabase;
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

public class CreateWorkorderController {

    @FXML TextField name_costomer_textfield;
    @FXML TextArea address_textfield;
    @FXML TextField tel_textfield;
    @FXML TextField time_textfield;
    @FXML ComboBox<String> leaderCBB;
    @FXML TextField price_textfield;
    @FXML DatePicker date_picker;
    @FXML ComboBox<String> status_combobox;
    Workorder workorder;
    Workorder workorderList = new Workorder();
    Owner ow = new Owner();
    ArrayList<String> arrayCount = new ArrayList<>();

    PreparedStatement preparedStatement, preparedStatement_all;
    ResultSet resultSet, resultSet2;

    Alert alert;
    CheckAndAlert ch = new CheckAndAlert();
    Connection con;

    @FXML
    public void initialize() throws SQLException {

        status_combobox.getItems().addAll("Pending", "Confirm", "Pending edit", "Go out to install", "Complete");

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

        }

        String sql_all_workorder = "SELECT * FROM Work_order ";
        preparedStatement_all = con.prepareStatement(sql_all_workorder);
        resultSet = preparedStatement_all.executeQuery();

        while (resultSet.next()) {

            workorder = new Workorder(resultSet.getString(1), resultSet.getString(5)
                    , resultSet.getString(2), resultSet.getString(3)
                    , resultSet.getFloat(6), resultSet.getString(7)
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

    public CreateWorkorderController() {

        con = ConnectDatabase.connectDB();

    }


    //???????????? confirm
    @FXML
    public void ConfirmButton(ActionEvent actionEvent) {

        if (this.name_costomer_textfield.getText().isEmpty() || this.address_textfield.getText().isEmpty() || this.tel_textfield.getText().isEmpty() ||
                this.date_picker.getEditor().getText().isEmpty() || this.time_textfield.getText().isEmpty() || this.price_textfield.getText().isEmpty() || status_combobox == null ) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please enter all data of workorder to complete");
            alert.showAndWait();

        } else if (!ch.validateName(this.name_costomer_textfield.getText())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please check your pattern of name customer!");
            alert.showAndWait();

        } else if (!ch.validatePhone(this.tel_textfield.getText())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please check your pattern of tel customer!");
            alert.showAndWait();

        } else if (!ch.checkTime(this.time_textfield.getText())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please check your pattern of time customer!");
            alert.showAndWait();

        } else if (!ch.checkPrice(this.price_textfield.getText())) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please check your pattern of price!");
            alert.showAndWait();

        } else if (!(name_costomer_textfield.getText().equals("")) && !(address_textfield.getText().equals(""))
                && !(tel_textfield.getText().equals("")) && !(date_picker.getEditor().getText().equals(""))
                && !(time_textfield.getText().equals("")) && !(price_textfield.getText().equals("")) && status_combobox != null) {

            System.out.println("???????????????????????????????????????");

            Alert alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
            alert.setHeight(100);
            alert.setWidth(200);
            alert.setTitle("CONFIRMATION");
            alert.setHeaderText(null);
            alert.setContentText("Do you want to create work order ? ");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {
                createWO();
                name_costomer_textfield.clear();
                address_textfield.clear();
                tel_textfield.clear();
                date_picker.getEditor().clear();
                time_textfield.clear();
                price_textfield.clear();
                status_combobox.setValue(null);
                leaderCBB.setValue(null);
            }



//            System.out.println(date_picker.getEditor().getText()); get ????????????????????? DatePicker
            } else {
                System.out.println("?????????????????????????????????");
            }


        }


        public void showDataOfWO () {

            String id_workorder = "W" + workorderList.getLengthArrayList();
            String name_customer = name_costomer_textfield.getText();
            String address_customer = address_textfield.getText();
            String tel_customer = tel_textfield.getText();
            float price_workorder = Float.parseFloat(price_textfield.getText());
            String date_workorder = date_picker.getEditor().getText();
            String time_workorder = time_textfield.getText();
            String status_workorder = status_combobox.getValue();
            String leader_workorder = leaderCBB.getValue();


        }

        public void createWO() {
            showDataOfWO();

            String sql = "INSERT INTO Work_order (ID_customer_workorder, Address_customer_workorder, Tel_customer_workorder, Status_workorder, Name_customer_workorder, Price_workorder, Installation_date_workorder, Installation_time_workorder, Leader_workorder)  VALUES (?,?,?,?,?,?,?,?,?)";

            try {

                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, "W" + workorderList.getLengthArrayList());
                preparedStatement.setString(2, address_textfield.getText());
                preparedStatement.setString(3, tel_textfield.getText());
                preparedStatement.setString(4, status_combobox.getValue());
                preparedStatement.setString(5, name_costomer_textfield.getText());
                preparedStatement.setString(6, price_textfield.getText());
                preparedStatement.setString(7, date_picker.getEditor().getText());
                preparedStatement.setString(8, time_textfield.getText());

                if (leaderCBB.getValue() == null){
                preparedStatement.setString(9, "Not specified");
                }
                else {
                    preparedStatement.setString(9, leaderCBB.getValue());
                }

                preparedStatement.executeUpdate();
                System.out.println("Save data of WO in DB");

            }catch (SQLException ex){
                ex.printStackTrace();
            }

            workorder = new Workorder("W" + workorderList.getLengthArrayList(), name_costomer_textfield.getText(), address_textfield.getText()
                    , tel_textfield.getText(), Float.parseFloat(price_textfield.getText())
                    , date_picker.getEditor().getText(), time_textfield.getText()
                    , status_combobox.getValue(), leaderCBB.getValue());

            workorderList.addWorkOrderToList(workorder);

            System.out.println("?????? list " + workorderList.getWorkbook());
            System.out.println(workorderList.getLengthArrayList());
        }

        @FXML
        public void BackButton (ActionEvent actionEvent){
            try {
                FXRouter.goTo("AdminMenu");
            } catch (IOException e) {
                System.err.println("??????????????????????????? AdminMenu ??????????????????");
                System.err.println("?????????????????????????????????????????????????????? route");
            }
        }
    }

