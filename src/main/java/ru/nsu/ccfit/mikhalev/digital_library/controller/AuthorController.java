package ru.nsu.ccfit.mikhalev.digital_library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.AuthorDto;
import ru.nsu.ccfit.mikhalev.digital_library.service.Mock;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextValidation;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@RequestMapping(value = "/digital_library", produces = APPLICATION_JSON_VALUE)
public class AuthorController {

    @Operation(summary = "api.digital-library.author.info")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "api-digital-library.return-code.ok",
            description = "api-digital-library.return-code.ok.description")
    })
    @GetMapping("/author_{surname}")
    public ResponseEntity<AuthorDto> getInfo(@PathVariable
                                               @Size(min = ContextValidation.MIN_SIZE_WORD,
                                                     max = ContextValidation.MAX_SIZE_WORD) String surname) {
        log.info("request getInfo " + surname);
        return ResponseEntity.ok(Mock.getAuthorInfo(surname));
    }

    @GetMapping("/authors/{number_page}")
    public  ResponseEntity<List<AuthorDto>> getBooksPage(@PathVariable(name = "number_page")
                                                         @Min(ContextValidation.MIN_SIZE_PAGES) Integer numberPage) {
        log.info("get authors by page" + numberPage);
        return ResponseEntity.ok(Mock.listAuthorFromPage(numberPage));
    }

    @PostMapping("/author_{surname}/add")
    public ResponseEntity<Void> add(@Valid @RequestBody AuthorDto authorDto) {
        log.info("add new author " + authorDto.getSurname());
        Mock.editAuthor(authorDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/author_{surname}/edit")
    public ResponseEntity<Void> edit(@Valid @RequestBody AuthorDto authorDto) {
        log.info("edit author " + authorDto.getSurname());
        Mock.editAuthor(authorDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/author_{surname}")
    public ResponseEntity<Void> delete(@PathVariable @Size(min = ContextValidation.MIN_SIZE_WORD,
                                                           max = ContextValidation.MAX_SIZE_WORD) String surname) {
        log.info("delete author " + surname);
        Mock.deleteAuthor(surname);
        return ResponseEntity.ok().build();
    }
}
