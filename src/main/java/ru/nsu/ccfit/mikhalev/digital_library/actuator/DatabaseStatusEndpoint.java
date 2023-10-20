package ru.nsu.ccfit.mikhalev.digital_library.actuator;

import com.mongodb.BasicDBObject;
import org.springframework.boot.actuate.health.Status;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.*;

import org.springframework.boot.actuate.endpoint.annotation.*;

@Component
@Endpoint(id = "databases")
public class DatabaseStatusEndpoint {
    private final JdbcTemplate jdbcTemplate;
    private final MongoTemplate mongoTemplate;

    public DatabaseStatusEndpoint(JdbcTemplate jdbcTemplate, MongoTemplate mongoTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mongoTemplate = mongoTemplate;
    }

    @ReadOperation
    public Map<String, Status> databases() {
        return Map.of("mongoDB",  mongoDBStatus(), "postgresSQL", postgresqlStatus());
    }


    private Status postgresqlStatus() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        } catch (Exception e) {
            return Status.DOWN;
        }
        return Status.UP;
    }

    private Status mongoDBStatus() {
        try {
            mongoTemplate.getDb().runCommand(new BasicDBObject ("ping", 1));
        } catch (Exception e) {
            return Status.DOWN;
        }
        return Status.UP;
    }
}

