package ru.nsu.ccfit.mikhalev.digital_library.model.mapper;

import ru.nsu.ccfit.mikhalev.digital_library.model.dto.BookDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.*;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextSpecialSymbols;

public class MapperBook {

    private MapperBook(){
        throw new IllegalStateException("Utility class");
    }

    public static void settingEntity(Book book, BookDto bookDto) {
        book.setYear(bookDto.getYear());
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
    }

    public static void settingDto(BookDto bookDto, Book book) {
        bookDto.setAuthors(book.getAuthors().stream()
            .map(author -> author.getName())
            .toList());
        bookDto.setDescription(book.getDescription());
        bookDto.setYear(book.getYear());
        bookDto.setPublisher(book.getPublisher().getTitle());
    }

    public static BookDto mapperToDto(Book book) {
        BookDto bookDto = new BookDto();
        MapperBook.settingDto(bookDto, book);
        return bookDto;
    }

    public static Book mapperToEntity(BookDto bookDto) {
        Book book = new Book();
        MapperBook.settingEntity(book, bookDto);
        return book;
    }
}
