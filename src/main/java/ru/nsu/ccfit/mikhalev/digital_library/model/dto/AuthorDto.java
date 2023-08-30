package ru.nsu.ccfit.mikhalev.digital_library.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextValidation;

import java.time.LocalDate;
import java.util.List;

@Data
public class AuthorDto {
    @Size(min = ContextValidation.MIN_SIZE_SEQ,
          max = ContextValidation.MAX_SIZE_SEQ)
    @NotEmpty(message = "{validation.digital-library.title.not-empty}")
    private String name;

    @Past(message = "{validation.digital-library.past-date}")
    private LocalDate birthDate;

    @Size(min = ContextValidation.MIN_SIZE_WORD)
    @NotEmpty(message = "{validation.digital-library.description.not-empty}")
    private String biography;

    private List<BookDto> books;
}
