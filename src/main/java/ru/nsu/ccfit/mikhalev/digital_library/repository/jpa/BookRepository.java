package ru.nsu.ccfit.mikhalev.digital_library.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.Book;

import java.awt.print.Pageable;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByTitle(String title);
}