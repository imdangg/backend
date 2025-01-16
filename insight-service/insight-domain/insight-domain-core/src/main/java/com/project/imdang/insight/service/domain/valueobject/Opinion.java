package com.project.imdang.insight.service.domain.valueobject;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Opinion<T> {
    @NotNull
    private T choice;
    private String text;

    @Builder
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
