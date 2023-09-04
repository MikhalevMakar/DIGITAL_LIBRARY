package ru.nsu.ccfit.mikhalev.digital_library.model.exception.author_exception;

public class AuthorAlreadyException extends RuntimeException {
    public AuthorAlreadyException(String message) {
        super("api.digital-library.book-info.by-name.response-already-database" + message);
    }
}