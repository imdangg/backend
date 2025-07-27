package com.project.imdang.member.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@Table(name = "member_priority")
@Entity
public class MemberPriorityEntity {

    @Id
    private Long id;

    private UUID memberId;
    private String first;
    private String second;
    private String third;
}
