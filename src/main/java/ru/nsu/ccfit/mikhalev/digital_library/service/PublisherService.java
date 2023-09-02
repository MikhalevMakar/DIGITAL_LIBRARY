package ru.nsu.ccfit.mikhalev.digital_library.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.PublisherDto;

import java.util.List;

@Slf4j
@Service
public class PublisherService implements ServiceCRUD<PublisherDto> {

    @Override
    public PublisherDto getInfoByTitle(String title) {
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
