package ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table
@NoArgsConstructor
public class Publisher {

    public Publisher(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "foundation_year")
    private Short foundationYear;

    @Column
    private String description;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Book> book = new HashSet<>();
}
