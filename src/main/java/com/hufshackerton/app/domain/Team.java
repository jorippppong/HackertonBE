package com.hufshackerton.app.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Team extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String imageUrl;

    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.ALL)
    private List<BaseballGame> homeBaseballGameList = new ArrayList<>();

    @OneToMany(mappedBy = "awayTeam", cascade = CascadeType.ALL)
    private List<BaseballGame> awayBaseballGameList = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Member> memberList = new ArrayList<>();

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Bet> betList = new ArrayList<>();
}
