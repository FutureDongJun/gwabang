package com.gwabang.gwabang.article.repository;

import com.gwabang.gwabang.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query("SELECT a FROM Article a WHERE a.groupCode = :groupCode ORDER BY a.createdAt DESC")
    List<Article> findByGroupCode(String groupCode);
}
