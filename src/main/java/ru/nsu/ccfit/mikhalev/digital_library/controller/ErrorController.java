package ru.nsu.ccfit.mikhalev.digital_library.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.utils.PropertyResolverUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.nsu.ccfit.mikhalev.digital_library.model.exception.book_exception.BookAlreadyExistsException;
import ru.nsu.ccfit.mikhalev.digital_library.model.exception.book_exception.BookNotFoundException;

import java.time.LocalDateTime;
import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorController {
    private final PropertyResolverUtils propertyResolverUtils;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerException(Exception exception) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse(LocalDateTime.now(),
                                    propertyResolverUtils.resolve("api.digital-library.book-info.server.error", Locale.getDefault()),
                                    HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerBookNotFoundException(BookNotFoundException exception) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse(LocalDateTime.now(),
                propertyResolverUtils.resolve("api.digital-library.book-info.by-name.response-book-not-found", Locale.getDefault()),
                HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handlerBookAlreadyExistsException(BookAlreadyExistsException exception) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse(LocalDateTime.now(),
                propertyResolverUtils.resolve("api.digital-library.book-info.by-name.response-book-not-found", Locale.getDefault()),
                HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
    private record ErrorResponse(LocalDateTime dateTime, String message, int code) {

    }
}
