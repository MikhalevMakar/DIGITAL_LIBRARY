package ru.nsu.ccfit.mikhalev.digital_library.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nsu.ccfit.mikhalev.digital_library.model.entity.jpa.Users;

public interface UsersRepository extends JpaRepository<Users, String> {
}
