package ru.nsu.ccfit.mikhalev.digital_library.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextValidation;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UsersDto {

    @NotEmpty
    @Size(min = ContextValidation.MIN_SIZE_NAME,
          max = ContextValidation.MAX_SIZE_NAME)
    private String username;

    @NotEmpty
    @Size(min = ContextValidation.MIN_SIZE_PASSWORD,
          max = ContextValidation.MAX_SIZE_PASSWORD)
    private String password;

    @NotEmpty
    private List<String> roles;
}
