package ru.nsu.ccfit.mikhalev.digital_library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.AuthorDto;
import ru.nsu.ccfit.mikhalev.digital_library.service.ServiceCRUD;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextValidation;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@RequestMapping(value = "/digital_library", produces = APPLICATION_JSON_VALUE)
public class AuthorController {

    @Qualifier("authorServiceImpl")
    private final ServiceCRUD<AuthorDto> authorService;

    @Autowired
    public AuthorController(ServiceCRUD<AuthorDto> authorService){
        this.authorService = authorService;
    }

    @Operation(summary = "api.digital-library.author.info")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "api-digital-library.return-code.ok",
            description = "api-digital-library.return-code.ok.description")
    })
    @GetMapping("/author/{surname}")
    public ResponseEntity<AuthorDto> getInfo(@PathVariable
                                             @Size(min = ContextValidation.MIN_SIZE_WORD,
                                                   max = ContextValidation.MAX_SIZE_WORD) String surname) {
        log.info("request getInfo " + surname);
        return ResponseEntity.ok(authorService.getInfoByTitle(surname));
    }

    @GetMapping("/authors/{number_page}")
    public  ResponseEntity<List<AuthorDto>> getAuthorPage(@PathVariable(name = "number_page")
                                                          @Min(ContextValidation.MIN_SIZE_PAGES) Integer numberPage) {
        log.info("get authors by page" + numberPage);
        return ResponseEntity.ok(authorService.getPage(numberPage));
    }

    @PostMapping("/manager/author/add")
    public ResponseEntity<Void> add(@Valid @RequestBody AuthorDto authorDto) {
        log.info("add new author " + authorDto.getName());
        authorService.add(authorDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/manager/author/edit/{surname}")
    public ResponseEntity<Void> edit(@PathVariable(name = "surname") @Size(min = ContextValidation.MIN_SIZE_WORD,
                                                                           max = ContextValidation.MAX_SIZE_WORD) String oldSurname,
                                     @Valid @RequestBody AuthorDto authorDto) {
        log.info("edit author " + authorDto.getName());
        authorService.edit(oldSurname, authorDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/manager/author/{surname}")
    public ResponseEntity<Void> delete(@PathVariable @Size(min = ContextValidation.MIN_SIZE_WORD,
                                                           max = ContextValidation.MAX_SIZE_WORD) String surname) {
        log.info("delete author " + surname);
        authorService.delete(surname);
        return ResponseEntity.ok().build();
    }
}