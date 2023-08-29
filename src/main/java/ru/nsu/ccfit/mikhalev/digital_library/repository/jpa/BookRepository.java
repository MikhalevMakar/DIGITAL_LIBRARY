package ru.nsu.ccfit.mikhalev.digital_library.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findBookByTitle(String title);


}
