package shop.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Employee {

    private String id_personal;
    private String name;
    private String surname;
    private String role;


    public Employee(String id_personal, String name, String surname, String role) {
        this.id_personal = id_personal;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public String getId_personal() {
        return id_personal;
    }

    public void setId_personal(String id_personal) {
        this.id_personal = id_personal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
