package ru.nsu.ccfit.mikhalev.digital_library.service;

import ru.nsu.ccfit.mikhalev.digital_library.model.dto.AuthorDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.BookDto;

import java.util.List;

public class Mock {
    public static BookDto getBookInfo(String title){
        return new BookDto ();
    }

    public static boolean addBook(BookDto bookInfoDto){
        return true;
    }

    public static List<BookDto> listBookFromPage(Integer numberPage) {
        return null;
    }

    public static AuthorDto getAuthorInfo(String title){
        return new AuthorDto();
    }

    public static boolean editBook(BookDto bookInfoDto) {
        return true;
    }

    public static void deleteBook(String title) {

    }

    public static List<AuthorDto> listAuthorFromPage(Integer numberPage) {
        return null;
    }

    public static void deleteAuthor(String title) {

    }

    public static void editAuthor(AuthorDto authorDto) {

    }
}

