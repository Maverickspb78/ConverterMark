package com.andreev.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class CutTheFile {
    private final Utils utils = new Utils();
    private final PrintMenu printMenu = new PrintMenu();

    public void cutTheFile(Scanner scanner, File file) throws IOException {
        File[] dir = file.listFiles();
        int number = 0;
        System.out.println("Выбирите Файл\n0. Выход");
        for (int i = 0; i < dir.length; i++) {
            if (!dir[i].isDirectory()) {
                String str = utils.markPars(dir[i]);
                System.out.println((i + 1) + " " + str);
            }
        }
            number = utils.parsStringToInt(scanner.nextLine());
            if (number == 0){
                printMenu.printMainMenu();
            } else if (number <= dir.length + 1){
                System.out.println("введите кол-во кодов маркеровки");
                cut(dir[number -1], utils.parsStringToInt(scanner.nextLine()));
                printMenu.printMainMenu();
            }
    }

    public void cut(File file, int numbKM) throws IOException {
        String[] data = utils.getDateFileName().split("-");
        String[] fileName = file.getName().split("_");
        StringBuilder nameOut = new StringBuilder(); //Имя файла в который пишут КМ
        StringBuilder nameIn = new StringBuilder(); //Имя файла после вырезки
        nameOut.append(utils.getCompany(file)).append("\\").append(data[0]).append("\\").append(data[1]).append("\\")
                .append(data[2]).append("\\");
        if (!Files.exists(Paths.get(String.valueOf(nameOut)))){
            new File(String.valueOf(nameOut)).mkdir();
        }
        System.out.println("********************************");
        if (fileName[0].startsWith("order")){
            System.out.println(fileName[5]);
            System.out.println(Integer.parseInt(fileName[5]) - numbKM);
            nameOut.append(file.getName().split("quantity_")[0]).append("quantity_").append(numbKM).append("_")
                    .append(fileName[fileName.length - 1]);
            nameIn.append("R:\\PUBLIC\\Markerovka\\FilesKM\\1_")
                    .append(file.getName(), file.getName().indexOf(""), file.getName().lastIndexOf("quantity_"))
                    .append("quantity_").append((Integer.parseInt(fileName[5]) - numbKM)).append("_")
                    .append(fileName[fileName.length - 1]);
        } else {
            try {
                nameOut.append(file.getName(), file.getName().indexOf("o"), file.getName().lastIndexOf("quantity_"))
                        .append("quantity_").append(numbKM).append("_").append(fileName[fileName.length - 1]);
                nameIn.append("R:\\PUBLIC\\Markerovka\\FilesKM\\").append((Integer.parseInt(fileName[0]) + 1))
                        .append(file.getName(), file.getName().indexOf("_"), file.getName().lastIndexOf("quantity_"))
                        .append("quantity_").append((Integer.parseInt(fileName[6]) - numbKM)).append("_")
                        .append(fileName[fileName.length - 1]);
            } catch (Exception e){
                System.err.println("не верное имя файла");
            }
        }
        System.out.println("nameOut: " + nameOut);
        System.out.println("nameIn: " + nameIn);
        System.out.println("********************************");
        if (utils.numberCodeMark(file) > numbKM) {
            FileWriter writerOut = new FileWriter(String.valueOf(nameOut));
            FileWriter writerIn = new FileWriter(String.valueOf(nameIn));
            Scanner scannerRead = new Scanner(file);
            int count = 1;
            while (scannerRead.hasNextLine()){
                String temp = scannerRead.nextLine();
                if (count > numbKM){
                    writerIn.write(temp);
                    if (scannerRead.hasNextLine()){
                        writerIn.write("\n");
                    }
                } else {
                    writerOut.write(temp);
                    if ((numbKM - count) > 0){
                        writerOut.write("\n");
                    }
                }
                count++;
            }
            writerOut.close();
            writerIn.close();
            scannerRead.close();
            Files.delete(Path.of(file.getAbsolutePath())); // удаление файла с которого вырезали
        } else {
            System.err.println("В файле осталось меньше КМ, чем нужно выделить");
        }
    }
}
