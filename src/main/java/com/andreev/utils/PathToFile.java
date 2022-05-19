package com.andreev.utils;

import com.andreev.properties.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PathToFile {

    private final ReaderProperties readerProperties = new ReaderProperties();
    private WriteSettings writeSettings = new WriteSettings();
    private Properties properties;
    private PathProperties pathProperties;
    private Price price;
    private PrintMenu printMenu = new PrintMenu();
    private static final Utils utils = new Utils();
    private File files;

    public File walkFileSystem(Scanner scanner) throws IOException {
        readProps(readerProperties.readPropertiesFile());
        files = new File(pathProperties.getFileKMPath());
        int number;
        System.out.println("Навигатор по фаловой системе");
        System.out.println("-1. Markerovka");
        listDir(files);
        while (scanner.hasNextLine()) {
            number = utils.parsStringToInt(scanner.nextLine());
            if (number == 1) {
                files = new File(files.getParent());
                listDir(files);
            } else if (number == 0) {
                break;
            } else if (number == -1) {
                files = new File("R:\\PUBLIC\\Markerovka");
                listDir(files);
            } else {
                changDirDown(files, number);
            }
        }
        return files;
    }

    private void listDir(File tempFile) {
        File[] files1 = tempFile.listFiles();
        System.out.println(tempFile.getAbsolutePath());
        printMenu.printUpBack();
        for (int i = 0; i < files1.length; i++) {
            if (files1[i].isDirectory()) {
                System.out.println((i + 2) + " " + files1[i].getName() + " \t folder");
            }
        }
        System.out.println("+++++++++++++++++++++++++++++++++");
    }

    private void listDirAndFiles(File tempFile){
        File[] files1 = tempFile.listFiles();
        System.out.println(tempFile.getAbsolutePath());
        printMenu.printUpBack();
        for (int i = 0; i < files1.length; i++) {
            if (files1[i].isDirectory()) {
                System.out.println((i + 2) + " " + files1[i].getName() + " \t folder");
            } else {
                System.out.println((i + 2) + " " + files1[i].getName() + " \t file");
            }
        }
        System.out.println("+++++++++++++++++++++++++++++++++");
    }

    private void changDirDown(File tempFile, int number) {
        File[] files1 = tempFile.listFiles();
        for (int i = 0; i < files1.length; i++) {
            if (i == (number - 2)) {
                files = new File(files.getAbsolutePath() + "\\" + files1[i].getName());
            }
        }
        listDir(files);
    }

    private void readProps(ArrayList<Prop> props){
        properties = (Properties) props.get(0);
        pathProperties = (PathProperties) props.get(1);
        price = (Price) props.get(2);
    }
}
