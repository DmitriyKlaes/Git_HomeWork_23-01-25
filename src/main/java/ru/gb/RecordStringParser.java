package ru.gb;

import java.util.*;

public class RecordStringParser {
    private String[] splitArrayFromUserString;
    private PersonDataValidator validator;

    public RecordStringParser(String stringFromUser) {
        this.splitArrayFromUserString = stringFromUser.split(" ");
        this.validator = new PersonDataValidator();
    }

    public boolean countCheck() {
        int exceptCountData = 6;
        return this.splitArrayFromUserString.length == exceptCountData;
    }

    public void parseString() {
        Map<String, String> mapForRecord = new HashMap<>();
        List<String> listNames = new LinkedList<>();

        for (String valueForCheck : this.splitArrayFromUserString) {
            if (validator.sexValidator().isValid(valueForCheck)) {
                if (mapForRecord.putIfAbsent("sex", valueForCheck) != null) {
                    throw new IllegalArgumentException("Введен лишний пол! Проверьте правильность ввода данных.");
                }
            }
            else if (validator.phoneValidator().isValid(valueForCheck)) {
                if (mapForRecord.putIfAbsent("telephone", valueForCheck) != null) {
                    throw new IllegalArgumentException("Введен лишний телефон! Проверьте правильность ввода данных.");
                }
            }
            else if (validator.dateValidator().isValid(valueForCheck)) {
                if (mapForRecord.putIfAbsent("birthday", valueForCheck) != null) {
                    throw new IllegalArgumentException("Введен лишний день рождения! Проверьте правильность ввода данных.");
                }
            } else {
                listNames.add(valueForCheck);
            }
        }
        if (listNames.size() != 3) {
            throw new IllegalArgumentException("Некорректный ввод данных: \"Пол\", \"Дата рождения или\", \"Телефон\"");
        }

    }

    private Map<String, String> mapAcceptedNames(LinkedList<String> listNames) {
        Map<String, String> resultMapNames = new HashMap<>();
        String[] arrayTitles = {"Фамилия", "Имя", "Отчество"};
        int count = 1;

        System.out.println("Подтвердите ввод ФИО:");
        for (String name : listNames) {
            System.out.printf("%d. %s: %s%n", count++, arrayTitles[count - 1], name);
        }
        System.out.print("\n1. Подтвердить\n2. Изменить\nВыбор: ");

        return resultMapNames;
    }

    private void changeNameTitles(LinkedList<String> listNames) {
        LinkedList<String> listNamesCopy = new LinkedList<>(List.copyOf(listNames));
        String title = "фамилию";
        int count = 1;
        int index = 0;
        int breakPoint = 1;
        while(breakPoint != 0) {
            System.out.println("Выберете " + title + ":");
            for (String name : listNamesCopy) {
                if (listNamesCopy.size() == 1) {
                    listNames.set(index++, listNamesCopy.get(0));
                    listNamesCopy.remove(0);
                    breakPoint = 0;
                }
                System.out.printf("%d. %s%n", count++, name);
            }
            int choice = OnlyCorrect.correctInt(1, count - 1);
            listNames.set(index++, listNamesCopy.get(choice - 1));
            listNamesCopy.remove(choice - 1);
            count = 1;
            title = "имя";
        }
    }

    public static void main(String[] args) {
        RecordStringParser test = new RecordStringParser("имя фамилия отчество 321321321 12.12.1955 f");

        LinkedList<String> listS = new LinkedList<>(List.of("имя", "фамилия", "отчество"));
        test.changeNameTitles(listS);
        System.out.println(listS);

    }
}
