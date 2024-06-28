package com.hufshackerton.app.domain;

import com.hufshackerton.app.domain.mapping.MemberMission;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Embedded
    private Password password;

    @Builder.Default
    private Integer point = 0;

    @Builder.Default
    private Integer accumulateDonatePoint = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Bet> betList = new ArrayList<>();

    public void setPoint(Integer point) {
        this.point = point;
    }

    public void setAccumulateDonatePoint(Integer accumulateDonatePoint) {
        this.accumulateDonatePoint = accumulateDonatePoint;
    }

    public void setTeam(Team team){this.team = team;}
}
