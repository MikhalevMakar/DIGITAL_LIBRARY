package ru.nsu.ccfit.mikhalev.digital_library.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.BookDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.*;
import ru.nsu.ccfit.mikhalev.digital_library.model.exception.*;
import ru.nsu.ccfit.mikhalev.digital_library.model.mapper.MapperBook;
import ru.nsu.ccfit.mikhalev.digital_library.repository.jpa.*;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookServiceImp implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public BookDto getBookInfoByTitle(String title) throws BookNotFoundException {
        log.info("find book by title" + title);
        Book book = bookRepository.findBookByTitle(title)
            .orElseThrow(() -> new BookNotFoundException(title));
        return MapperBook.mapperToDto(book);
    }

    @Override
    @Transactional
    public void add(BookDto bookDto) {
        log.info("find book in repository");
        Optional<Book> existingBook = bookRepository.findBookByTitle(bookDto.getTitle());
        existingBook.ifPresent(book -> {
            throw new BookAlreadyExistsException(bookDto.getTitle());
        });

        log.info("book add dependence set<Author>");
        final Book newBook = MapperBook.mapperToEntity(bookDto);
        Set<Author> newAuthors = bookDto.getAuthors().stream()
            .filter(authorName -> authorRepository.findAuthorByName(authorName) == null)
            .map(Author::new)
            .collect(Collectors.toSet());

        newBook.getAuthors().addAll(newAuthors);

        log.info("book add publisher");
        if (publisherRepository.findByTitle(bookDto.getPublisher()) == null) {
            Publisher publisher = new Publisher(bookDto.getPublisher());
            newBook.setPublisher(publisher);
        }

        bookRepository.save(newBook);
    }
}