package com.hufshackerton.app.domain.mapping;

import com.hufshackerton.app.domain.Member;
import com.hufshackerton.app.domain.Mission;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Builder.Default
    private Boolean isCompleted = false;

    @Column
    private String imageUrl;

    private LocalDateTime completeAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    public void missionComplete(String imageUrl) {
        this.imageUrl = imageUrl;
        this.isCompleted = true;
        this.completeAt = LocalDateTime.now();
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

}
