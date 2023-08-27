package ru.nsu.ccfit.mikhalev.digital_library.model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextValidation;
import lombok.Data;

@Data
public class BookInfoDto {
    private static Integer v = 1;

    @Size(min = ContextValidation.MIN_SIZE_WORD,
          max = ContextValidation.MAX_SIZE_WORD)
    @NotEmpty(message = "{validation.book-info.title.not-empty}")
    private String title;

    @Min(ContextValidation.MIN_SIZE_YEAR)
    @Max(ContextValidation.CURRENT_YEAR)
    @NotEmpty(message = "{validation.book-info.year.not-empty}")
    private Short year;

    @Size(min = ContextValidation.MIN_SIZE_AUTHOR,
          max = ContextValidation.MAX_SIZE_WORD)
    @NotEmpty(message = "{validation.book-info.author.not-empty}")
    private String author;

    @Size(min = ContextValidation.MIN_SIZE_WORD,
          max = ContextValidation.MAX_SIZE_WORD)
    @NotEmpty(message = "{validation.book-info.publisher.not-empty}")
    private String publisher;

    @Size
    @NotEmpty(message = "{validation.book-info.description.not-empty}")
    private String description;
}
