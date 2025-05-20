package com.gwabang.gwabang.article.dto;

import lombok.*;

@Getter
@Builder
public class ArticleRequest {
    private Long categoryId;
    private String title;
    private String content;
}
