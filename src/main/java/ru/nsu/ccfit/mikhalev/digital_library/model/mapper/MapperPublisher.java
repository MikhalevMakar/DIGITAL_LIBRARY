package ru.nsu.ccfit.mikhalev.digital_library.model.mapper;

import ru.nsu.ccfit.mikhalev.digital_library.model.dto.PublisherDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.Publisher;

public class MapperPublisher {

    public static PublisherDto mapperToDto(Publisher publisher) {
        PublisherDto publisherDto = new PublisherDto();
        publisherDto.setTitle(publisher.getTitle());
        publisherDto.setDescription(publisher.getDescription());
        publisherDto.setFoundationYear(publisher.getFoundationYear());
        return publisherDto;
    }

    public static Publisher mapperToEntity(PublisherDto publisherDto) {
        Publisher publisher = new Publisher();
        publisher.setTitle(publisherDto.getTitle());
        publisher.setDescription(publisherDto.getDescription());
        publisher.setFoundationYear(publisherDto.getFoundationYear());
        return publisher;
    }
}
