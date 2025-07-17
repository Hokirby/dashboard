package com.example.dashboard.domain.summary.repository;

import com.example.dashboard.domain.summary.entity.Summary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SummaryRepository extends JpaRepository<Summary, Long> {
}
