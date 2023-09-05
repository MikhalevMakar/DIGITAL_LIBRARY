package ru.nsu.ccfit.mikhalev.digital_library.model.exception.publisher_exception;

public class PublisherNotFoundException extends RuntimeException {
    public PublisherNotFoundException(String message) {
        super("api.digital-library.author.by-name.response-author-not-found" + message);
    }
}