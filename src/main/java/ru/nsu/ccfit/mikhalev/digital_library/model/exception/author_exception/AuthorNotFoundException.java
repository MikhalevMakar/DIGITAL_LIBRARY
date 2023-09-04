package ru.nsu.ccfit.mikhalev.digital_library.model.exception.author_exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(String message) {
        super("api.digital-library.book-info.by-name.response-author-not-found" + message);
    }
}