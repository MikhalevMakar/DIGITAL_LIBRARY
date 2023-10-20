package ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class Format {
    @Id
    private String bookFormat;
}
