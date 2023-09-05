package ru.nsu.ccfit.mikhalev.digital_library.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.BookDto;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.PublisherDto;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextValidation;

import java.util.List;

//@RestController
@Slf4j
public class PublisherController {
//    @PostMapping("/manager/publisher/{title}/add")
//    public ResponseEntity<Void> add(@Valid @RequestBody PublisherDto publisherDto) {
//        log.info("add new publisher: " + publisherDto.getTitle());
//        //Mock.addBook(publisherDto);
//        return ResponseEntity.ok().build();
//    }
//
//    @PatchMapping("/manager/publisher_{title}/edit")
//    public ResponseEntity<Void> edit(@Valid @RequestBody PublisherDto publisherDto) {
//        log.info("edit publisher: " + publisherDto.getTitle());
//
//        return ResponseEntity.ok().build();
//    }

//    @GetMapping("publishers/{number_page}")
//    public  ResponseEntity<List<PublisherDto>> getPublisherByPage(@PathVariable(name = "number_page")
//                                                                  @Min(ContextValidation.MIN_SIZE_PAGES) Integer numberPage) {
//        log.info("get publishers by page" + numberPage);
//        return ResponseEntity.ok(null);
//    }
//
//    @DeleteMapping("/manager/publisher_{title}")
//    public ResponseEntity<Void> delete(@PathVariable @Size(min = ContextValidation.MIN_SIZE_WORD,
//                                                           max = ContextValidation.MAX_SIZE_WORD) String title) {
//        log.info("delete publisher " + title);
//        Mock.deleteBook(title);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/publisher_{title}")
//    public ResponseEntity<BookDto> getInfo(@PathVariable
//                                               @Size(min = ContextValidation.MIN_SIZE_WORD,
//                                                   max = ContextValidation.MAX_SIZE_WORD) String title) {
//        log.info("request publisher getInfo " + title);
//        return ResponseEntity.ok(Mock.getBookInfo(title));
//    }
}
