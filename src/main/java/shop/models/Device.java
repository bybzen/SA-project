package shop.models;

public class Device {

    private String id_device;
    private String name_device;
    private int quantity;

    public Device(String id_device, String name_device, int quantity) {
        this.id_device = id_device;
        this.name_device = name_device;
        this.quantity = quantity;
    }


    public void decreaseDevice(int num){   // ลดจำนวนอุปกรณ์ใน Stock
        this.quantity-=num;
    }

    public void increaseDevice(int num){  // เพิ่มอุปกรณ์ใน Stock
        this.quantity+=num;
    }

    public String getId_device() {
        return id_device;
    }

    public void setId_device(String id_device) {
        this.id_device = id_device;
    }

    public String getName_device() {
        return name_device;
    }

    public void setName_device(String name_device) {
        this.name_device = name_device;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id_device='" + id_device + '\'' +
                ", name_device='" + name_device + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
