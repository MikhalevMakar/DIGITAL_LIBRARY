package ru.nsu.ccfit.mikhalev.digital_library.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    Publisher findByTitle(String title);
}