package com.gmail.podkutin.dmitry.bookconverter.util.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super("Ошибка Валидации XML файла : "+ message);
    }
}