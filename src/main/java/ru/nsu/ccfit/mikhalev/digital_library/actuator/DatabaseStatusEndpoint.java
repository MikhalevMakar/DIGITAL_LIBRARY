package ru.nsu.ccfit.mikhalev.digital_library.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Endpoint(id = "database")
public class DatabaseStatusEndpoint {
    @ReadOperation
    private Map<String, Status> databases() {
        return Map.of("mongoDB", Status.UP, "postgresSQL", Status.UP);
    }
}
