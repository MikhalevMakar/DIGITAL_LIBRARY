package ru.nsu.ccfit.mikhalev.digital_library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.BookDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.exception.book_exception.BookAlreadyExistsException;
import ru.nsu.ccfit.mikhalev.digital_library.model.exception.book_exception.BookNotFoundException;
import ru.nsu.ccfit.mikhalev.digital_library.service.*;
import org.springframework.http.ResponseEntity;
import ru.nsu.ccfit.mikhalev.digital_library.util.*;

import java.util.*;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@Validated
@Tag(name = "api.digital-library.tag.name", description = "api.digital-library.tag.description")
@RequestMapping(value = "digital_library", produces = APPLICATION_JSON_VALUE)
public class BookController {
    @Autowired
    @Qualifier("bookServiceImpl")
    private ServiceCRUD<BookDto> bookService;

    @Operation(summary = "api.digital-library.book.info")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "api-digital-library.return-code.ok",
                     description = "api-digital-library.return-code.ok.description"
        ),
        @ApiResponse(responseCode = "api-digital-library.return-code.not-found",
                     description = "api.digital-library.book-info.by-name.response-book-not-found",
                     content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = BookNotFoundException.class))}
        )
    })
    @GetMapping("/book/{title}")
    public ResponseEntity<BookDto> getInfo(@PathVariable @Size(min = ContextValidation.MIN_SIZE_WORD,
                                                               max = ContextValidation.MAX_SIZE_WORD) String title) {
        log.info("get book " + title + " info");
        return ResponseEntity.ok(bookService.getInfoByTitle(title.replace(ContextSpecialSymbols.SYMBOL_UNDERLINE,
                                                                          ContextSpecialSymbols.SYMBOL_EMPTY)));
    }

    @Operation(summary = "api.digital-library.book.add")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "api-digital-library.return-code.ok",
                     description = "api-digital-library.return-code.ok.description"
        ),
        @ApiResponse(responseCode = "api-digital-library.return-code.not-found",
                     description = "api.digital-library.book-info.by-name.response-already-database",
                     content = {@Content(mediaType = APPLICATION_JSON_VALUE,
                     schema = @Schema(implementation = BookAlreadyExistsException.class))}
        )
    })
    @PostMapping("/manager/book/add")
    public ResponseEntity<Void> add(@Valid @RequestBody BookDto bookDto) {
        log.info("add new book " + bookDto);
        bookService.add(bookDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/manager/book/edit/{old_title}")
    public ResponseEntity<Void> edit(@PathVariable(name = "old_title") String oldTitle, @Valid @RequestBody BookDto bookDto) {
        log.info("edit book " + oldTitle);
        bookService.edit(oldTitle, bookDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "api.digital-library.book.books-page")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "api-digital-library.return-code.ok",
                     description = "api-digital-library.return-code.ok.description"),
        @ApiResponse(responseCode = "api-digital-library.return-code.not-found",
            description = "api.digital-library.book-info.by-name.response-book-not-found")
    })
    @GetMapping("/books/{number_page}")
    public List<BookDto> getBooksPage(@PathVariable(name = "number_page")
                                      @Min(ContextValidation.MIN_SIZE_PAGES) Integer numberPage) {
        log.info("get books from page " + numberPage);
        return bookService.getPage(numberPage);
    }

    @DeleteMapping("/manager/book/{title}")
    public ResponseEntity<Void> delete(@PathVariable @Size(min = ContextValidation.MIN_SIZE_WORD,
                                                           max = ContextValidation.MAX_SIZE_WORD) String title) {
        log.info("delete book " + title);
        bookService.delete(title.replace(ContextSpecialSymbols.SYMBOL_EMPTY,
                                         ContextSpecialSymbols.SYMBOL_UNDERLINE));
        return ResponseEntity.ok().build();
    }
}
