package ru.nsu.ccfit.mikhalev.digital_library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.BookDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.Book;
import ru.nsu.ccfit.mikhalev.digital_library.model.exception.BookNotFoundException;
import ru.nsu.ccfit.mikhalev.digital_library.repository.jpa.BookRepository;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto getBookInfo(String title) throws BookNotFoundException {
        Book book = bookRepository.findBookByTitle(title);

        if(book.equals(null))
            throw new BookNotFoundException("api.digital-library.book-info.by-name.api.response.404" + title);

        return MapperBook.;
    }

}
