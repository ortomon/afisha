package org.javaacademy.afisha.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

// 2.5 Создать репозитории (для каждой сущности), которые позволят:
// сохранить новую сущность, получить сущность по id, получить все сущности.
@Component
@RequiredArgsConstructor
public class EventTypeRepository {
    private JdbcTemplate jdbcTemplate;

    public
}
