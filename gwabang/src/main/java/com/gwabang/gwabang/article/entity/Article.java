package com.gwabang.gwabang.article.entity;

import com.gwabang.gwabang.articlelike.entity.ArticleLike;
import com.gwabang.gwabang.category.entity.Category;
import com.gwabang.gwabang.comment.entity.Comment;
import com.gwabang.gwabang.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="article")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "article")
    private List<ArticleLike> articleLikes = new ArrayList<>();


}
