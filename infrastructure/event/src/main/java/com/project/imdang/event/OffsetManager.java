package com.project.imdang.event;

public interface OffsetManager {
    long get();
    void update(long nextOffset);
}
