package shop.models;

import java.util.ArrayList;

public class Workorder {
    private String onOrder;  //เลขที่ใบงาน
    private String nameCustomer;
    private String addressCustomer;
    private String phoneCustomer;
    private float price; // ราคาติดตั้ง
    private String date; //วันที่
    private String time; //เวลา
    private String statusOrder; //สถานะ order
    private String liable; //พนักงานที่รับผิดชอบ
    private ArrayList<Workorder> workbook = new ArrayList<>();

    public Workorder(){}

    // Constructor from create workorder
    public Workorder(String onOrder, String nameCustomer, String addressCustomer, String phoneCustomer,
                     float price, String date, String time, String statusOrder) {
        this.onOrder = onOrder;
        this.nameCustomer = nameCustomer;
        this.addressCustomer = addressCustomer;
        this.phoneCustomer = phoneCustomer;
        this.price = price;
        this.date = date;
        this.time = time;
        this.statusOrder = statusOrder;
        this.liable = liable; // พนักงานผู้รับผิดชอบงาน
    }


    // Constructor from Database
    public Workorder(String onOrder, String nameCustomer, String addressCustomer, String phoneCustomer,
                     float price, String date, String time, String statusOrder, String liable) {
        this.onOrder = onOrder;
        this.nameCustomer = nameCustomer;
        this.addressCustomer = addressCustomer;
        this.phoneCustomer = phoneCustomer;
        this.price = price;
        this.date = date;
        this.time = time;
        this.statusOrder = statusOrder;
        this.liable = liable; // พนักงานผู้รับผิดชอบงาน
    }

    public Workorder(String nameCustomer, String addressCustomer, String phoneCustomer, float price, String time, String statusOrder, String liable) {
        this.nameCustomer = nameCustomer;
        this.addressCustomer = addressCustomer;
        this.phoneCustomer = phoneCustomer;
        this.price = price;
        this.time = time;
        this.statusOrder = statusOrder;
        this.liable = liable;
    }

    public int getLengthArrayList(){   // get ความยาว arraylist
        return workbook.size();
    }

    public void addWorkOrderToList(Workorder order){
        workbook.add(order);
    }
    public String getOnOrder() {
        return onOrder;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public float getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public String getLiable() {
        return liable;
    }

    public ArrayList<Workorder> getWorkbook() {
        return workbook;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public void setLiable(String liable) {
        this.liable = liable;
    } // ระบุพนักงานรับผิดชอบ

    @Override
    public String toString() {
        return "Workorder{" +
                "onOrder='" + onOrder + '\'' +
                ", nameCustomer='" + nameCustomer + '\'' +
                ", addressCustomer='" + addressCustomer + '\'' +
                ", phoneCustomer='" + phoneCustomer + '\'' +
                ", price=" + price +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", statusOrder='" + statusOrder + '\'' +
                ", liable='" + liable + '\'' +
                '}';
    }
}
