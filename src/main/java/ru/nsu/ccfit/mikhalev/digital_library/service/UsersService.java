package ru.nsu.ccfit.mikhalev.digital_library.service;

import ru.nsu.ccfit.mikhalev.digital_library.model.dto.UsersDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.UsernameDto;

import java.util.List;

public interface UsersService {
    List<UsernameDto> list();

    UsersDto findByUsername(String username);

    UsernameDto create(UsersDto userDto);

    void delete(String username);
}
