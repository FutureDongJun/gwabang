package com.gwabang.gwabang.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ArticleListItemDto {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
}
