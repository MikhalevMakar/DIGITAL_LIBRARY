package ru.nsu.ccfit.mikhalev.digital_library.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.*;
import ru.nsu.ccfit.mikhalev.digital_library.service.UsersService;
import ru.nsu.ccfit.mikhalev.digital_library.util.ContextValidation;

import java.util.List;

@RestController
@RequestMapping("/digital_library/admin")
@Slf4j
@Validated
public class AdminController {
    @Qualifier("userServiceImpl")
    private final UsersService usersService;

    @Autowired
    public AdminController(UsersService usersService){
        this.usersService = usersService;
    }

    @GetMapping("users")
    public ResponseEntity<List<UsernameDto>> users() {
        return ResponseEntity.ok(usersService.list());
    }

    @PostMapping("/create")
    public ResponseEntity<UsernameDto> createUser(@RequestBody @Valid UsersDto userDto) {
        log.info("create new user");
        return ResponseEntity.ok(usersService.create(userDto));
    }

    @DeleteMapping("delete/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable @Size(min = ContextValidation.MIN_SIZE_NAME,
                                                               max = ContextValidation.MAX_SIZE_NAME) String username) {
        usersService.delete(username);
        return ResponseEntity.ok().build();
    }
}
