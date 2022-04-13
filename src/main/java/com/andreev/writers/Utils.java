package com.andreev.writers;

import com.andreev.properties.PathProperties;
import com.andreev.properties.Price;
import com.andreev.properties.Properties;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Utils {

    public String parsFileNameForPrice(PathProperties pathProperties, Price price){
        String[] arr = pathProperties.getFileName().split("_");
        if (arr[3].startsWith("04603734326017") || arr[3].startsWith("00046037343260")){
            return price.getPrice19LV();
        } else if (arr[3].startsWith("04603734326062")){
            return price.getPrice19LF();
        } else if (arr[3].startsWith("04603734326031")){
            return price.getPrice6LV();
        } else if (arr[3].startsWith("04603734326048")){
            return price.getPrice05LV();
        }

        return "Wrong file name";
    }

    public ArrayList<String> getAllFileName(String path) {
        ArrayList <String> list = new ArrayList <String> ();
        File file = new File(path);
        String[] str = file.list();
        boolean add = false;
        for (String l: str) {
            add = list.add(l);
        }
        if (add) {
            return list;
        }
        return null;
    }

    public String getDateFileName (){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return format.format(date);
    }

    public void printCountCodes(int count, int countAll){
        System.out.println("обработано: " + count + " кодов маркировки, всего обработано: " + countAll);
    }


}
