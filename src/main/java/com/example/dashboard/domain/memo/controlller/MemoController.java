package com.example.dashboard.domain.memo.controlller;

import com.example.dashboard.domain.auth.entity.AuthMember;
import com.example.dashboard.domain.memo.dto.MemoCreateRequest;
import com.example.dashboard.domain.memo.dto.MemoListResponse;
import com.example.dashboard.domain.memo.dto.MemoResponse;
import com.example.dashboard.domain.memo.dto.MemoUpdateRequest;
import com.example.dashboard.domain.memo.service.MemoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memos")
public class MemoController {

    private final MemoService memoService;

    // 메모 생성
    @PostMapping
    public ResponseEntity<MemoResponse> save(
            @AuthenticationPrincipal AuthMember authMember,
            @Valid @RequestBody MemoCreateRequest memoCreateRequest
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memoService.save(authMember, memoCreateRequest));
    }

    // 메모 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<MemoResponse> get(
            @AuthenticationPrincipal AuthMember authMember,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(memoService.find(authMember, id));
    }

    // 메모 다건 조회
    @GetMapping
    public ResponseEntity<Page<MemoListResponse>> getMemos(
            @AuthenticationPrincipal AuthMember authMember,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(memoService.findMemos(authMember, PageRequest.of(page - 1, size)));
    }

    // 매모 수정
    @PatchMapping("/{id}")
    public ResponseEntity<MemoResponse> update(
            @AuthenticationPrincipal AuthMember authMember,
            @Valid @RequestBody MemoUpdateRequest memoUpdateRequest,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(memoService.update(id, authMember, memoUpdateRequest));
    }

    // 메모 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal AuthMember authMember,
            @PathVariable Long id
    ) {
        memoService.delete(id, authMember);
        return ResponseEntity.ok().build();
    }
}
