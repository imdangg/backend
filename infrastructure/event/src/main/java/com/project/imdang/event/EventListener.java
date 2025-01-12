package com.project.imdang.event;

public interface EventListener {
    // 포워더에서 받은 이벤트 처리
    void process(EventEntry eventEntry);
}
