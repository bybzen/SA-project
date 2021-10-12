package shop.models;

import java.util.HashMap;

public class Stock {

    private HashMap<String,Device> stock_device = new HashMap<String, Device>();

//    public Stock (){
//        stock_device = new HashMap<>();
//    }

    public Device getDataDevice(String id_personal) {  // เอาข้อมูลทั้งหมดของ Device แต่ละอัน
        return stock_device.get(id_personal);
    }

    public void addDevicetoStock (String id_personal, Device item){  // add Device to Stock
        stock_device.put(id_personal, item);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "stock_device = "+ stock_device +
                '}';
    }

//    public static void main(String[] args) {            // test add & get Device from Stock By HashMap
//
//        Stock stock_devices = new Stock();
//        Device i1 = new Device("001", "PVC", 1);
//        stock_devices.addDevicetoStock("001",i1);
//
////        Device tem1 = stock_devices.getStock_device("001");
////        System.out.println(tem1.toString());
////        System.out.println(stock_devices.toString());
//
//        Device i2 = new Device("002", "Air", 10);
//        stock_devices.addDevicetoStock("002",i2);
////        System.out.println(stock_devices.toString());
////        System.out.println(stock_devices.stock_device.keySet());
//
////        System.out.println(stock_devices.stock_device.keySet());
//
//        for (String keys : stock_devices.stock_device.keySet())
//        {
//            Device tem = stock_devices.getDataDevice(keys);
//            System.out.println("-------------------ก่อนอัพเดต-------------------");
//            System.out.println(tem);
//
//            if (tem.getId_device().equals("002")){
//                System.out.println("-------------------หลังอัพเดต-------------------");
//                tem.decreaseDevice(5);
//                System.out.println(tem.toString());
//            }
//
//            if (tem.getId_device().equals("001")){
//                tem.increaseDevice(35);
//                System.out.println("-------------------หลังอัพเดต-------------------");
//                System.out.println(tem.toString());
//        }
//        }
//
//    }
}
