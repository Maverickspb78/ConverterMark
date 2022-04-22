package com.andreev.writers;

import com.andreev.properties.PathProperties;
import com.andreev.properties.Price;
import com.andreev.properties.Properties;
import com.andreev.utils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WriterVO implements WriterDoc{

    Utils utils = new Utils();

    public void writeDoc(Properties properties,
                         PathProperties pathProperties,
                         Price price) throws IOException {

        final File fileSampleVO = new File(pathProperties.getPathInOborot());
        final String org;
        if (properties.getInn().equals("7814075424")){
            org = "Гидро";
        } else {
            org = "Оазис";
        }
        StringBuilder temp = new StringBuilder(70);
        ArrayList<String> listFile = utils.getAllFileName(pathProperties.getFileKMPath());
        StringBuilder path = new StringBuilder(pathProperties.getPathToWriteFile())
                .append(properties.getDateOfManufacture())
                .append("_Ввод_в_оборот_")
                .append(org)
                .append(".csv");
        FileWriter writer = new FileWriter(String.valueOf(path), false);
        try {
            Scanner scannerSample = new Scanner(new FileInputStream(fileSampleVO));
            for (int i = 0; i < 4; i++) {
                String line = scannerSample.nextLine();
                writer.write(line.replace("Inn", properties.getInn()).replace("Date", properties.getDateOfManufacture()) + "\n");
            }
            scannerSample.close();
            int countAll = 0;
            for (int i = 0; i < listFile.size(); i++) {
                pathProperties.setFileName(listFile.get(i));
                System.out.println("Путь к файлу: " + pathProperties.getFileKMPath() + "\nИмя файла: " + pathProperties.getFileName());
                File fileKM = new File(pathProperties.getFileKMPath() + "\\" + pathProperties.getFileName());
                Scanner scannerKM = new Scanner(fileKM);
                int count = 0;
                while (scannerKM.hasNextLine()) {
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
                        count++;
                        temp.delete(0, temp.length());
                    } else {
                        if (i + 1 == listFile.size()) {
                            writer.write(String.valueOf(temp.append("\"")
                                    .append(line.substring(0, 31).replace("\"", "\"\""))
                                    .append("\"")
                                    .append(",,")
                                    .append(properties.getDateOfManufacture())
                                    .append(",")
                                    .append(properties.getCodeTNVD())
                                    .append(",,,,,")
                            ));
                            count++;
                            countAll +=count;
                        } else {
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
                            count++;
                            countAll +=count;
                            temp.delete(0, temp.length());
                        }
                        utils.printCountCodes(count, countAll);
                    }
                }
                scannerKM.close();
            }
            writer.flush();
            writer.close();
            System.out.println("============================================");
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

