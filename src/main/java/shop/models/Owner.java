package shop.models;

import java.util.ArrayList;

public class Owner {

    private String username;
    private String password;
    private ArrayList<Employee> listOfEmployee; //list รายชื่อพนักงาน

    public Owner (){
        this.username = "admin2009";
        this.password = "maxmotives";
    }

    public void addToEmployeeList(Employee employee){  //add พนักงานเข้า list รายชื่อ
        listOfEmployee.add(employee);
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

    @Override
    public String toString() {
        return "Owner{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
