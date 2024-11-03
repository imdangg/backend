package com.project.imdang.valueobject;

public class Password {
    private String value;

    public Password(String value) {
        this.value = value;
    }

    public boolean match(String password) {
        return value.equals(password);
    }
}
