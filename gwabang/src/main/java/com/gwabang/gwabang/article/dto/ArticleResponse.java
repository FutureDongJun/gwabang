package com.gwabang.gwabang.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ArticleResponse {
    private Long articleId;
    private String title;
    private String content;
    private LocalDateTime createdAt;

}