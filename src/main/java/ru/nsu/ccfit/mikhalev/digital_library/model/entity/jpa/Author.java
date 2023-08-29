package ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name="author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @Column(name = "birth_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    @Column(name = "biography", nullable = false)
    private String biography;

    @ManyToMany
    @JoinTable(name = "author_book_info",
               joinColumns = @JoinColumn(name = "author_id", nullable = false),
               inverseJoinColumns = @JoinColumn(name = "book_info_id", nullable = false))
    private Set<Book> books;
}