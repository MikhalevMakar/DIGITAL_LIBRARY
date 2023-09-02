package ru.nsu.ccfit.mikhalev.digital_library.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.AuthorDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.BookDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.Author;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.Book;
import ru.nsu.ccfit.mikhalev.digital_library.model.exception.AuthorAlreadyException;
import ru.nsu.ccfit.mikhalev.digital_library.model.exception.AuthorNotFoundException;
import ru.nsu.ccfit.mikhalev.digital_library.model.mapper.MapperAuthor;
import ru.nsu.ccfit.mikhalev.digital_library.model.mapper.MapperBook;
import ru.nsu.ccfit.mikhalev.digital_library.repository.jpa.AuthorRepository;
import ru.nsu.ccfit.mikhalev.digital_library.repository.jpa.BookRepository;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextValidation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthorService implements ServiceCRUD<AuthorDto> {
    @Autowired
    private  AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    private<T  extends RuntimeException> Author findAuthor(String title, T authorException) {
        return authorRepository.findAuthorByName(title)
            .orElseThrow(() -> authorException);
    }

    @Override
    public AuthorDto getInfoByTitle(String title){
        log.info("find author by title" + title);
        return MapperAuthor.mapperToDto(findAuthor(title, new AuthorAlreadyException(title)));
    }

    private void addLinkBooks(List<BookDto> books, Author author) {
        log.info("book add link books");

        author.setBooks(books.stream()
                .map(bookDto -> {
                    Optional<Book> newBook = bookRepository.findBookByTitle(bookDto.getTitle());
                    return newBook.orElseGet(() -> MapperBook.mapperToEntity(bookDto));
                })
                .collect(Collectors.toSet()));
    }

    @Override
    public void add(AuthorDto authorDto){
        log.info("find author in repository");
        authorRepository.findAuthorByName(authorDto.getName()).ifPresent(book -> {
            throw new AuthorAlreadyException(authorDto.getName());
        });
        Author author = MapperAuthor.mapperToEntity(authorDto);
        log.info("author add dependence set<Boook>");
        addLinkBooks(authorDto.getBooks(), author);
        authorRepository.save(author);
    }

    @Override
    public void edit(String oldTitle, AuthorDto authorDto){
        Author author = findAuthor(oldTitle, new AuthorNotFoundException(oldTitle));

        log.info("setting entity");
        MapperAuthor.settingEntity(author, authorDto);

        log.info("author edit dependence set<Book>");
        addLinkBooks(authorDto.getBooks(), author);
        authorRepository.save(author);
    }

    @Override
    public void delete(String title) {
        log.info("delete author by title" + title);
        Author author = findAuthor(title, new AuthorNotFoundException(title));
        authorRepository.delete(author);
    }

    @Override
    public List<AuthorDto> getPage(Integer numberPage){
        return authorRepository.findAll(PageRequest.of(numberPage,
                                                       ContextValidation.CURRENT_SIZE_PAGE))
            .stream().map(MapperAuthor::mapperToDto).toList();
    }
}
