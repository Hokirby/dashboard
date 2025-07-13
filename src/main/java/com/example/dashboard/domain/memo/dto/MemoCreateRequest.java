package com.example.dashboard.domain.memo.dto;

import java.time.LocalDate;

public record MemoCreateRequest(
        String title,
        String content,
        LocalDate date
) {
}
