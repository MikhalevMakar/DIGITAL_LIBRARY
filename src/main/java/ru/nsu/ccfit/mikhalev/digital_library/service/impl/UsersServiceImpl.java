package ru.nsu.ccfit.mikhalev.digital_library.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.mikhalev.digital_library.model.dto.*;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.Users;
import ru.nsu.ccfit.mikhalev.digital_library.model.exception.user_exception.UserAlreadyExistsException;
import ru.nsu.ccfit.mikhalev.digital_library.repository.jpa.UsersRepository;
import ru.nsu.ccfit.mikhalev.digital_library.service.UsersService;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
public class UsersServiceImpl implements UsersService, UserDetailsService {

    private final UsersRepository usersRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository,
                            PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UsernameDto> list(){
        return usersRepository.findAll().stream()
            .map(user->new UsernameDto(user.getUsername()))
            .toList();
    }


    @Override
    public UsersDto findByUsername(String username){
        return usersRepository.findById(username)
            .map(user->new UsersDto (user.getUsername(), user.getPassword(), user.getRoles().stream().toList()))
            .orElseThrow(
                ()-> new UsernameNotFoundException("api.digital-library.user.by-name.response-not-found" + username)
            );
    }

    @Override
    public UsernameDto create(UsersDto userDto) {
        log.info("create new user by name " + userDto.getUsername());

        usersRepository.findById(userDto.getUsername()).ifPresent(user-> {
            throw new UserAlreadyExistsException(user.getUsername());
        });

        Users users = new Users(userDto.getUsername(),
                             passwordEncoder.encode(userDto.getPassword()),
                             new HashSet<>(userDto.getRoles()));
        usersRepository.save(users);
        return new UsernameDto(userDto.getUsername());
    }

    @Override
    public void delete(String username) {
        Users users = usersRepository.findById(username)
            .orElseThrow(
                ()-> new UsernameNotFoundException("api.digital-library.user.by-name.response-not-found" + username)
            );
        usersRepository.delete(users);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersDto userDto = findByUsername(username);

        return User.builder()
            .username(userDto.getUsername())
            .password(userDto.getPassword())
            .roles(userDto.getRoles().toArray(String[]::new))
            .build();
    }
}