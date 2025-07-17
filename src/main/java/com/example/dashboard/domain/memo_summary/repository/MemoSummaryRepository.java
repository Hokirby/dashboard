package com.example.dashboard.domain.memo_summary.repository;

import com.example.dashboard.domain.memo_summary.entity.MemoSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoSummaryRepository extends JpaRepository<MemoSummary, Long> {
}
