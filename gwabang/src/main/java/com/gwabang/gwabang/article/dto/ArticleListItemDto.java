package com.gwabang.gwabang.article.dto;

import com.gwabang.gwabang.category.entity.Category;
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
