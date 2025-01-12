package com.project.imdang.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventForwarder {

    private static final int DEFAULT_LIMIT_SIZE = 100;

    private final EventManager eventManager;
    private final OffsetManager offsetManager;


    @Scheduled(initialDelay = 1000L, fixedDelay = 1000L)
    public void getAndSend() {
        long nextOffset = getNextOffset();
        List<EventEntry> eventEntries = eventManager.get(nextOffset, DEFAULT_LIMIT_SIZE);
        if (!eventEntries.isEmpty()) {
            int processedCount = processEvent(eventEntries);
            if (processedCount > 0) {
                saveNextOffset(nextOffset + processedCount);
            }
        }
    }

    private void saveNextOffset(long nextOffset) {
        offsetManager.update(nextOffset);
    }

    private int processEvent(List<EventEntry> eventEntries) {
        int processedCount = 0;

        try {
            for (EventEntry eventEntry : eventEntries) {
//                eventListener.process(eventEntry);
                processedCount++;
            }
        } catch (Exception e) {
            // TODO - 로깅
            log.warn("Error!");
        }
        return processedCount;
    }

    private long getNextOffset() {
        return offsetManager.get();
    }
}
