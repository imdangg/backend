package com.project.imdang.event;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JdbcOffsetManager implements OffsetManager {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public long get() {
        return jdbcTemplate.queryForObject(
                "select value from offset",
                Long.class
        );
    }

    @Override
    public void update(long nextOffset) {
        jdbcTemplate.update(
                "update offset set value = ? where id = ?",
                ps -> {
                    ps.setLong(1, nextOffset);
                    ps.setLong(2, 1);
                });
    }
}
