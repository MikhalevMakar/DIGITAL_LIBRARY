package ru.nsu.ccfit.mikhalev.digital_library.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.AuthorDto;
import ru.nsu.ccfit.mikhalev.digital_library.service.Mock;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextValidation;

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
    @GetMapping("/book/{title}")
    public ResponseEntity<AuthorDto> getInfo(@PathVariable
                                               @Size(min = ContextValidation.MIN_SIZE_WORD,
                                                     max = ContextValidation.MAX_SIZE_WORD) String title) {
        log.info("request getInfo " + title);
        return ResponseEntity.ok(Mock.getAuthorInfo(title));
    }
}
