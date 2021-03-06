package shop.models;

import sun.security.timestamp.TSRequest;

import java.util.ArrayList;

public class BillLading {

    private String idBillLading; // เลขที่ใบเบิก
    private String nameAndQuantityDevice;
    private String date;
    private String time;
    private String pickName;
    private String status;
    private String note;


    ArrayList<BillLading> billList = new ArrayList<>();

    public BillLading(){}


    public BillLading(String idBillLading, String nameAndQuantityDevice, String date, String time, String pickName, String status) {
        this.idBillLading = idBillLading;
        this.nameAndQuantityDevice = nameAndQuantityDevice;
        this.date = date;
        this.time = time;
        this.pickName = pickName;
        this.status = status;
        this.note = "-";

    }

    public BillLading(String idBillLading, String nameAndQuantityDevice, String date, String time
                        , String pickName, String status, String note) {
        this.idBillLading = idBillLading;
        this.nameAndQuantityDevice = nameAndQuantityDevice;
        this.date = date;
        this.time = time;
        this.pickName = pickName;
        this.status = status;
        this.note = note;

    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
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
                ", note='" + note + '\'' +
                '}';
    }
}
