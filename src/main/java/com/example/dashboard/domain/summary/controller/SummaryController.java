package com.example.dashboard.domain.summary.controller;

import com.example.dashboard.domain.auth.entity.AuthMember;
import com.example.dashboard.domain.summary.dto.SummaryListResponse;
import com.example.dashboard.domain.summary.dto.SummaryResponse;
import com.example.dashboard.domain.summary.service.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/summaries")
public class SummaryController {

    private final SummaryService summaryService;

    // 요약 생성 후 저장
    @PostMapping
    public ResponseEntity<SummaryResponse> summarizeAndSave(
            @AuthenticationPrincipal AuthMember authMember,
            @RequestParam(name = "date") LocalDate date,
            @RequestParam(name = "type") String type
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(summaryService.SummarizeAndSave(authMember, date, type));
    }

    // 요약 조회
    @GetMapping("/{id}")
    public ResponseEntity<SummaryResponse> get(
            @AuthenticationPrincipal AuthMember authMember,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(summaryService.find(authMember, id));
    }

    // 요약 다건 조회
    @GetMapping
    public ResponseEntity<Page<SummaryListResponse>> getSummaries(
            @AuthenticationPrincipal AuthMember authMember,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(summaryService.findSummaries(authMember, PageRequest.of(page - 1, size)));
    }

    // 요약 재생성 후 수정
    @PatchMapping("/{id}")
    public ResponseEntity<SummaryResponse> update(
            @AuthenticationPrincipal AuthMember authMember,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(summaryService.update(authMember, id));
    }

    // 요약 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal AuthMember authMember,
            @PathVariable Long id
    ) {
        summaryService.delete(authMember, id);
        return ResponseEntity.ok().build();
    }
}
