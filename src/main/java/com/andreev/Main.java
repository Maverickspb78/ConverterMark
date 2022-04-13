package com.andreev;

import com.andreev.properties.*;
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

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        readProps(readerProperties.readPropertiesFile());
        System.out.println("выберете действие:\n1. Настройка\n2. Заполнить документ\n0. Выход");
        while (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            if (number == 1) {
                number = scanner.nextInt();
                if (number == 1) {

                }
            } else if (number == 2) {
                writerDocuments(scanner);
            }else if (number == 0) {
                System.exit(1);
            } else {
                System.out.println("Введена не верная команда");
            System.out.println("выберете вид документа:\n1. Ввод в оборот\n2. Вывод из оборота\n0. Выход");

            }
        }
    }

    private static void writerDocuments(Scanner scanner) throws IOException {
        System.out.println("выберете вид документа:\n1. Ввод в оборот\n2. Вывод из оборота\n0. Выход");
        while (scanner.hasNextInt()) {
            int number = scanner.nextInt();
            if (number == 1) {
                readProps(readerProperties.readPropertiesFile());
                writerVO.writeDoc(properties, pathProperties, price);
            } else if (number == 2) {
                readProps(readerProperties.readPropertiesFile());
                writerOut.writeDoc(properties, pathProperties, price);
            } else if (number == 0) {
                System.out.println("выберете действие:\n1. Настройка\n2. Заполнить документ\n0. Выход");
                return;
            } else {
                System.out.println("Введена не верная команда");
            }
            System.out.println("выберете вид документа:\n1. Ввод в оборот\n2. Вывод из оборота\n0. Выход");
        }
    }

    private static void changeProperties(Scanner scanner){
        /*
        * */

    }

    private static void readProps(ArrayList<Prop> props){
        properties = (Properties) props.get(0);
        pathProperties = (PathProperties) props.get(1);
        price = (Price) props.get(2);
    }
}

