package com.example.dashboard.domain.summary.entity;

import com.example.dashboard.common.TimeStamped;
import com.example.dashboard.domain.memo_summary.entity.MemoSummary;
import com.example.dashboard.domain.summary.enums.TermType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Summary extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @Enumerated(EnumType.STRING)
    private TermType term;

    @OneToMany(mappedBy = "summary", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<MemoSummary> memos = new ArrayList<>();

    public Summary(String content, TermType term) {
        this.content = content;
        this.term = term;
    }

    public void updateContent(String content) {
        this.content = content;
    }

}
