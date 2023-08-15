package com.andreev;

import com.andreev.properties.*;
import com.andreev.utils.*;
import com.andreev.writers.WriterOutO;
import com.andreev.writers.WriterVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Properties properties;
    private static PathProperties pathProperties;
    private static Price price;
    private static CutTheFile cutTheFile= new CutTheFile();
    private static final ReaderProperties readerProperties = new ReaderProperties();
    private static final WriterVO writerVO = new WriterVO();
    private static final WriterOutO writerOut = new WriterOutO();
    private static PathToFile pathToFile = new PathToFile();
    private static WriteSettings writeSettings = new WriteSettings();
    private static final PrintMenu printMenu = new PrintMenu();
    private static final Utils utils = new Utils();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        readProps(readerProperties.readPropertiesFile());
        printMenu.printMainMenu();
        while (scanner.hasNextLine()) {
            int number = utils.parsStringToInt(scanner.nextLine());
            if (number == 1) {
                changeProperties();
            } else if (number == 2) {
                writerDocuments(scanner);
            } else if (number == 3){
                pathProperties.setFileKMPath("R:\\PUBLIC\\Markerovka\\FilesKM");
                writeSettings.writeSettings(properties, pathProperties, price);
                cutTheFile.cutTheFile(scanner, pathToFile.walkFileSystem(scanner));
            }else if (number == 0) {
                System.exit(1);
            } else {
                System.out.println("Введена не верная команда");
                printMenu.printDocumentType();

            }
        }
    }

    private static void writerDocuments(Scanner scanner) throws IOException {
        printMenu.printDocumentType();
        while (scanner.hasNextLine()) {
            int number = utils.parsStringToInt(scanner.nextLine());
            if (number == 1) {
                System.out.println("выберете действие:\n1. Ввести в оборот сегодняшним числом\n2. Ввести в оборот вчерашним числом\n3. Выбрать папку");
                number = utils.parsStringToInt(scanner.nextLine());
                if (number == 1){
                    properties.setDateOfManufacture(LocalDate.now().toString());
                    writeVODate("Gidro");
                    writeVODate("Oasis");
                    printMenu.printMainMenu();
                    return;
                } else if (number == 2){
                    System.out.println("вошли в 2");
                    properties.setDateOfManufacture(LocalDate.now().minusDays(1).toString());
                    writeVODate("Gidro");
                    writeVODate("Oasis");
                    printMenu.printMainMenu();
                    return;
                } else if (number == 3){
                    System.out.println("вошли в 3");
                    System.out.println("выберите папку");
                    pathProperties.setFileKMPath(pathToFile.walkFileSystem(scanner).getAbsolutePath());
                    String[] date = pathProperties.getFileKMPath().split("\\\\");
                    properties.setDateOfManufacture(date[date.length-3] + "-" + date[date.length-2] + "-" +date[date.length-1]);
                    System.out.println("Дата производства " + properties.getDateOfManufacture() + ". Изменить дату производства?\n0. Выход\n1. Да\n2. Нет");
                    int num = utils.parsStringToInt(scanner.nextLine());
                    if (num == 1){
                        System.out.println("введите даду производства в формате год-месяц-день");
                        properties.setDateOfManufacture(scanner.nextLine());
                    } else if (num == 0) {
                        printMenu.printMainMenu();
                        return;
                    }
//                    System.out.println("выбирете фирму\n1. Гидротехнология\n2. Оазис");
//                    num = utils.parsStringToInt(scanner.nextLine());
//                    if (num == 0){
//                        printMenu.printMainMenu();
//                        return;
//                    }
//                    setupInn(num);
                    writeSettings.writeSettings(properties, pathProperties, price);
                    readProps(readerProperties.readPropertiesFile());
                    writerVO.writeDoc(properties, pathProperties, price);
                    printMenu.printDocumentType();
                } else {
                    System.out.println("Введена не корректная команда");
                    printMenu.printMainMenu();
                    break;
                }

            } else if (number == 2) {
                System.out.println("Выбрана фирма: " + (properties.getInn().equals("7814075424") ? "Гидротехнология" : "Оазис"));
                System.out.println("Дата вывода из оборота: " + properties.getDataOut());
                System.out.println("Номер первичного документа: " + properties.getNumberDoc());
                System.out.println("Дата первичного документа: " + properties.getDataPerDoc());
                System.out.println("Изменить настройки?\n1. Да\n2. Нет\n0. Выход");
                number = utils.parsStringToInt(scanner.nextLine());
                if (number == 1){
                    System.out.println("Выбрана фирма: " + (properties.getInn().equals("7814075424") ? "Гидротехнология" : "Оазис"));
                    System.out.println("Изменить фирму?\n1. Да\n2. Нет\n0. Выход");
                    number = utils.parsStringToInt(scanner.nextLine());
                    if (number == 1){
                        System.out.println("1. Гидротехнология\n2. Оазис\n3. Выход");
                        number = utils.parsStringToInt(scanner.nextLine());
                        if (number == 1){
                            properties.setInn("7814075424");
                        } else if (number == 2){
                            properties.setInn("7814216234");
                        } else if (number == 0) {
                            return;
                        }
                    }
                    writeSettings.writeSettings(properties, pathProperties, price);
                    System.out.println("Дата вывода из оборота: " + properties.getDataOut());
                    System.out.println("Изменить дату вывода из оборота?\n1. Да\n2. Нет\n0. Выход");
                    number = utils.parsStringToInt(scanner.nextLine());
                    if (number == 1){
                        System.out.println("Введите дату вывода из оборота в формате гггг-мм-дд");
                        properties.setDataOut(scanner.nextLine());
                    } else if (number == 0){
                        return;
                    }
                    writeSettings.writeSettings(properties, pathProperties, price);
                    System.out.println("Номер первичного документа: " + properties.getNumberDoc());
                    System.out.println("Изменить номер документа?\n1. Да\n2. Нет\n0. Выход");
                    number = utils.parsStringToInt(scanner.nextLine());
                    if (number == 1){
                        System.out.println("Введите номер первичного документа");
                        properties.setNumberDoc(scanner.nextLine());
                    } else if (number == 0){
                        return;
                    }
                    writeSettings.writeSettings(properties, pathProperties, price);
                    System.out.println("Дата первичного документа: " + properties.getDataPerDoc());
                    System.out.println("Изменить дату первичного документа?\n1. Да\n2. Нет\n0. Выход");
                    number = utils.parsStringToInt(scanner.nextLine());
                    if (number == 1){
                        System.out.println("Введите дату первичного документа в формате гггг-мм-дд");
                        properties.setDataPerDoc(scanner.nextLine());
                    } else if (number == 0){
                        return;
                    }
                    writeSettings.writeSettings(properties, pathProperties, price);
                } else if (number == 0){
                    return;
                }
                System.out.println("выберите папку");
                pathProperties.setFileKMPath(pathToFile.walkFileSystem(scanner).getAbsolutePath());
                writeSettings.writeSettings(properties, pathProperties, price);
                readProps(readerProperties.readPropertiesFile());
                writerOut.writeDoc(properties, pathProperties, price);
            } else if (number == 0) {
                printMenu.printMainMenu();
                return;
            } else {
                System.out.println("Введена не верная команда");
            }
//            printMenu.printDocumentType();
        }
    }

    private static void writeVODate(String firm) throws IOException {
        pathProperties.setFileKMPath("R:\\PUBLIC\\Markerovka\\" + firm + "\\"
                + properties.getDateOfManufacture().replaceAll("-", "\\" + "\\"));
        System.out.println(pathProperties.getFileKMPath());
        writeSettings.writeSettings(properties, pathProperties, price);
        writerVO.writeDoc(properties, pathProperties, price);
        return;
    }

    private static void changeProperties() throws IOException {
        pathToFile = new PathToFile();
        printMenu.printSettings();
        while (scanner.hasNextLine()) {
            int number = utils.parsStringToInt(scanner.nextLine());
            if (number == 1) {
            } else if (number == 2) {
                pathProperties.setFileKMPath(pathToFile.walkFileSystem(scanner).getAbsolutePath());
                printMenu.printSettings();
            } else if (number == 3) {
                System.out.println("Введите дату производства товара в формате год-месяц-день");
                properties.setDateOfManufacture(scanner.nextLine());
                printMenu.printSettings();
            } else if (number == 4) {
                System.out.println("Выберите фирму:\n1. Гидротехнология\n2. Оазис\n0. Назад");
                number = utils.parsStringToInt(scanner.nextLine());
                setupInn(number);
                printMenu.printSettings();
            } else if (number == 5) {

                printMenu.printSettings();
            } else if (number == 6) {
                printMenu.printSettings();
            } else if (number == 7) {
                printMenu.printSettings();
            } else if (number == 8) {
                printMenu.printSettings();
            } else if (number == 9) {
                printMenu.printSettings();
            } else if (number == 10){
                writeSettings.writeSettings(properties, pathProperties, price);
                System.out.println("************************\nНастройки сохранены\n************************");
                printMenu.printSettings();
            } else if (number == 0) {
                printMenu.printMainMenu();
                break;
            } else {
                System.out.println("Введена не верная команда");
                printMenu.printSettings();
            }
        }
    }

    private static void readProps(ArrayList<Prop> props){
        properties = (Properties) props.get(0);
        pathProperties = (PathProperties) props.get(1);
        price = (Price) props.get(2);
    }

    private static void setupInn(int number){
        if (number == 1){
            properties.setInn("7814075424");
        } else if (number == 2){
            properties.setInn("7814216234");
        }
    }

}

