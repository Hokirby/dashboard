package com.example.dashboard.domain.summary.repository;

import com.example.dashboard.domain.summary.entity.Summary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SummaryRepository extends JpaRepository<Summary, Long> {

    @Query("""
                SELECT DISTINCT s FROM Summary s
                JOIN s.memos ms
                JOIN ms.memo m
                WHERE m.member.id = :memberId
                ORDER BY s.createdAt ASC
            """)
    Page<Summary> findAllByMemberIdOOrderByCreatedAt(@Param("memberId") Long memberId, Pageable pageable);
}
