package ru.nsu.ccfit.mikhalev.digital_library.model.exception.publisher_exception;

public class PublisherAlreadyExistsException extends RuntimeException {
    public PublisherAlreadyExistsException(String message) {
        super("api.digital-library.author.by-name.response-already-database" + message);
    }
}