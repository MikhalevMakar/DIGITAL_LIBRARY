package ru.nsu.ccfit.mikhalev.digital_library.actuator;

import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.boot.actuate.health.Status;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Endpoint(id = "databases")
public class DatabaseStatusEndpoint {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseStatusEndpoint(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @ReadOperation
    private Map<String, Status> databases() {
        return Map.of("mongoDB", Status.UP, "postgresSQL", Status.UP);
    }


}
