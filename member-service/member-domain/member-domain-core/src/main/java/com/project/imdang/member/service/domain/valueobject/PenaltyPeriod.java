package com.project.imdang.member.service.domain.valueobject;

import java.time.LocalDate;

public class PenaltyPeriod {
    private final LocalDate from;
    private final LocalDate to;

    private PenaltyPeriod(int days) {
        this.from = LocalDate.now();
        this.to = from.plusDays(days);
    }

    public static PenaltyPeriod during(int days) {
        return new PenaltyPeriod(days);
    }
}
