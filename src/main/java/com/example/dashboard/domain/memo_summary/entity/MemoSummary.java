package com.example.dashboard.domain.memo_summary.entity;

import com.example.dashboard.domain.memo.entity.Memo;
import com.example.dashboard.domain.summary.entity.Summary;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemoSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memo_id")
    private Memo memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "summary_id", nullable = true)
    private Summary summary;

    public MemoSummary(Memo memo, Summary summary) {
        this.memo = memo;
    }
}
