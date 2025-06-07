package com.gwabang.gwabang.category.controller;


import com.gwabang.gwabang.article.dto.ArticleListItemDto;
import com.gwabang.gwabang.article.dto.ArticleRequest;
import com.gwabang.gwabang.article.dto.ArticleResponse;
import com.gwabang.gwabang.article.service.ArticleService;
import com.gwabang.gwabang.category.entity.Category;
import com.gwabang.gwabang.category.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/{groupCode}")
    public ResponseEntity<CategoryDto> getCategoryIdByGroupCode(@PathVariable String groupCode) {
        // 1. groupCode를 Integer로 변환
        Integer code = Integer.valueOf(groupCode);

        // 2. repository 메서드 호출
        Category category = categoryRepository.findByDepartmentGroup_Code(code)
                .orElseThrow(() -> new RuntimeException("카테고리 없음"));

        return ResponseEntity.ok(new CategoryDto(category.getId()));
    }


    @Getter
    @AllArgsConstructor
    static class CategoryDto {
        private Long id;
    }
}
