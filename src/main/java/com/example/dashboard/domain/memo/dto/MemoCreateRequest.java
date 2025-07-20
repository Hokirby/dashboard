package com.example.dashboard.domain.memo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MemoCreateRequest(
        @NotBlank(message = "Title Must Be Entered")
        String title,
        @NotBlank(message = "Content Must Be Entered")
        String content,
        @NotNull(message = "Date Must Be Entered")
        LocalDate date
) {
}
