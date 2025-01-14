package com.project.imdang.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.imdang.domain.event.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JdbcEventManager implements EventManager {

    private final ObjectMapper objectMapper;
    private final JdbcTemplate jdbcTemplate;

    private <T extends DomainEvent> String toJson(T event) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            // TODO - 예외 처리
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T extends DomainEvent> void save(T event) {
        EventEntry eventEntry = EventEntry.builder()
                .type(event.getClass().getName())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .payload(toJson(event))
                .build();
        jdbcTemplate.update(
                "insert into event_entry(type, content_type, payload, timestamp) values (?, ?, ?, ?)",
                ps -> {
                    ps.setString(1, eventEntry.getType());
                    ps.setString(2, eventEntry.getContentType());
                    ps.setString(3, eventEntry.getPayload());
                    ps.setTimestamp(4, new Timestamp(eventEntry.getTimestamp()));
                });
    }

    @Override
    public List<EventEntry> get(long offset, long limit) {
        return jdbcTemplate.query(
                "select * from event_entry order by id asc limit ?, ?",
                ps -> {
                    ps.setLong(1, offset);
                    ps.setLong(2, limit);
                },
                (rs, rowNum) -> new EventEntry(
                        rs.getLong("id"),
                        rs.getString("type"),
                        rs.getString("content_type"),
                        rs.getString("payload"),
                        rs.getTimestamp("timestamp").getTime()
                )
        );
    }
}
