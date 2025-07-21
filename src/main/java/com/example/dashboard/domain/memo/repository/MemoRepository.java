package com.example.dashboard.domain.memo.repository;

import com.example.dashboard.domain.memo.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {

    Optional<Memo> findMemoById(Long id);

    Page<Memo> findAllByMemberIdOrderByCreatedAt(Long memberId, Pageable pageable);

    List<Memo> findAllByMemberIdAndDateBetween(Long memberId, LocalDate startDate, LocalDate endDate);
}
