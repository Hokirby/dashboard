package com.example.dashboard.domain.memo.service;

import com.example.dashboard.domain.auth.entity.AuthMember;
import com.example.dashboard.domain.auth.entity.Member;
import com.example.dashboard.domain.auth.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

    // 메모 생성
    @Transactional
    public MemoResponse save(AuthMember authMember, MemoCreateRequest memoCreateRequest) {

        Member foundMember = memberRepository.findById(authMember.getMemberId())
                .orElseThrow(() -> new NotFoundException("Member Not Found"));

        Memo memo = new Memo(
                memoCreateRequest.title(),
                memoCreateRequest.content(),
                memoCreateRequest.date(),
                foundMember
        );

        Memo savedMemo = memoRepository.save(memo);

        return MemoResponse.toDto(savedMemo);
    }

    // 메모 단건 조회
    @Transactional(readOnly = true)
    public MemoResponse getMemo(AuthMember authMember, Long id) {

        Memo foundMemo = memoRepository.findMemoById(id)
                .orElseThrow(() -> new NotFoundException("Memo Not Found"));

        if (!authMember.getMemberId().equals(foundMemo.getMember().getId())) {
            throw new InvalidRequestException("Invalid Member ID");
        }

        return MemoResponse.toDto(foundMemo);
    }

    // 메모 다건 조회
    @Transactional(readOnly = true)
    public Page<MemoListResponse> getMemos(AuthMember authMember, Pageable pageable) {

        Page<Memo> memos = memoRepository.findAllByMemberIdOrderByCreatedAtDesc(authMember.getMemberId(), pageable);

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
