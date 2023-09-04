package ru.nsu.ccfit.mikhalev.digital_library.model.exception.user_exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super("api.digital-library.user.by-name.response-already-database" + message);
    }
}
