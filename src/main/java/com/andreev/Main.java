package com.andreev;

import com.andreev.properties.*;
import com.andreev.utils.PathToFile;
import com.andreev.utils.PrintMenu;
import com.andreev.utils.Utils;
import com.andreev.utils.WriteSettings;
import com.andreev.writers.WriterOutO;
import com.andreev.writers.WriterVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Properties properties;
    private static PathProperties pathProperties;
    private static Price price;
    private static ArrayList<Prop> props;
    private static final ReaderProperties readerProperties = new ReaderProperties();
    private static final WriterVO writerVO = new WriterVO();
    private static final WriterOutO writerOut = new WriterOutO();
    private static PathToFile pathToFile;
    private static WriteSettings writeSettings = new WriteSettings();
    private static final PrintMenu printMenu = new PrintMenu();
    private static final Utils utils = new Utils();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        readProps(readerProperties.readPropertiesFile());
        System.out.println("выберете действие:\n1. Настройка\n2. Заполнить документ\n0. Выход");
        while (scanner.hasNextLine()) {
            int number = utils.parsStringToInt(scanner.nextLine());
            if (number == 1) {
                changeProperties();
            } else if (number == 2) {
                writerDocuments(scanner);
            }else if (number == 0) {
                System.exit(1);
            } else {
                System.out.println("Введена не верная команда");
                printMenu.printDocumentType();

            }
        }
    }

    private static void writerDocuments(Scanner scanner) throws IOException {

        System.out.println("выберете вид документа:\n1. Ввод в оборот\n2. Вывод из оборота\n0. Выход");
        while (scanner.hasNextLine()) {
            int number = utils.parsStringToInt(scanner.nextLine());
            if (number == 1) {
                pathToFile = new PathToFile();
                System.out.println("выберите папку");
                pathProperties.setFileKMPath(pathToFile.walkFileSystem(scanner).getAbsolutePath());
                String[] date = pathProperties.getFileKMPath().split("\\\\");
                properties.setDateOfManufacture(date[date.length-3] + "-" + date[date.length-2] + "-" +date[date.length-1]);
                System.out.println("Дата производства " + properties.getDateOfManufacture() + ". Изменить дату производства?\n1. Да\n2. Нет");
                int num = utils.parsStringToInt(scanner.nextLine());
                if (num == 1){
                    System.out.println("введите даду производства в формате год-месяц-день");
                    properties.setDateOfManufacture(scanner.nextLine());
                }
                System.out.println("выбирете фирму\n1. Гидротехнология\n2. Оазис");
                num = utils.parsStringToInt(scanner.nextLine());
                setupInn(num);
                writeSettings.writeSettings(properties, pathProperties, price);
                readProps(readerProperties.readPropertiesFile());
                writerVO.writeDoc(properties, pathProperties, price);
            } else if (number == 2) {
                readProps(readerProperties.readPropertiesFile());
                writerOut.writeDoc(properties, pathProperties, price);
            } else if (number == 0) {
                printMenu.printMainMenu();
                return;
            } else {
                System.out.println("Введена не верная команда");
            }
            printMenu.printDocumentType();
        }
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

