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
//        Scanner scanner = new Scanner(System.in);
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
        /*
        * 1. Изменить системные настройки
        * (файлы шаблонов и хранения записаных фалов, код ТНВЭД)
        * 2. Путь к кодам маркировки
        * 3. Дата производства
        * 4. Выбор фирмы
        * 5. Дата вывода из оборота
        * 6. Номер первичного документа
        * 7. Дата первичного документа
        * 8. Дата вывода из оборота
        * 9. Прайс лист
        *
        * */

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
                if (number == 1){
                    properties.setInn("7814075424");
                } else if (number == 2){
                    properties.setInn("7814216234");
                }
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
}

