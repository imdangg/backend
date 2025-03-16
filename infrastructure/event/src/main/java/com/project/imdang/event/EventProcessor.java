package com.project.imdang.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.domain.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventProcessor {

    private final EventListener eventListener;
    private final ObjectMapper objectMapper;

    public <T extends DomainEvent> void process(T event) {
        EventEntry eventEntry = EventEntry.builder()
                .type(event.getClass().getSimpleName())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .payload(toJson(event))
                .build();
        eventListener.process(eventEntry);
    }

    private <T extends DomainEvent> String toJson(T event) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            // TODO - 예외 처리
            throw new RuntimeException(e);
        }
    }
}
