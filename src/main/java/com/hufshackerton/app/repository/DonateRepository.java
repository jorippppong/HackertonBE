package com.hufshackerton.app.repository;

import com.hufshackerton.app.domain.Donate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonateRepository extends JpaRepository<Donate, Long> {

    List<Donate> findAll();

}
