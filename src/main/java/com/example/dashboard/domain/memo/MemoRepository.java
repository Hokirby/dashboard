package com.example.dashboard.domain.memo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {

    Optional<Memo> findMemoById(Long id);

    Page<Memo> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
