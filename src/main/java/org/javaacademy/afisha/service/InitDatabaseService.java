package org.javaacademy.afisha.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
@Profile("first")
@RequiredArgsConstructor
public class InitDatabaseService {
    private final JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void runScript() {
        boolean continueOnError = false;
        boolean ignoreFailedDrops = false;
        String commentPrefix = "--";
        String separator = ";";
        String blockCommentStartDelimiter = "/*";
        String blockCommentEndDelimiter = "*/";

        Resource resource = new ClassPathResource("init.sql");
        EncodedResource encodedResource = new EncodedResource(resource);

        System.out.println("Executing SQL script...");

        jdbcTemplate.execute((Connection connection) -> {
            try {
                ScriptUtils.executeSqlScript(
                        connection,
                        encodedResource,
                        continueOnError,
                        ignoreFailedDrops,
                        commentPrefix,
                        separator,
                        blockCommentStartDelimiter,
                        blockCommentEndDelimiter
                );
                System.out.println("SQL script executed successfully.");
            } catch (Exception e) {
                System.out.println("Error executing SQL script: " + e.getMessage());
                throw new RuntimeException("Failed to execute database script", e);
            }
            return null;
        });
    }
}
