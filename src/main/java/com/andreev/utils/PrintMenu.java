package com.andreev.utils;

public class PrintMenu {


    public void printSettings(){
        System.out.println("1. Изменить пути файлов и код ТНВЭД\n2. Путь к кодам маркировки\n3. Дата производства\n4. Выбор фирмы\n" +
                "5. Дата вывода из оборота\n6. Номер первичного документа\n7. Дата первичного документа\n8. Дата вывода из оборота\n" +
                "9. Прайс лист\n10. Сохранить настройки\n0. Назад");
    }

    public void printMainMenu(){
        System.out.println("выберете действие:\n1. Настройка\n2. Заполнить документ\n0. Выход");
    }

    public void printUpBack(){
        System.out.println("1. Вверх\n0. Назад");
    }

    public void printDocumentType(){
        System.out.println("выберете вид документа:\n1. Ввод в оборот\n2. Вывод из оборота\n0. Выход");
    }
}
