package com.hufshackerton.app.repository;

import com.hufshackerton.app.web.dto.repo.SimpleMemberInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRankRepository {
    @PersistenceContext
    private final EntityManager em;

    public List<SimpleMemberInfo> sortMemberByMission(LocalDate startDate, LocalDate endDate){
        return em.createQuery(
                "select new com.hufshackerton.app.web.dto.repo.SimpleMemberInfo(m.id, count(*))"+
                        " from MemberMission mm, Member m"+
                        " where mm.member = m and mm.isCompleted = true"+
                        " and function('DATE', mm.completeAt) between :startDate and :endDate"+
                        " group by member.id"+
                        " order by count(*) desc"+
                        " limit 3", SimpleMemberInfo.class
                )
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }

}
