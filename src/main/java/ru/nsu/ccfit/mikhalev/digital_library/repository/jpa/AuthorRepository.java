package ru.nsu.ccfit.mikhalev.digital_library.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.Author;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findAuthorByName(String nameAuthor);
}
