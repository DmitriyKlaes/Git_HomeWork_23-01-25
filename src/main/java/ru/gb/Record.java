package ru.gb;

public class Record {
    String firstName;
    String lastName;
    String middleName;
    String birthday;
    int telephone;
    char sex;

    public Record(String firstName, String lastName, String middleName, String birthday, int telephone, char sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.telephone = telephone;
        this.sex = sex;
    }
}
