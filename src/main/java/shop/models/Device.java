package shop.models;

import java.util.ArrayList;

public class Device {

    private String idDevice;
    private String nameDevice;
    private int quantity;
    private ArrayList<Device> deviceList = new ArrayList<>();;

    public Device (){
//        deviceList = new ArrayList<>();
    }

    public Device(String idDevice, String nameDevice, int quantity) {
        this.idDevice = idDevice;
        this.nameDevice = nameDevice;
        this.quantity = quantity;
    }


    public ArrayList<Device> getDeviceList() {  // Get data all device
        return deviceList;
    }

    public void addDeviceToStock(Device device){  // add device to list
        deviceList.add(device);
    }

    public void decreaseDevice(int num){   // ลดจำนวนอุปกรณ์ใน Stock
        this.quantity-=num;
    }

    public void increaseDevice(int num){  // เพิ่มอุปกรณ์ใน Stock
        this.quantity+=num;
    }

    public String getIdDevice() {
        return idDevice;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Device{" +
                "idDevice='" + idDevice + '\'' +
                ", nameDevice='" + nameDevice + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
