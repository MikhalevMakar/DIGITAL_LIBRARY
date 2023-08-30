package ru.nsu.ccfit.mikhalev.digital_library.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.BookDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.*;
import ru.nsu.ccfit.mikhalev.digital_library.model.exception.*;
import ru.nsu.ccfit.mikhalev.digital_library.model.mapper.MapperBook;
import ru.nsu.ccfit.mikhalev.digital_library.repository.jpa.*;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextSpecialSymbols;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    public BookDto getBookInfo(String title) throws BookNotFoundException {
        Book book = bookRepository.findBookByTitle(title);

        if (book == null)
            throw new BookNotFoundException(title);

        return MapperBook.mapperToDto(book);
    }

    private void createEmptyAuthor(Book book, String authorName) {
        Author author = new Author(authorName.replace(ContextSpecialSymbols.SYMBOL_EMPTY,
                                                      ContextSpecialSymbols.SYMBOL_UNDERLINE));
        book.getAuthors().add(author);
    }

    @Transactional
    public void add(BookDto bookDto) {
        Book book = bookRepository.findBookByTitle(bookDto.getTitle());
        if (book != null) throw new BookAlreadyExistsException(bookDto.getTitle());

        final Book newBook = MapperBook.mapperToEntity(bookDto);
        bookDto.getAuthors().stream()
            .filter(author -> authorRepository.findAuthorByName(author) == null)
            .forEach(author-> createEmptyAuthor(newBook, author));

        if (publisherRepository.findByTitle(bookDto.getPublisher()) == null) {
            Publisher publisher = new Publisher(bookDto.getPublisher());
            newBook.setPublisher(publisher);
        }

        bookRepository.save(newBook);
    }
}