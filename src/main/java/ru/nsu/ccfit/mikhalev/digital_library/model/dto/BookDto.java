package ru.nsu.ccfit.mikhalev.digital_library.model.dto;

import jakarta.validation.constraints.*;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextValidation;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {

    @Size(min = ContextValidation.MIN_SIZE_WORD,
          max = ContextValidation.MAX_SIZE_WORD)
    @NotEmpty(message = "{validation.digital-library.title.not-empty}")
    private String title;

    @Min(ContextValidation.MIN_SIZE_YEAR)
    @Max(ContextValidation.CURRENT_YEAR)
    @NotEmpty(message = "{validation.digital-library.year.not-empty}")
    @Past(message = "{validation.digital-library.past-date}")
    private Short year;

    @NotNull(message = "{validation.digital-library.author.not-empty}")
    private List<AuthorDto> authors;

    @Size(min = ContextValidation.MIN_SIZE_WORD,
          max = ContextValidation.MAX_SIZE_WORD)
    @NotEmpty(message = "{validation.digital-library.publisher.not-empty}")
    private String publisher;

    @Size(min = ContextValidation.MIN_SIZE_WORD)
    @NotEmpty(message = "{validation.digital-library.description.not-empty}")
    private String description;
}
