package com.andreev.writers;

import com.andreev.properties.PathProperties;
import com.andreev.properties.Price;
import com.andreev.properties.Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WriterOutO implements WriterDoc{

    private final Utils utils = new Utils();

    public void writeDoc(Properties properties,
                         PathProperties pathProperties,
                         Price price) throws IOException {

    final File fileSampleOut = new File(pathProperties.getPathOutOborot());
    StringBuilder temp = new StringBuilder(50);
    String[] str = pathProperties.getFileKMPath().split("\\\\");
    StringBuilder path = new StringBuilder(pathProperties.getPathToWriteFileO())
            .append(str[6])
            .append(".")
            .append(str[5])
            .append(".")
            .append(str[4])
            .append("Вывод_из_оборота_")
            .append(".csv");
    ArrayList<String> listFile = utils.getAllFileName(pathProperties.getFileKMPath());
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
                pathProperties.setFileName(listFile.get(i));
                File fileKM = new File(pathProperties.getFileKMPath() + "\\" + pathProperties.getFileName());
                String priceStr = utils.parsFileNameForPrice(pathProperties, price);
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
                                    .append(priceStr)
                                    .append(",,,,")
                            ));
                            count++;
                            countAll +=count;
                        } else {
                            writer.write(String.valueOf(temp.append("\"")
                                    .append(line.substring(0, 31).replace("\"", "\"\""))
                                    .append("\"")
                                    .append(",")
                                    .append(priceStr)
                                    .append(",,,,")
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
        } catch(IOException e) {
        System.err.println(e.getMessage());
        }
    }
}
