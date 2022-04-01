package com.andreev.writers;

import com.andreev.properties.Properties;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class WriterVO {

    public void writeVO(Properties properties) throws IOException {

        final File fileSampleVO = new File(properties.getPathInOborot());
        final File fileKM = new File(properties.getFileKMPath() + "\\" +properties.getFileName());
        StringBuilder temp = new StringBuilder(70);
        String[] str = properties.getFileName().split("o");
        StringBuilder path = new StringBuilder(properties.getPathToWriteFile())
                .append(str[0])
                .append("Ввод_в_оборот_")
                .append(getDateFileName())
                .append(".csv");
        FileWriter writer = new FileWriter(String.valueOf(path), false);
        System.out.println(fileKM);
        System.out.println("Путь к файлу: " + properties.getFileKMPath() + "\nИмя файла: " + properties.getFileName());

        try{
            Scanner scannerSample = new Scanner(fileSampleVO);
            for (int i = 0; i < 4; i++){
                String line = scannerSample.nextLine();
                writer.write(line.replace("Inn", properties.getInn()).replace("Date", properties.getDateOfManufacture()) + "\n");
            }

            scannerSample.close();
            Scanner scannerKM = new Scanner(fileKM);
            int count = 0;
            while(scannerKM.hasNextLine()) {
                String line = scannerKM.nextLine();
                if (scannerKM.hasNextLine()) {
                    writer.write(String.valueOf(temp.append("\"")
                            .append(line.substring(0, 31).replace("\"", "\"\""))
                            .append("\"")
                            .append(",,")
                            .append(properties.getDateOfManufacture())
                            .append(",")
                            .append(properties.getCodeTNVD())
                            .append(",,,,,")
                            .append("\n")
                    ));
                    count ++;
                    temp.delete(0, temp.length());
                } else {
                    writer.write(String.valueOf(temp.append("\"")
                            .append(line.substring(0, 31).replace("\"", "\"\""))
                            .append("\"")
                            .append(",,")
                            .append(properties.getDateOfManufacture())
                            .append(",")
                            .append(properties.getCodeTNVD())
                            .append(",,,,,")
                    ));
                    writer.flush();
                    writer.close();
                    System.out.println("обработано: " + ++count + " кодов маркировки");

                }
            }
            scannerKM.close();
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
        }

    }

    private String getDateFileName (){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return format.format(date);
    }

}

