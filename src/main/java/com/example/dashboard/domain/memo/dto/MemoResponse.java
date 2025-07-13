package com.example.dashboard.domain.memo.dto;

import com.example.dashboard.domain.memo.Memo;

import java.sql.Timestamp;
import java.time.LocalDate;

public record MemoResponse(
        Long id,
        String title,
        String content,
        LocalDate date,
        Timestamp createdAt
) {
    public static MemoResponse toDto(Memo memo) {
        return new MemoResponse(
                memo.getId(),
                memo.getTitle(),
                memo.getContent(),
                memo.getDate(),
                memo.getCreatedAt()
        );
    }
}
