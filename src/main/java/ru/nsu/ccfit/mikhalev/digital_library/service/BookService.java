package ru.nsu.ccfit.mikhalev.digital_library.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.BookDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.*;
import ru.nsu.ccfit.mikhalev.digital_library.model.exception.*;
import ru.nsu.ccfit.mikhalev.digital_library.model.mapper.MapperBook;
import ru.nsu.ccfit.mikhalev.digital_library.repository.jpa.*;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextValidation;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookService implements ServiceCRUD<BookDto> {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public BookDto getInfoByTitle(String title) throws BookNotFoundException {
        log.info("find book by title" + title);
        Book book = bookRepository.findBookByTitle(title)
            .orElseThrow(() -> new BookNotFoundException(title));
        return MapperBook.mapperToDto(book);
    }

    private void addLinkPublisher(String publisherName, Book book) {
        log.info("book add link publisher");
        book.setPublisher(publisherRepository.findByTitle(publisherName)
                .orElse(new Publisher(publisherName)));
    }

    private void addLinkAuthors(List<String> authorTitle, Book book) {
        log.info("book add link authors");
        book.setAuthors(authorTitle.stream()
            .map(authorName -> {
                Optional<Author> author = authorRepository.findAuthorByName(authorName);
                return author.orElseGet(() -> new Author(authorName));
            })
            .collect(Collectors.toSet()));
    }

    private<T  extends RuntimeException> Book findBook(String title, T bookException) {
        return bookRepository.findBookByTitle(title)
            .orElseThrow(() -> bookException);
    }

    @Override
    @Transactional
    public void add(BookDto bookDto) {
        log.info("find book in repository");
        bookRepository.findBookByTitle(bookDto.getTitle()).ifPresent(book -> {
            throw new BookAlreadyExistsException(bookDto.getTitle());
        });

        log.info("book add dependence set<Author>");

        final Book newBook = MapperBook.mapperToEntity(bookDto);

        addLinkAuthors(bookDto.getAuthors(), newBook);
        addLinkPublisher(bookDto.getPublisher(), newBook);
        bookRepository.save(newBook);
    }

    @Override
    @Transactional
    public void edit(String oldTitle, BookDto bookDto) {
        Book book = findBook(oldTitle, new BookNotFoundException(oldTitle));

        log.info("setting entity");
        MapperBook.settingEntity(book, bookDto);

        addLinkPublisher(bookDto.getPublisher(), book);
        addLinkAuthors(bookDto.getAuthors(), book);
        bookRepository.save(book);
    }

    @Override
    public void delete(String title) {
        Book book = findBook(title, new BookNotFoundException(title));
        bookRepository.delete(book);
    }

    @Override
    public List<BookDto> getPage(Integer numberPage) {
        return bookRepository.findAll(PageRequest.of(numberPage,
                                                     ContextValidation.CURRENT_SIZE_PAGE))
            .stream().map(MapperBook::mapperToDto).toList();
    }
}