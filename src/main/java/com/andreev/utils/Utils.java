package com.andreev.utils;

import com.andreev.properties.PathProperties;
import com.andreev.properties.Price;
import com.andreev.properties.Properties;

import java.io.*;
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return format.format(date);
    }

    public void printCountCodes(int count, int countAll){
        System.out.println("обработано: " + count + " кодов маркировки, всего обработано: " + countAll);
    }

    public int parsStringToInt (String s){
        int number = -1;
        try {
            number = Integer.parseInt(s.trim());
        } catch (Exception e){
            System.err.println("введено не число");

        }
        return number;
    }

    public String markPars(File file) throws IOException {
        String code = null;
        String[] pars = file.getName().split("_");
        int i;
        if (pars[0].startsWith("order")){
            i = 3;
        } else {
            i = 4;
        }
        int lines = numberCodeMark(file);
        if (pars[i].equals("04603734326017")){
            code = "Гидро Вода 19 литров, " + lines + " кодов маркировки осталось";
        } else if (pars[i].equals("04603734326031")){
            code = "Гидро Вода 6 литров, " + lines + " кодов маркировки осталось";
        } else if (pars[i].equals("04603734326048")){
            code = "Гидро Вода 0.5 литров, " + lines + " кодов маркировки осталось";
        } else if (pars[i].equals("04603734326062")){
            code = "Гидро Фтор 19 литров, " + lines + " кодов маркировки осталось";
        } else if (pars[i].equals("00046037343260")){
            code = "Оазис Вода 19 литров, " + lines + " кодов маркировки осталось";
        }
        return code;
    }

    public String getCompany(File file){
        String[] pars = file.getName().split("_");
        if (pars[3].equals("00046037343260")){
            return "R:\\PUBLIC\\Markerovka\\Oasis";
        } else return "R:\\PUBLIC\\Markerovka\\Gidro";
    }

    public int numberCodeMark(File file) throws IOException {
        int number = 0;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while (reader.readLine() != null) number++;
        reader.close();
        return number;
    }
}
