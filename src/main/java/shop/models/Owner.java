package shop.models;

import java.util.ArrayList;

public class Owner {

    private String id_personal;
    private String username;
    private String password;
    private String role;
    private String name;
    private ArrayList<Employee> listOfEmployee; //list รายชื่อพนักงาน

    public Owner() {
        this.username = "admin2009";
        this.password = "1234";
    }

    public Owner(String id_personal, String username, String password, String role, String name) {
        this.id_personal = id_personal;
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name ;
    }
    

    public String getName() {
        return name;
    }

    public void addEmployeeToList(Employee employee){  //add พนักงานเข้า list รายชื่อ
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

    public String getRole() {
        return role;
    }

    public String getId_personal() {
        return id_personal;
    }

    public ArrayList<Employee> getListOfEmployee() {
        return listOfEmployee;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id_personal='" + id_personal + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
