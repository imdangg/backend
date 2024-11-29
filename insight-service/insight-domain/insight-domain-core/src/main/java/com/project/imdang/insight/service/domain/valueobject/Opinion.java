package com.project.imdang.insight.service.domain.valueobject;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Opinion<T> {
    private T choice;
    private String text;

    public Opinion(T choice, String text) {
        if (choice == null) {
            // TODO - CHECK
            throw new RuntimeException();
        }
        this.choice = choice;
        this.text = text;
    }

    public void validate() {
        if (this.choice == null) {
            // TODO - exception
            throw new RuntimeException();
        }
    }
}
