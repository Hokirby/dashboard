package com.example.dashboard.domain.memo.service;

import com.example.dashboard.domain.auth.entity.AuthMember;
import com.example.dashboard.domain.memo.entity.Memo;
import com.example.dashboard.domain.memo.dto.MemoCreateRequest;
import com.example.dashboard.domain.memo.dto.MemoListResponse;
import com.example.dashboard.domain.memo.dto.MemoResponse;
import com.example.dashboard.domain.memo.dto.MemoUpdateRequest;
import com.example.dashboard.domain.memo.repository.MemoRepository;
import com.example.dashboard.exception.InvalidRequestException;
import com.example.dashboard.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    // 메모 생성
    @Transactional
    public MemoResponse save(MemoCreateRequest memoCreateRequest) {

        Memo memo = new Memo(
                memoCreateRequest.title(),
                memoCreateRequest.content(),
                memoCreateRequest.date()
        );

        Memo savedMemo = memoRepository.save(memo);

        return MemoResponse.toDto(savedMemo);
    }

    // 메모 단건 조회
    @Transactional(readOnly = true)
    public MemoResponse getMemo(Long id) {

        Memo foundMemo = memoRepository.findMemoById(id)
                .orElseThrow(() -> new NotFoundException("Memo Not Found"));

        return MemoResponse.toDto(foundMemo);
    }

    // 메모 다건 조회
    @Transactional(readOnly = true)
    public Page<MemoListResponse> getMemos(Pageable pageable) {

        Page<Memo> memos = memoRepository.findAllByOrderByCreatedAtDesc(pageable);

        return memos.map(MemoListResponse::of);
    }

    // 메모 수정
    @Transactional
    public MemoResponse update(Long id, AuthMember authMember, MemoUpdateRequest memoUpdateRequest) {

        Memo foundMemo = memoRepository.findMemoById(id)
                .orElseThrow(() -> new NotFoundException("Memo Not Found"));

        if (!foundMemo.getMember().getId().equals(authMember.getMemberId())) {
            throw new InvalidRequestException("Invalid Member ID");
        }

        foundMemo.update(
                memoUpdateRequest.getTitle(),
                memoUpdateRequest.getContent(),
                memoUpdateRequest.getDate()
        );

        return MemoResponse.toDto(foundMemo);
    }

    // 메모 삭제
    @Transactional
    public void delete(Long id, AuthMember authMember) {

        Memo foundMemo = memoRepository.findMemoById(id)
                .orElseThrow(() -> new NotFoundException("Memo Not Found"));

        if (!foundMemo.getMember().getId().equals(authMember.getMemberId())) {
            throw new InvalidRequestException("Invalid Member ID");
        }

        memoRepository.delete(foundMemo);
    }
}
