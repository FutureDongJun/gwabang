package com.gwabang.gwabang.article.repository;

import com.gwabang.gwabang.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByGroupCode(String groupCode);
}
