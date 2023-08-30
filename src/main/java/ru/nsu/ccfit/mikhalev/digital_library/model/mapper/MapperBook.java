package ru.nsu.ccfit.mikhalev.digital_library.model.mapper;

import ru.nsu.ccfit.mikhalev.digital_library.model.dto.BookDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.*;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextSpecialSymbols;

public class MapperBook {

    private MapperBook(){
        throw new IllegalStateException("Utility class");
    }

    public static BookDto mapperToDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle().replace(ContextSpecialSymbols.SYMBOL_UNDERLINE,
                                                 ContextSpecialSymbols.SYMBOL_EMPTY));
        bookDto.setAuthors(book.getAuthors().stream()
               .map(author -> author.getName().replace(ContextSpecialSymbols.SYMBOL_UNDERLINE,
                                                       ContextSpecialSymbols.SYMBOL_EMPTY))
               .toList());
        bookDto.setDescription(book.getDescription());
        bookDto.setYear(book.getYear());
        bookDto.setPublisher(book.getPublisher().getTitle());
        return bookDto;
    }

    public static Book mapperToEntity(BookDto bookDto) {
        Book book = new Book();
        book.setYear(bookDto.getYear());
        book.setTitle(bookDto.getTitle().replace(ContextSpecialSymbols.SYMBOL_EMPTY,
                                                 ContextSpecialSymbols.SYMBOL_UNDERLINE));
        book.setDescription(bookDto.getDescription());
        return book;
    }
}
