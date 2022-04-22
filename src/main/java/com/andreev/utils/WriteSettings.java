package com.andreev.utils;

import com.andreev.properties.PathProperties;
import com.andreev.properties.Price;
import com.andreev.properties.Properties;

import java.io.FileWriter;
import java.io.IOException;

public class WriteSettings {

    public void writeSettings (Properties properties,
                               PathProperties pathProperties,
                               Price price) throws IOException {

        StringBuilder writeBuilder = new StringBuilder(1100);
        FileWriter writer = new FileWriter("C:\\Users\\aait\\Desktop\\test\\property.txt", false);
        writer.write(String.valueOf(writeBuilder.append("+++ путь к папке с файлом кодов маркировки+++\n")
                .append("pathToFileKM ").append(pathProperties.getFileKMPath()).append("\n")
                .append("+++ имя файла с кодами маркировки+++\n")
                .append("fileName ").append(pathProperties.getFileName()).append("\n")
                .append("+++ путь куда сохранять файл ввод в оборот загрузки+++\n")
                .append("pathToWriteFile ").append(pathProperties.getPathToWriteFile()).append("\n")
                .append("+++ путь куда сохранять файл вывод из оборота загрузки+++\n")
                .append("pathWriteFileO ").append(pathProperties.getPathToWriteFileO()).append("\n")
                .append("+++ путь и имя файла шаблона  ввод в оборот+++\n")
                .append("pathInOborot ").append(pathProperties.getPathInOborot()).append("\n")
                .append("+++ путь и имя файла шаблона вывыо из оборота+++\n")
                .append("pathOutOborot ").append(pathProperties.getPathOutOborot()).append("\n")
                .append("+++ Дата производства продукции+++\n")
                .append("dateOfManufacture ").append(properties.getDateOfManufacture()).append("\n")
                .append("+++ Дата вывода из оборота+++\n")
                .append("dateOut ").append(properties.getDataOut()).append("\n")
                .append("+++ ИНН+++\n")
                .append("Inn ").append(properties.getInn()).append("\n")
                .append("+++ Код ТНВЭД+++\n")
                .append("codeTNVD ").append(properties.getCodeTNVD()).append("\n")
                .append("+++ Номер первичного документа+++\n")
                .append("numberDoc ").append(properties.getNumberDoc()).append("\n")
                .append("+++ Дата первичного документа+++\n")
                .append("dataPerDoc ").append(properties.getDataPerDoc()).append("\n")
                .append("+++ Дата вывода из оборота+++\n")
                .append("dateOut ").append(properties.getDataOut()).append("\n")
                .append("+++ Прайс+++\n")
                .append("19LV ").append(price.getPrice19LV()).append("\n")
                .append("19LF ").append(price.getPrice19LF()).append("\n")
                .append("6LV ").append(price.getPrice6LV()).append("\n")
                .append("0,5LV ").append(price.getPrice05LV()).append("\n")));
        writer.close();
    }
}
