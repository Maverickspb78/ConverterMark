package com.andreev.writers;

import com.andreev.properties.Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class WriterOutO {

    public void writeOO(Properties properties) throws IOException {

    final File fileSampleOut = new File(properties.getPathOutOborot());
    StringBuilder temp = new StringBuilder(50);
    String[] str = properties.getFileKMPath().split("\\\\");
    StringBuilder path = new StringBuilder(properties.getPathToWriteFileO())
            .append(str[6])
            .append(".")
            .append(str[5])
            .append(".")
            .append(str[4])
            .append("Вывод_из_оборота_")
            .append(".csv");
    ArrayList<String> listFile = getAllFileName(properties.getFileKMPath());
    FileWriter writer = new FileWriter(String.valueOf(path), false);
        try {
            Scanner scannerSample = new Scanner( new FileInputStream(fileSampleOut));
            for (int i = 0; i < 4; i++) {
                String line = scannerSample.nextLine();
                writer.write(line.replace("Inn", properties.getInn())
                        .replace("DataOut", properties.getDataOut())
                        .replace("number", properties.getNumberDoc())
                        .replace("DatePerDoc", properties.getDataPerDoc())  + "\n"
                );
            }
            scannerSample.close();
            int countAll = 0;
            for (int i = 0; i < listFile.size(); i++) {
                properties.setFileName(listFile.get(i));
                File fileKM = new File(properties.getFileKMPath() + "\\" + properties.getFileName());
                String price = parsFileNameForPrice(properties);
                Scanner scannerKM = new Scanner(fileKM);
                int count = 0;
                while (scannerKM.hasNextLine()) {
                    String line = scannerKM.nextLine();
                    if (scannerKM.hasNextLine()) {
                        writer.write(String.valueOf(temp.append("\"")
                                        .append(line.substring(0, 31).replace("\"", "\"\""))
                                        .append("\"")
                                        .append(",")
                                        .append(price)
                                        .append(",,,,")
                                        .append("\n")
                        ));
                        count++;
                        temp.delete(0, temp.length());

                    } else {
                        if (i + 1 == listFile.size()) {
                            writer.write(String.valueOf(temp.append("\"")
                                    .append(line.substring(0, 31).replace("\"", "\"\""))
                                    .append("\"")
                                    .append(",")
                                    .append(price)
                                    .append(",,,,")
                            ));
                            count++;
                            countAll +=count;
                        } else {
                            writer.write(String.valueOf(temp.append("\"")
                                    .append(line.substring(0, 31).replace("\"", "\"\""))
                                    .append("\"")
                                    .append(",")
                                    .append(price)
                                    .append(",,,,")
                                    .append("\n")
                            ));
                            count++;
                            countAll +=count;
                            temp.delete(0, temp.length());
                        }
                        System.out.println("обработано: " + count + " кодов маркировки, всего обработано: " + countAll);
//                        System.out.println("processed: " + count + " marking codes in the file, total codes processed: " + countAll);
                    }
                }
                scannerKM.close();
            }
            writer.flush();
            writer.close();
        }
        catch(
    IOException e) {
        System.err.println(e.getMessage());
    }
        }



    private String getDateFileName (){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return format.format(date);
    }

    public static ArrayList<String> getAllFileName(String path) {
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

    public String parsFileNameForPrice(Properties properties){
        String[] arr = properties.getFileName().split("\\.");
        if (arr[1].startsWith("19LV")){
            return properties.getPrice19LV();
        } else if (arr[1].startsWith("19LF")){
            return properties.getPrice19LF();
        } else if (arr[1].startsWith("6LV")){
            return properties.getPrice6LV();
        } else if (arr[1].startsWith("0,5LV")){
            return properties.getPrice05LV();
        }

        return "Wrong file name";
    }
}
