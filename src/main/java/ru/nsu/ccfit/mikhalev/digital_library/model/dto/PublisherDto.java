package ru.nsu.ccfit.mikhalev.digital_library.model.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextValidation;

@Data
public class PublisherDto {

    @Size(min = ContextValidation.MIN_SIZE_WORD,
          max = ContextValidation.MAX_SIZE_WORD)
    @NotEmpty(message = "{validation.digital-library.title.not-empty}")
    private String title;

    @Min(ContextValidation.MIN_SIZE_YEAR)
    @Max(ContextValidation.CURRENT_YEAR)
    @Past(message = "{validation.digital-library.past-date}")
    private Short foundationYear;

    @Size
    @NotEmpty(message = "{validation.digital-library.description.not-empty}")
    private String description;
}
