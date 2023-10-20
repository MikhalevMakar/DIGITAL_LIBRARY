package ru.nsu.ccfit.mikhalev.digital_library.model.entity.mongo;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

@Accessors(chain = true)
@TypeAlias("Data")
@Document(collection = "dataset")
public class BookData {
    @Id
    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private String pathToText;

    @Column(nullable = false)
    private String pathToImage;
}
