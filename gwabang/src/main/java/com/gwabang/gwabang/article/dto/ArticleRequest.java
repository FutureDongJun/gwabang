package com.gwabang.gwabang.article.dto;

import lombok.*;

@Setter
@Getter
@Builder
public class ArticleRequest {
    private Long categoryId;
    private String title;
    private String content;
}
