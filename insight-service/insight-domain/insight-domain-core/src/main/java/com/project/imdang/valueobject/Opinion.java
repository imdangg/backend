package com.project.imdang.valueobject;

public class Opinion<T> {
    private final T choice;
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
