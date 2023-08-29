package ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Short year;

    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;

    @ManyToOne
    @JoinColumn(name="publisher_id", nullable = false)
    private Publisher publisher;
}
