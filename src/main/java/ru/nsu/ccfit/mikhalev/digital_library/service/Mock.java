package ru.nsu.ccfit.mikhalev.digital_library.service;

import ru.nsu.ccfit.mikhalev.digital_library.model.dto.AuthorDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.BookInfoDto;

import java.util.List;

public class Mock {
    public static BookInfoDto getBookInfo(String title){
        return new BookInfoDto();
    }

    public static boolean addBook(BookInfoDto bookInfoDto){
        return true;
    }

    public static List<BookInfoDto> listBookFromPage(Integer numberPage) {
        return null;
    }

    public static AuthorDto getAuthorInfo(String title){
        return new AuthorDto();
    }

    public static boolean editBook(BookInfoDto bookInfoDto) {
        return true;
    }
}

