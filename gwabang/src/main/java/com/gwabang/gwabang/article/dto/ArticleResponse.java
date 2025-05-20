package com.gwabang.gwabang.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ArticleResponse {
    private Long articleId;
    private String title;
    private String content;
}