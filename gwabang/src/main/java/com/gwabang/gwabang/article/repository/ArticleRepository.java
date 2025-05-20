package com.gwabang.gwabang.article.repository;

import com.gwabang.gwabang.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
