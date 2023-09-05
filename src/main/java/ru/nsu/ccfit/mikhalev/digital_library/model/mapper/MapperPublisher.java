package ru.nsu.ccfit.mikhalev.digital_library.model.mapper;

import ru.nsu.ccfit.mikhalev.digital_library.model.dto.PublisherDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.Publisher;

public class MapperPublisher {

    public static void settingEntity(Publisher publisher, PublisherDto publisherDto) {
        publisher.setTitle(publisherDto.getTitle());
        publisher.setDescription(publisherDto.getDescription());
        publisher.setFoundationYear(publisherDto.getFoundationYear());

    }

    public static void settingDto(PublisherDto publisherDto, Publisher publisher) {
        publisherDto.setTitle(publisher.getTitle());
        publisherDto.setDescription(publisher.getDescription());
        publisherDto.setFoundationYear(publisher.getFoundationYear());
    }

    public static PublisherDto mapperToDto(Publisher publisher) {
        PublisherDto publisherDto = new PublisherDto();
        settingDto(publisherDto, publisher);
        return publisherDto;
    }

    public static Publisher mapperToEntity(PublisherDto publisherDto) {
        Publisher publisher = new Publisher();
        settingEntity(publisher, publisherDto);
        return publisher;
    }
}
