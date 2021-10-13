package shop.models;

import java.util.ArrayList;

public class Owner {

    private String idPersonal;
    private String username;
    private String password;
    private String role;

    
    public Owner (){

        this.idPersonal = "00";
        this.username = "admin2009";
        this.password = "1234";
        this.role = "Owner";
    }

    public Owner(String idPersonal, String username, String password, String role) {
        this.idPersonal = idPersonal;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean checkUsername(String username){
        return username.equals(username);
    }

    public boolean checkPassword(String password){
        return password.equals(password);
    }


    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getIdPersonal() {
        return idPersonal;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id_personal='" + idPersonal + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
