package ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="author")
public class Author {
    public Author() {}

    public Author(String name) {
        this.name = name;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    @Column
    private String biography;

    @ManyToMany
    @JoinTable(name = "author_book_info",
               joinColumns = @JoinColumn(name = "author_id", nullable = false),
               inverseJoinColumns = @JoinColumn(name = "book_info_id", nullable = false))
    private Set<Book> books = new HashSet<>();
}