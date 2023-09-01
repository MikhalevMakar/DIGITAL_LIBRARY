package ru.nsu.ccfit.mikhalev.digital_library.service;

import ru.nsu.ccfit.mikhalev.digital_library.model.dto.BookDto;

public interface BookService {
    void add(BookDto bookDto);

    BookDto getBookInfoByTitle(String title);
}
