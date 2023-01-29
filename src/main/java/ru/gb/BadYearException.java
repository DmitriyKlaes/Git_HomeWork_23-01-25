package ru.gb;

public class BadYearException extends Exception{
    public BadYearException() {
        super("Неверно введен год рождения");
    }
}
