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
    private Integer year;

    @Column(nullable = false)
    private String description;

    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    private Set<Author> authors;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="publisher_id", nullable = false)
    private Publisher publisher;
}
