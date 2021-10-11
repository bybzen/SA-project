package shop.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import shop.models.Owner;

import java.io.IOException;

public class LoginController {

    @FXML TextField username_textfield;
    @FXML TextField password_textfield;

    Owner ow = new Owner();
    Alert alert;

    @FXML
    public void ConfirmlogInAction(ActionEvent event) throws IOException {

        String username_input = username_textfield.getText();
        String password_input = password_textfield.getText();

//<<<<<<< HEAD
        //Owner ow = new Owner();

        if (this.username_textfield.getText().equals("") && this.password_textfield.getText().equals("") ) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(" ");
            alert.setContentText("Please enter username and password");
            alert.showAndWait();
        }
        else {
//=======
            if (username_input.equals(ow.getUsername()) && password_input.equals(ow.getPassword())){
                try {
                    FXRouter.goTo("AdminMenu");
                } catch (IOException e) {
                    System.err.println("ไปที่หน้า AdminMenu ไม่ได้");
                    System.err.println("ให้ตรวจสอบการกำหนด route");
                }

            } else if (this.username_textfield.getText().equals("") && !(this.password_textfield.getText().equals(""))) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(" ");
                alert.setContentText("Please enter username");
                alert.showAndWait();

            }else if (this.password_textfield.getText().equals("") && !(this.username_textfield.getText().equals(""))) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(" ");
                alert.setContentText("Please enter password");
                alert.showAndWait();

            } else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(" ");
                alert.setContentText("Please check your username or password");
                alert.showAndWait();
            }

        }
    }

    @FXML public void handleBackButton (ActionEvent actionEvent) throws IOException{
        try {
            FXRouter.goTo("Home");
        } catch (IOException var3) {
            System.err.println("ไปที่หน้า Home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }

    }

}