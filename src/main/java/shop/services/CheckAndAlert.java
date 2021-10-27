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
        if(m.find()){
            return true ;
        }else{
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
    public boolean validateId(String id){
        Pattern p = Pattern.compile("[A-Za-z0-9]+");
        Matcher m = p.matcher(id);
        if(m.find()){
            return true ;
        }else{
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

    public boolean checkTime(String time){
        Pattern VALID_TIME_REGEX =
                Pattern.compile("^(?:0?[0-9]|1[0-2])[-:][0-5][0-9]\\s*[ap]m$", Pattern.CASE_INSENSITIVE);

        Matcher matcher = VALID_TIME_REGEX.matcher(time);

        if (matcher.find()){
            return true;
        } else {
            return false;
        }
    }
}
