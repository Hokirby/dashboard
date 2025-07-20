package com.example.dashboard.domain.summary.service;

import com.example.dashboard.client.ChatService;
import com.example.dashboard.domain.auth.entity.AuthMember;
import com.example.dashboard.domain.memo.entity.Memo;
import com.example.dashboard.domain.memo.repository.MemoRepository;
import com.example.dashboard.domain.memo_summary.entity.MemoSummary;
import com.example.dashboard.domain.memo_summary.repository.MemoSummaryRepository;
import com.example.dashboard.domain.summary.dto.SummaryResponse;
import com.example.dashboard.domain.summary.entity.Summary;
import com.example.dashboard.domain.summary.enums.TermType;
import com.example.dashboard.domain.summary.repository.SummaryRepository;
import com.example.dashboard.exception.InvalidRequestException;
import com.example.dashboard.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SummaryService {

    private final ChatService chatService;
    private final MemoRepository memoRepository;
    private final SummaryRepository summaryRepository;
    private final MemoSummaryRepository memoSummaryRepository;

    // 요약 생성 후 저장
    @Transactional
    public SummaryResponse SummarizeAndSave(AuthMember authMember, LocalDate date, String type) {

        TermType termType = TermType.of(type);
        LocalDate[] range = termType.getDateRange(date);

        List<Memo> memos = memoRepository.findAllByMemberIdAndDateBetween(authMember.getMemberId(), range[0], range[1]);

        if (memos.isEmpty()) {
            throw new NotFoundException("Memo Not Found");
        }

        String combinedContent = memos.stream()
                .map(Memo::getContent)
                .collect(Collectors.joining("\n\n"));

        String summaryText = chatService.generateSummaryText(combinedContent, type);

        Summary summary = new Summary(
                summaryText,
                termType
        );

        Summary savedSummary = summaryRepository.save(summary);

        for (Memo memo : memos) {
            MemoSummary memoSummary = new MemoSummary(memo, savedSummary);
            savedSummary.getMemos().add(memoSummary);
            memoSummaryRepository.save(memoSummary);
        }

        List<Long> memoIds = savedSummary.getMemos()
                .stream()
                .map(s -> s.getMemo().getId())
                .toList();

        return SummaryResponse.toDto(savedSummary, memoIds);
    }

    // 요약 조회
    @Transactional(readOnly = true)
    public SummaryResponse find(AuthMember authMember, Long id) {

        Summary foundSummary = authenticateAndValidate(authMember, id);

        List<Long> memoIds = foundSummary.getMemos()
                .stream()
                .map(s -> s.getMemo().getId())
                .toList();

        return SummaryResponse.toDto(foundSummary, memoIds);
    }

    // 요약 재생성, 교체
    @Transactional
    public SummaryResponse update(AuthMember authMember, Long id) {

        Summary foundSummary = authenticateAndValidate(authMember, id);

        List<Memo> memos = foundSummary.getMemos().stream()
                .map(MemoSummary::getMemo)
                .toList();

        String combinedContent = memos.stream()
                .map(Memo::getContent)
                .collect(Collectors.joining("\n\n"));

        String generatedText = chatService.generateSummaryText(combinedContent, foundSummary.getTerm().name());

        foundSummary.updateContent(generatedText);

        List<Long> memoIds = memos.stream()
                .map(Memo::getId)
                .toList();

        return SummaryResponse.toDto(foundSummary, memoIds);
    }

    // 요약 삭제
    @Transactional
    public void delete(AuthMember authMember, Long id) {

        Summary foundSummary = authenticateAndValidate(authMember, id);
        summaryRepository.delete(foundSummary);
    }

    private Summary authenticateAndValidate(AuthMember authMember, Long id) {

        Summary foundSummary = summaryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Summary Not Found"));

        List<MemoSummary> memoSummaries = foundSummary.getMemos();
        if (memoSummaries.isEmpty()) {
            throw new NotFoundException("Connected Memo Not Found");
        }

        Long ownerId = memoSummaries.get(0).getMemo().getMember().getId();
        if (!authMember.getMemberId().equals(ownerId)) {
            throw new InvalidRequestException("Invalid Member ID");
        }

        return foundSummary;
    }
}
