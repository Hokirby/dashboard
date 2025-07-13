package com.example.dashboard.domain.memo.dto;

import com.example.dashboard.domain.memo.Memo;

import java.sql.Timestamp;
import java.time.LocalDate;

public record MemoListResponse(
        Long id,
        String title,
        LocalDate date,
        Timestamp createdAt
) {
    public static MemoListResponse of(Memo memo) {
        return new MemoListResponse(
                memo.getId(),
                memo.getTitle(),
                memo.getDate(),
                memo.getCreatedAt()
        );
    }
}
