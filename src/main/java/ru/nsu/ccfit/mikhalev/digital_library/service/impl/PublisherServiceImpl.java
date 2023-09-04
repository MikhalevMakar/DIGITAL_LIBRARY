package ru.nsu.ccfit.mikhalev.digital_library.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.PublisherDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.Publisher;
import ru.nsu.ccfit.mikhalev.digital_library.repository.jpa.PublisherRepository;
import ru.nsu.ccfit.mikhalev.digital_library.service.ServiceCRUD;

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
        //Publisher publisher = publisherRepository.findByTitle(title);
        return null;
    }

    @Override
    public void add(PublisherDto publisherDto) {

    }

    @Override
    public void edit(String oldTitle, PublisherDto publisherDto) {

    }

    @Override
    public void delete(String title) {

    }

    @Override
    public List<PublisherDto> getPage(Integer numberPage) {
        return null;
    }
}
