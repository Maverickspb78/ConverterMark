package com.andreev.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReaderProperties {

    private final Properties properties = new Properties();
    private final PathProperties pathProperties = new PathProperties();
    private final Price price = new Price();

    public ArrayList<Prop> readPropertiesFile() throws FileNotFoundException {
        String PROPERTIESPATH = "C:\\Users\\aait\\Desktop\\test\\property.txt";
        ArrayList<Prop> props = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new FileInputStream(PROPERTIESPATH));
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.startsWith("+++")||line.startsWith("/")) {
                    if (line.startsWith("pathToFileKM")) {
                        String[] str = line.split(" ");
                        pathProperties.setFileKMPath(str[1]);
                    } else if (line.startsWith("pathToWriteFile")) {
                        String[] str = line.split(" ");
                        pathProperties.setPathToWriteFile(str[1]);
                    } else if (line.startsWith("pathWriteFileO")) {
                        String[] str = line.split(" ");
                        pathProperties.setPathToWriteFileO(str[1]);
                    } else if (line.startsWith("fileName")) {
                        String[] str = line.split(" ");
                        pathProperties.setFileName(str[1]);
                    } else if (line.startsWith("pathInOborot")) {
                        String[] str = line.split(" ");
                        pathProperties.setPathInOborot(str[1]);
                    } else if (line.startsWith("pathOutOborot")) {
                        String[] str = line.split(" ");
                        pathProperties.setPathOutOborot(str[1]);
                    } else if (line.startsWith("dateOfManufacture")) {
                        String[] str = line.split(" ");
                        properties.setDateOfManufacture(str[1]);
                    } else if (line.startsWith("Inn")) {
                        String[] str = line.split(" ");
                        properties.setInn(str[1]);
                    } else if (line.startsWith("codeTNVD")) {
                        String[] str = line.split(" ");
                        properties.setCodeTNVD(str[1]);
                    } else if (line.startsWith("19LV")) {
                        String[] str = line.split(" ");
                        price.setPrice19LV(str[1]);
                    } else if (line.startsWith("19LF")) {
                        String[] str = line.split(" ");
                        price.setPrice19LF(str[1]);
                    } else if (line.startsWith("6LV")) {
                        String[] str = line.split(" ");
                        price.setPrice6LV(str[1]);
                    } else if (line.startsWith("0,5LV")) {
                        String[] str = line.split(" ");
                        price.setPrice05LV(str[1]);
                    } else if (line.startsWith("numberDoc")) {
                        String[] str = line.split(" ");
                        properties.setNumberDoc(str[1]);
                    } else if (line.startsWith("dataPerDoc")) {
                        String[] str = line.split(" ");
                        properties.setDataPerDoc(str[1]);
                    } else if (line.startsWith("dateOut")) {
                        String[] str = line.split(" ");
                        properties.setDataOut(str[1]);
                    }
                }
            }
            props.add(properties);
            props.add(pathProperties);
            props.add(price);
        }
        catch(IOException e) {
            System.err.println(e.getMessage());
        }
        return props;
    }

}
