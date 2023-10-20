package ru.nsu.ccfit.mikhalev.digital_library.model.entity.mongo;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Accessors(chain = true)
@TypeAlias("Author")
@Data
@Document(collection = "authors")
public class AuthorData {
    @Id
    private String title;

    @Column(nullable = false)
    private String pathToImage;
}
