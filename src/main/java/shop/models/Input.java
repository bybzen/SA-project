package shop.models;

public class Input {
    String name;
    String call_number;
    String id_student;

    public Input(String name, String call_number, String id_student) {
        this.name = name;
        this.call_number = call_number;
        this.id_student = id_student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCall_number() {
        return call_number;
    }

    public void setCall_number(String call_number) {
        this.call_number = call_number;
    }

    public void setId_student(String id_student) {
        this.id_student = id_student;
    }

    public String getId_student() {
        return id_student;
    }

    @Override
    public String toString() {
        return "Input{" +
                "name='" + name + '\'' +
                ", call_number='" + call_number + '\'' +
                ", id_student='" + id_student + '\'' +
                '}';
    }
}
