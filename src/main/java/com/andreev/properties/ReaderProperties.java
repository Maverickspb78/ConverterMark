package com.andreev.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ReaderProperties {
    private final Properties properties = new Properties();

    public ReaderProperties() {
    }

    public Properties readPropertiesFile() throws FileNotFoundException {
        String PROPERTIESPATH = "C:\\Users\\aait\\Desktop\\test\\property.txt";

        try {
            Scanner sc = new Scanner(new FileInputStream(PROPERTIESPATH));

            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.startsWith("+++") && !line.startsWith("/")) {
                    String[] str;
                    if (line.startsWith("pathToFileKM")) {
                        str = line.split(" ");
                        this.properties.setFileKMPath(str[1]);
                    } else if (line.startsWith("pathToWriteFile")) {
                        str = line.split(" ");
                        this.properties.setPathToWriteFile(str[1]);
                    } else if (line.startsWith("pathWriteFileO")) {
                        str = line.split(" ");
                        this.properties.setPathToWriteFileO(str[1]);
                    } else if (line.startsWith("fileName")) {
                        str = line.split(" ");
                        this.properties.setFileName(str[1]);
                    } else if (line.startsWith("pathInOborot")) {
                        str = line.split(" ");
                        this.properties.setPathInOborot(str[1]);
                    } else if (line.startsWith("pathOutOborot")) {
                        str = line.split(" ");
                        this.properties.setPathOutOborot(str[1]);
                    } else if (line.startsWith("dateOfManufacture")) {
                        str = line.split(" ");
                        this.properties.setDateOfManufacture(str[1]);
                    } else if (line.startsWith("Inn")) {
                        str = line.split(" ");
                        this.properties.setInn(str[1]);
                    } else if (line.startsWith("codeTNVD")) {
                        str = line.split(" ");
                        this.properties.setCodeTNVD(str[1]);
                    } else if (line.startsWith("19LV")) {
                        str = line.split(" ");
                        this.properties.setPrice19LV(str[1]);
                    } else if (line.startsWith("19LF")) {
                        str = line.split(" ");
                        this.properties.setPrice19LF(str[1]);
                    } else if (line.startsWith("6LV")) {
                        str = line.split(" ");
                        this.properties.setPrice6LV(str[1]);
                    } else if (line.startsWith("0,5LV")) {
                        str = line.split(" ");
                        this.properties.setPrice05LV(str[1]);
                    } else if (line.startsWith("numberDoc")) {
                        str = line.split(" ");
                        this.properties.setNumberDoc(str[1]);
                    } else if (line.startsWith("dataPerDoc")) {
                        str = line.split(" ");
                        this.properties.setDataPerDoc(str[1]);
                    } else if (line.startsWith("dateOut")) {
                        str = line.split(" ");
                        this.properties.setDataOut(str[1]);
                    }
                }
            }
        } catch (IOException var5) {
            System.err.println(var5.getMessage());
        }

        return this.properties;
    }
}
