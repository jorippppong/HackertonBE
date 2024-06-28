package com.hufshackerton.app.repository;

import com.hufshackerton.app.domain.Donate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonateRepository extends JpaRepository<Donate, Long> {



}
