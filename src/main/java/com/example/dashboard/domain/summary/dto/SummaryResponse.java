package com.example.dashboard.domain.summary.dto;

import com.example.dashboard.domain.summary.entity.Summary;
import com.example.dashboard.domain.summary.enums.TermType;

import java.sql.Timestamp;
import java.util.List;

public record SummaryResponse(
        Long id,
        String content,
        TermType term,
        Timestamp createdAt,
        List<Long> memoIds
) {
    public static SummaryResponse toDto(Summary summary, List<Long> memoIds) {
        return new SummaryResponse(
                summary.getId(),
                summary.getContent(),
                summary.getTerm(),
                summary.getCreatedAt(),
                memoIds
        );
    }
}
