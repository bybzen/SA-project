package shop.models;

public class Workorder {
    private String on_order;  //เลขที่ใบงาน
    private String name_customer;
    private String surname_customer;
    private String address_customer;
    private String datetime; //วัน-เวลา
    private String status_order; //สถานะ order
    private String liable; //พนักงานที่รับผิดชอบ

    public Workorder(String on_order, String name_customer, String surname_customet,
                     String address_customer, String datetime, String status_order, String liable) {

        this.on_order = on_order;
        this.name_customer = name_customer;
        this.surname_customer = surname_customet;
        this.address_customer = address_customer;
        this.datetime = datetime;
        this.status_order = status_order;
        this.liable = "-";
    }

    public void setOn_order(String on_order) {
        this.on_order = on_order;
    }

    public void setName_customer(String name_customer) {
        this.name_customer = name_customer;
    }

    public void setSurname_customet(String surname_customet) {
        this.surname_customer = surname_customet;
    }

    public void setAddress_customer(String address_customer) {
        this.address_customer = address_customer;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setStatus_order(String status_order) {
        this.status_order = status_order;
    }

    public void setLiable(String liable) {
        this.liable = liable;
    }

    public String getOnOrder() {
        return on_order;
    }

    public String getName_customer() {
        return name_customer;
    }

    public String getSurname_customet() {
        return surname_customer;
    }

    public String getAddress_customer() {
        return address_customer;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getStatus_order() {
        return status_order;
    }

    public String getLiable() {
        return liable;
    }

    @Override
    public String toString() {
        return "Workorder{" +
                "on_order=" + on_order +
                ", name_customer='" + name_customer + '\'' +
                ", surname_customer='" + surname_customer + '\'' +
                ", address_customer='" + address_customer + '\'' +
                ", datetime='" + datetime + '\'' +
                ", status_order='" + status_order + '\'' +
                ", liable='" + liable + '\'' +
                '}';
    }
}
