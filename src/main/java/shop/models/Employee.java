package shop.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Employee {
    private String name;
    private String surname;
    private int age;


    public Employee (String name, String surname, int age){
        this.name = name ;
        this.surname = surname;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public String getSurname(){
        return surname;
    }

    public int getAge(){
        return age;
    }

}
