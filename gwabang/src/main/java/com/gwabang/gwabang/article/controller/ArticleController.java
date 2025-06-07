package com.gwabang.gwabang.article.controller;

import com.gwabang.gwabang.article.dto.ArticleListItemDto;
import com.gwabang.gwabang.article.dto.ArticleRequest;
import com.gwabang.gwabang.article.dto.ArticleResponse;
import com.gwabang.gwabang.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article/{groupCode}")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/list")
    public ResponseEntity<List<ArticleListItemDto>> getArticlesByGroupCode(
            @PathVariable String groupCode

    ) {
        System.out.println("🔍 [Controller] groupCode: " + groupCode); // ✅ 여기!

        List<ArticleListItemDto> articles = articleService.getArticlesByGroupCode(groupCode);
        return ResponseEntity.ok(articles);
    }

    @PostMapping("/write")
    public ResponseEntity<ArticleResponse> createArticle(
            @PathVariable String groupCode,
            @RequestBody ArticleRequest dto,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        // Bearer 토큰에서 실제 access token 추출
        String accessToken = authorizationHeader.startsWith("Bearer ")
                ? authorizationHeader.substring(7)
                : authorizationHeader;
        //System.out.println("dto = " + dto);

        ArticleResponse result = articleService.createArticle(groupCode, dto, accessToken);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long id) {
        ArticleResponse article = articleService.getArticle(id);
    return ResponseEntity.ok(article);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponse> updateArticle(
        @PathVariable Long id,
        @RequestBody ArticleRequest dto,
        @RequestHeader("Authorization") String authorizationHeader
    ) {
        String accessToken = authorizationHeader.startsWith("Bearer ")
                ? authorizationHeader.substring(7)
                : authorizationHeader;

        ArticleResponse updated = articleService.updateArticle(id, dto, accessToken);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String accessToken = authorizationHeader.startsWith("Bearer ")
                ? authorizationHeader.substring(7)
                : authorizationHeader;

        articleService.deleteArticle(id, accessToken);
        return ResponseEntity.noContent().build(); // 삭제 성공 시 204 응답
    }

}
