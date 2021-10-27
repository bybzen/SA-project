package shop.models;

import java.util.ArrayList;

public class BillLading {

    String idBillLading; // เลขที่ใบเบิก
    String nameDevice;
    int quantity;
    String date;
    String time;
    String pickName;

    ArrayList<BillLading> billList = new ArrayList<>();

    public BillLading(){}


    public BillLading(String idBillLading, String nameDevice, int quantity, String date, String time, String pickName) {
        this.idBillLading = idBillLading;
        this.nameDevice = nameDevice;
        this.quantity = quantity;
        this.date = date;
        this.time = time;
        this.pickName = pickName;
    }

    public int getLengthArrayList(){   // get จำนวน bill of lading ทั้งหมด
        return billList.size();
    }

    public void addBillToList(BillLading bill){   // add bill to list
        billList.add(bill);
    }

    public void setIdBillLading(String idBillLading) {
        this.idBillLading = idBillLading;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPickName(String pickName) {
        this.pickName = pickName;
    }

    public void setBillList(ArrayList<BillLading> billList) {
        this.billList = billList;
    }

    public String getIdBillLading() {
        return idBillLading;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPickName() {
        return pickName;
    }

    public ArrayList<BillLading> getBillList() {
        return billList;
    }
}
