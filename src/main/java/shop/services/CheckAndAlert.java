package shop.services;

import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckAndAlert {
    
    public boolean validatePhone(CharSequence Phone){
        Pattern p = Pattern.compile("\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}");
        Matcher m = p.matcher(Phone);
        if(m.find() && m.group().equals(Phone)){
            return true ;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeight(100);
            alert.setWidth(200);
            alert.setTitle("Validate PhoneNumber");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid PhoneNumber");
            alert.showAndWait();
            return false ;
        }
    }

    public boolean validateName(String name){
        Pattern p = Pattern.compile("[A-Za-z_]+");
        Matcher m = p.matcher(name);
        if(m.find() && m.group().equals(name)){
            return true ;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeight(100);
            alert.setWidth(200);
            alert.setTitle("Validate Name");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Name");
            alert.showAndWait();
            return false ;
        }
    }

    public boolean validateSurName(String surname){
        Pattern p = Pattern.compile("[A-Za-z_]+");
        Matcher m = p.matcher(surname);
        if(m.find() && m.group().equals(surname)){
            return true ;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeight(100);
            alert.setWidth(200);
            alert.setTitle("Validate Name");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Name");
            alert.showAndWait();
            return false ;
        }
    }
    public boolean validateUsername(String username){
        Pattern p = Pattern.compile("[A-Za-z0-9_]+");
        Matcher m = p.matcher(username);
        if(m.find() && m.group().equals(username)){
            return true ;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeight(100);
            alert.setWidth(200);
            alert.setTitle("Validate Username");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Username");
            alert.showAndWait();
            return false ;
        }
    }
    public boolean validatePassword(String password){
        Pattern p = Pattern.compile("[A-Za-z0-9_]+");
        Matcher m = p.matcher(password);
        if(m.find() && m.group().equals(password)){
            return true ;
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeight(100);
            alert.setWidth(200);
            alert.setTitle("Validate Password");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter Valid Password");
            alert.showAndWait();
            return false ;
        }

    }
}
