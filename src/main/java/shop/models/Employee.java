package shop.models;

import java.util.ArrayList;

public class Employee {

    private String idPersonal;
    private String name;
    private String surname;
    private String role;
    private ArrayList<Employee> employeeList = new ArrayList<>(); //list รายชื่อพนักงาน

    public Employee(String id_personal, String name, String surname, String role) {
        this.idPersonal = id_personal;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }


    public ArrayList<Employee> getEmployeesList() {  // Get all data employees
        return employeeList;
    }

    public void addEmployeeToList(Employee emp){  // Add employee to list
        employeeList.add(emp);
    }

    public String getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(String idPersonal) {
        this.idPersonal = idPersonal;
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

    @Override
    public String toString() {
        return "Employee{" +
                "idPersonal='" + idPersonal + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
