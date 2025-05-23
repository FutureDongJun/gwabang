package com.gwabang.gwabang.article.controller;

import com.gwabang.gwabang.article.dto.ArticleRequest;
import com.gwabang.gwabang.article.dto.ArticleResponse;
import com.gwabang.gwabang.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/{groupCode}")
    public ResponseEntity<ArticleResponse> createArticle(
            @PathVariable String groupCode,
            @RequestBody ArticleRequest dto,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        // Bearer 토큰에서 실제 access token 추출
        String accessToken = authorizationHeader.startsWith("Bearer ")
                ? authorizationHeader.substring(7)
                : authorizationHeader;

        ArticleResponse result = articleService.createArticle(groupCode, dto, accessToken);
        return ResponseEntity.ok(result);
    }

}
