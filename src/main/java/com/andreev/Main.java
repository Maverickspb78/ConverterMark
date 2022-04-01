package com.andreev;

import com.andreev.properties.Properties;
import com.andreev.properties.ReaderProperties;
import com.andreev.writers.WriterOutO;
import com.andreev.writers.WriterVO;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final ReaderProperties readerProperties = new ReaderProperties();
    private static final WriterVO writerVO = new WriterVO();
    private static final WriterOutO writerOut = new WriterOutO();

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("выберете вид документа:\n1. Ввод в оборот\n2. Вывод из оборота\n0. Выход");
        while (scanner.hasNextInt()){
            int number = scanner.nextInt();
            Properties properties;
            if (number == 1){
                properties = readerProperties.readPropertiesFile();
                writerVO.writeVO(properties);
            } else if (number == 2){
                properties = readerProperties.readPropertiesFile();
                writerOut.writeOO(properties);
            } else if(number == 0){
                System.exit(1);
            } else {
                System.out.println("Введена не верная команда");
            }
            System.out.println("выберете вид документа:\n1. Ввод в оборот\n2. Вывод из оборота");
        }

    }
}

