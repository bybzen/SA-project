package shop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import shop.models.Owner;

import java.io.IOException;

public class LoginController {

    @FXML
    TextField username_textfield;
    @FXML
    TextField password_textfield;

    @FXML
    public void ConfirmlogInAction(ActionEvent event) throws IOException {

        String username_input = username_textfield.getText();
        String password_input = password_textfield.getText();

        Owner ow = new Owner();


    }

}