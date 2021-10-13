package shop.models;

import java.util.ArrayList;

public class Workorder {
    private String onOrder;  //เลขที่ใบงาน
    private String nameCustomer;
    private String surnameCustomer;
    private String addressCustomer;
    private String datetime; //วัน-เวลา
    private String statusOrder; //สถานะ order
    private String liable; //พนักงานที่รับผิดชอบ
    private ArrayList<Workorder> workbook = new ArrayList<>();

    public Workorder(String onOrder, String nameCustomer, String surnameCustomer,
                     String addressCustomer, String datetime, String statusOrder, String liable) {
        this.onOrder = onOrder;
        this.nameCustomer = nameCustomer;
        this.surnameCustomer = surnameCustomer;
        this.addressCustomer = addressCustomer;
        this.datetime = datetime;
        this.statusOrder = statusOrder;
        this.liable = "-"; // พนักงานผู้รับผิดชอบงาน
    }

    public ArrayList<Workorder> getWorkbook() {
        return workbook;
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

    public String getSurnameCustomer() {
        return surnameCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public String getLiable() {
        return liable;
    }

    public void setOnOrder(String onOrder) {
        this.onOrder = onOrder;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public void setSurnameCustomer(String surnameCustomer) {
        this.surnameCustomer = surnameCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public void setLiable(String liable) {
        this.liable = liable;
    }

    public void setWorkbook(ArrayList<Workorder> workbook) {
        this.workbook = workbook;
    }

    @Override
    public String toString() {
        return "Workorder{" +
                "onOrder='" + onOrder + '\'' +
                ", nameCustomer='" + nameCustomer + '\'' +
                ", surnameCustomer='" + surnameCustomer + '\'' +
                ", addressCustomer='" + addressCustomer + '\'' +
                ", datetime='" + datetime + '\'' +
                ", statusOrder='" + statusOrder + '\'' +
                ", liable='" + liable + '\'' +
                '}';
    }
}
