package com.example.dashboard.domain.memo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MemoUpdateRequest {
    private final String title;
    private final String content;
    private final LocalDate date;
}
