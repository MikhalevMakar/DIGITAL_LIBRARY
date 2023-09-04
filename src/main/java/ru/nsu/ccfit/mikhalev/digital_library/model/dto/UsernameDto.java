package ru.nsu.ccfit.mikhalev.digital_library.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextValidation;

@Getter
@Setter
@AllArgsConstructor
public class UsernameDto {
    @NotEmpty
    @Size(min = ContextValidation.MIN_SIZE_NAME,
          max = ContextValidation.MAX_SIZE_NAME)
    String name;
}
