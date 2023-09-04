package ru.nsu.ccfit.mikhalev.digital_library.model.exception.book_exception;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String message) {
        super(message);
    }
}