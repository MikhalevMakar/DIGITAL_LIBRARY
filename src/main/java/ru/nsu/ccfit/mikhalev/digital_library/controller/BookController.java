package ru.nsu.ccfit.mikhalev.digital_library.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.BookInfoDto;
import ru.nsu.ccfit.mikhalev.digital_library.service.Mock;
import org.springframework.http.ResponseEntity;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextValidation;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@Validated
@Tag(name = "api.digital-library.tag.name", description = "api.digital-library.tag.description")
@RequestMapping(value = "/digital_library", produces = APPLICATION_JSON_VALUE)
public class BookController {

    @Operation(summary = "api.digital-library.book.info")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "api-digital-library.return-code.ok",
                     description = "api-digital-library.return-code.ok.description")
    })
    @GetMapping("/book/{title}")
    public ResponseEntity<BookInfoDto> getInfo(@PathVariable
                                               @Size(min = ContextValidation.MIN_SIZE_WORD,
                                                     max = ContextValidation.MAX_SIZE_WORD) String title) {
        log.info("request getInfo " + title);
        return ResponseEntity.ok(Mock.getBookInfo(title));
    }

    @Operation(summary = "api.digital-library.book.add")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "api-digital-library.return-code.ok",
            description = "api-digital-library.return-code.ok.description")
    })
    @PostMapping("/book/add")
    public ResponseEntity<Void> add(@Valid @RequestBody BookInfoDto bookInfoDto) {
        log.info("add new book " + bookInfoDto.getTitle());
        Mock.addBook(bookInfoDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/book/edit")
    public ResponseEntity<Void> edit(@Valid @RequestBody BookInfoDto bookInfoDto) {
        log.info("add new book " + bookInfoDto.getTitle());
        Mock.editBook(bookInfoDto);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "api.digital-library.book.books-page")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "api-digital-library.return-code.ok",
            description = "api-digital-library.return-code.ok.description")
    })
    @GetMapping("books/{number_page}")
    public  ResponseEntity<List<BookInfoDto>> getBooksPage(@PathVariable(name = "number_page")
                                                           @Min(ContextValidation.MIN_SIZE_PAGES) Integer numberPage) {
        log.info("get books by page" + numberPage);
        return ResponseEntity.ok(Mock.listBookFromPage(numberPage));
    }
}
