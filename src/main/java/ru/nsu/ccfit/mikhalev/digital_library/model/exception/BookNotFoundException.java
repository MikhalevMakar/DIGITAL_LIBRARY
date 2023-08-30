package ru.nsu.ccfit.mikhalev.digital_library.model.exception;

import ru.nsu.ccfit.mikhalev.digital_library.repository.jpa.BookRepository;

import java.util.NoSuchElementException;

public class BookNotFoundException extends NoSuchElementException {
    public BookNotFoundException(String message) {
        super("api.digital-library.book-info.by-name.api.response.404" + message);
    }
}
