package com.project.imdang.batch;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Member {
    private String id;
//    private long accusedCount;
    private LocalDate penaltyFrom;
    private LocalDate penaltyTo;

    @Setter
    private String status;

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", penaltyFrom=" + penaltyFrom +
                ", penaltyTo=" + penaltyTo +
                ", status='" + status + '\'' +
                '}';
    }
}
