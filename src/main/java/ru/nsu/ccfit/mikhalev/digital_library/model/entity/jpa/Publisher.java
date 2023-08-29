package ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Table
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "foundation_year", nullable = false)
    private Short foundationYear;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Book> book;
}
