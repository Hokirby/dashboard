package com.example.dashboard.domain.summary.dto;

import com.example.dashboard.domain.summary.entity.Summary;
import com.example.dashboard.domain.summary.enums.TermType;

import java.sql.Timestamp;

public record SummaryListResponse(
        Long id,
        String content,
        TermType termType,
        Timestamp createdAt
) {
    public static SummaryListResponse of(Summary summary) {
        return new SummaryListResponse(
                summary.getId(),
                summary.getContent(),
                summary.getTerm(),
                summary.getCreatedAt()
        );
    }
}
