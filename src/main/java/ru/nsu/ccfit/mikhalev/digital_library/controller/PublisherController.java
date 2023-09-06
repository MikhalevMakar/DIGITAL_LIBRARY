package ru.nsu.ccfit.mikhalev.digital_library.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.PublisherDto;
import ru.nsu.ccfit.mikhalev.digital_library.service.ServiceCRUD;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextValidation;

import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
@RequestMapping(value = "/digital_library", produces = APPLICATION_JSON_VALUE)
public class PublisherController {

    @Qualifier("publisherServiceImpl")
    private final ServiceCRUD<PublisherDto> publisherService;

    @Autowired
    public PublisherController(ServiceCRUD<PublisherDto> publisherService){
        this.publisherService = publisherService;
    }

    @PostMapping("/manager/publisher/{title}/add")
    public ResponseEntity<Void> add(@Valid @RequestBody PublisherDto publisherDto) {
        log.info("add new publisher: " + publisherDto.getTitle());
        publisherService.add(publisherDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/manager/publisher_{title}/edit")
    public ResponseEntity<Void> edit(@PathVariable
                                     @Size(min = ContextValidation.MIN_SIZE_WORD,
                                           max = ContextValidation.MAX_SIZE_WORD) String title, @Valid @RequestBody PublisherDto publisherDto) {
        log.info("edit publisher: " + title);
        publisherService.edit(title, publisherDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("publishers/{number_page}")
    public  ResponseEntity<List<PublisherDto>> getPublisherByPage(@PathVariable(name = "number_page")
                                                                  @Min(ContextValidation.MIN_SIZE_PAGES) Integer numberPage) {
        log.info("get publishers by page" + numberPage);
        publisherService.getPage(numberPage);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/manager/publisher_{title}")
    public ResponseEntity<Void> delete(@PathVariable @Size(min = ContextValidation.MIN_SIZE_WORD,
                                                           max = ContextValidation.MAX_SIZE_WORD) String title) {
        log.info("delete publisher " + title);
        publisherService.delete(title);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/publisher_{title}")
    public ResponseEntity<PublisherDto> getInfo(@PathVariable
                                               @Size(min = ContextValidation.MIN_SIZE_WORD,
                                                   max = ContextValidation.MAX_SIZE_WORD) String title) {
        log.info("request publisher getInfo " + title);
        return ResponseEntity.ok(publisherService.getInfoByTitle(title));
    }
}
