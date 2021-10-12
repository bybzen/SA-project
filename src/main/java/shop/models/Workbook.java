package shop.models;

import java.util.HashMap;

public class Workbook {

    private HashMap<String, Workorder> workbook = new HashMap<>();


    public Workorder getDataWorkorder(String on_order) {  // เอาข้อมูลทั้งหมดของ Workorder แต่ละอัน
        return workbook.get(on_order);
    }

     @Override
        public String toString() {
            return "Workbook{" +
                    "workbook=" + workbook +
                    '}';
        }
    }

