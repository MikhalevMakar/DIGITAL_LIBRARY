package ru.nsu.ccfit.mikhalev.digital_library.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.PublisherDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.Publisher;
import ru.nsu.ccfit.mikhalev.digital_library.model.exception.book_exception.BookAlreadyExistsException;
import ru.nsu.ccfit.mikhalev.digital_library.model.exception.publisher_exception.PublisherNotFoundException;
import ru.nsu.ccfit.mikhalev.digital_library.model.mapper.MapperPublisher;
import ru.nsu.ccfit.mikhalev.digital_library.repository.jpa.PublisherRepository;
import ru.nsu.ccfit.mikhalev.digital_library.service.ServiceCRUD;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextValidation;

import java.util.List;

@Slf4j
@Service
public class PublisherServiceImpl implements ServiceCRUD<PublisherDto> {

    @Autowired
    private PublisherRepository publisherRepository;

    private<T  extends RuntimeException> Publisher findPublisher(String title, T publisherException) {
        return publisherRepository.findByTitle(title)
            .orElseThrow(() -> publisherException);
    }

    @Override
    public PublisherDto getInfoByTitle(String title) {
        log.info("find publisher by title: " + title);
        Publisher publisher = findPublisher(title, new PublisherNotFoundException(title));
        return MapperPublisher.mapperToDto(publisher);
    }

    @Override
    public void add(PublisherDto publisherDto) {
        publisherRepository.findByTitle(publisherDto.getTitle())
            .ifPresent(book -> {
            throw new BookAlreadyExistsException(publisherDto.getTitle());
        });
    }

    @Override
    public void edit(String oldTitle, PublisherDto publisherDto) {
        log.info("find by name " + oldTitle);
        Publisher publisher = findPublisher(oldTitle, new PublisherNotFoundException(oldTitle));
        log.info("change param");
        MapperPublisher.settingEntity(publisher, publisherDto);
        publisherRepository.save(publisher);
    }

    @Override
    public void delete(String title) {
        log.info("find publisher " + title);
        Publisher publisher = findPublisher(title, new PublisherNotFoundException(title));
        publisherRepository.delete(publisher);
    }

    @Override
    public List<PublisherDto> getPage(Integer numberPage) {
        return publisherRepository.findAll(PageRequest.of(numberPage,
                                                          ContextValidation.CURRENT_SIZE_PAGE))
            .stream().map(MapperPublisher::mapperToDto).toList();
    }
}