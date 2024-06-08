package org.javaacademy.afisha.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Event {
    private Integer id;
    private String name;
    private EventType eventType;
    private LocalDateTime eventDate;
    private Place placeId;
}
