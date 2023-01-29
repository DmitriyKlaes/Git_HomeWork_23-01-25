package ru.gb;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class PersonDataValidator {

    public Validator phoneValidator() {
        return string -> {
            try {
                Integer.parseInt(string);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        };
    }

    public Validator sexValidator() {
        return string -> {
            char sex = string.charAt(0);
            return (sex == 'f' || sex == 'm') && string.length() == 1;
        };
    }

    public Validator dateValidator() {
        return string -> {
            try {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.uuuu")
                        .withResolverStyle(ResolverStyle.STRICT);
                dateFormatter.parse(string);
            } catch (DateTimeParseException e) {
                return false;
            }
            return true;
        };
    }
}
