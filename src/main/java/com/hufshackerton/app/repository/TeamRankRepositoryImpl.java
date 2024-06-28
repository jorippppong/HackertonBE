package com.hufshackerton.app.repository;

import com.hufshackerton.app.web.dto.repo.SimpleTeamInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TeamRankRepositoryImpl implements TeamRankRepository{
    @PersistenceContext
    private final EntityManager em;

    @Override
    public List<SimpleTeamInfo> sortTeamByMission(LocalDate startDate, LocalDate endDate) {
        return em.createQuery(
                "select new com.hufshackerton.app.web.dto.repo.SimpleTeamInfo(t.id, count(*))"+
                        " from MemberMission mm, Member m, Team t"+
                        " where mm.member = m and m.team = t and mm.isCompleted = true"+
                        " and function('DATE', mm.completeAt) between :startDate and :endDate"+
                        " group by t.id"+
                        " order by count(*) desc", SimpleTeamInfo.class)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}
