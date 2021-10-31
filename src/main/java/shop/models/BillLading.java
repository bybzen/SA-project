package shop.models;

import java.util.ArrayList;

public class BillLading {

    String idBillLading; // เลขที่ใบเบิก
    String nameAndQuantityDevice;
//    int quantity;
    String date;
    String time;
    String pickName;
    String status;

    ArrayList<BillLading> billList = new ArrayList<>();

    public BillLading(){}

//    public BillLading(String idBillLading, String nameAndQuantityDevice, String date, String time, String pickName) {
//        this.idBillLading = idBillLading;
//        this.nameAndQuantityDevice = nameAndQuantityDevice;
////        this.quantity = quantity;
//        this.date = date;
//        this.time = time;
//        this.pickName = pickName;
//        this.status = "Wait allow";
//    }


    public BillLading(String idBillLading, String nameAndQuantityDevice, String date, String time, String pickName, String status) {
        this.idBillLading = idBillLading;
        this.nameAndQuantityDevice = nameAndQuantityDevice;
//        this.quantity = quantity;
        this.date = date;
        this.time = time;
        this.pickName = pickName;
        this.status = status;
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

    public void setNameAndQuantityDevice(String nameAndQuantityDevice) {
        this.nameAndQuantityDevice = nameAndQuantityDevice;
    }

//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }

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

    public String getNameAndQuantityDevice() {
        return nameAndQuantityDevice;
    }

//    public int getQuantity() {
//        return quantity;
//    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getPickName() {
        return pickName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<BillLading> getBillList() {
        return billList;
    }

    @Override
    public String toString() {
        return "BillLading{" +
                "idBillLading='" + idBillLading + '\'' +
                ", nameAndQuantityDevice='" + nameAndQuantityDevice + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", pickName='" + pickName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
