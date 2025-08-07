package com.example.dashboard.domain.summary.enums;

import com.example.dashboard.exception.NotFoundException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;

public enum TermType {
    DAILY {
        @Override
        public LocalDate[] getDateRange(LocalDate date) {
            return new LocalDate[]{date, date};
        }
    },
    WEEKLY {
        @Override
        public LocalDate[] getDateRange(LocalDate date) {
            LocalDate start = date.with(DayOfWeek.MONDAY);
            LocalDate end = start.plusDays(6);
            return new LocalDate[]{start, end};
        }
    },
    MONTHLY {
        @Override
        public LocalDate[] getDateRange(LocalDate date) {
            LocalDate start = date.withDayOfMonth(1);
            LocalDate end = date.withDayOfMonth(date.lengthOfMonth());
            return new LocalDate[]{start, end};
        }
    };

    public abstract LocalDate[] getDateRange(LocalDate localDate);

    public static TermType of(String type) {
        return Arrays.stream(TermType.values())
                .filter(t -> t.toString().equalsIgnoreCase(type))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("TermType Not Found"));
    }
}
