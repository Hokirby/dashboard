package com.example.dashboard.domain.memo.entity;

import com.example.dashboard.common.TimeStamped;
import com.example.dashboard.domain.auth.entity.Member;
import com.example.dashboard.domain.memo_summary.entity.MemoSummary;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Memo extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String title;

    @Column(columnDefinition = "TEXT")
    String content;

    @Column
    LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    Member member;

    @OneToMany(mappedBy = "memo", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<MemoSummary> summaries = new ArrayList<>();

    public Memo(String title, String content, LocalDate date, Member member) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.member = member;
    }

    public void update(String title, String content, LocalDate date) {
        if (title != null) {
            this.title = title;
        }
        if (content != null) {
            this.content = content;
        }
        if (date != null) {
            this.date = date;
        }
    }
}
