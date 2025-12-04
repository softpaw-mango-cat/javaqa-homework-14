package ru.netology.exceptions;

public class AlreadyRegisteredException extends RuntimeException {
    public AlreadyRegisteredException(String s) {
        super(s);
    }
}
