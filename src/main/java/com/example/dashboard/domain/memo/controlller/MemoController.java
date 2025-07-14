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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/memos")
public class MemoController {

    private final MemoService memoService;

    // 메모 생성
    @PostMapping
    public MemoResponse save(@Valid @RequestBody MemoCreateRequest memoCreateRequest) {
        return memoService.save(memoCreateRequest);
    }

    // 메모 단건 조회
    @GetMapping("/{id}")
    public MemoResponse getMemo(@PathVariable Long id) {
        return memoService.getMemo(id);
    }

    // 메모 다건 조회
    @GetMapping
    public Page<MemoListResponse> getMemos(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return memoService.getMemos(PageRequest.of(page - 1, size));
    }

    // 매모 수정
    @PostMapping("/{id}")
    public MemoResponse update(@AuthenticationPrincipal AuthMember authMember,
                               @Valid @RequestBody MemoUpdateRequest memoUpdateRequest,
                               @PathVariable Long id
    ) {
        return memoService.update(id, authMember, memoUpdateRequest);
    }

    // 메모 삭제
    @DeleteMapping("/{id}")
    public void delete(
            @AuthenticationPrincipal AuthMember authMember,
            @PathVariable Long id
    ) {
        memoService.delete(id, authMember);
    }
}
