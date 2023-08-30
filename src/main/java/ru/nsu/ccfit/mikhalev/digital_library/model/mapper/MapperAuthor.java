package ru.nsu.ccfit.mikhalev.digital_library.model.mapper;

import ru.nsu.ccfit.mikhalev.digital_library.model.dto.AuthorDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.Author;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextSpecialSymbols;

import java.util.stream.Collectors;

public class MapperAuthor {

    public static Author mapperToEntity(AuthorDto authorDto) {
        Author author = new Author();
        author.setName(authorDto.getName().replace(ContextSpecialSymbols.SYMBOL_UNDERLINE,
                                                   ContextSpecialSymbols.SYMBOL_EMPTY));
        author.setBirthDate(authorDto.getBirthDate());
        author.setBiography(authorDto.getBiography());
        return author;
    }

    public static AuthorDto mapperToDto(Author author) {
        AuthorDto authorDto = new AuthorDto();
        authorDto.setBiography(author.getBiography());
        authorDto.setName(author.getName().replace(ContextSpecialSymbols.SYMBOL_EMPTY,
                                                   ContextSpecialSymbols.SYMBOL_UNDERLINE));
        authorDto.setBooks(author.getBooks().stream()
                           .map(MapperBook::mapperToDto)
                           .toList());
        return authorDto;
    }
}
