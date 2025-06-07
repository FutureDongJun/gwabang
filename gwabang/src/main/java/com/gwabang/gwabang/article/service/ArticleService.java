package com.gwabang.gwabang.article.service;

import com.gwabang.gwabang.article.dto.ArticleListItemDto;
import com.gwabang.gwabang.article.dto.ArticleRequest;
import com.gwabang.gwabang.article.dto.ArticleResponse;
import com.gwabang.gwabang.article.entity.Article;
import com.gwabang.gwabang.article.repository.ArticleRepository;
import com.gwabang.gwabang.category.entity.Category;
import com.gwabang.gwabang.category.repository.CategoryRepository;
import com.gwabang.gwabang.member.entity.Member;
import com.gwabang.gwabang.member.repository.MemberRepository;
import com.gwabang.gwabang.security.config.jwt.TokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
//    private final DepartmentGroupRepository departmentGroupRepository;
    private final TokenProvider tokenProvider;
    @Transactional
    public ArticleResponse createArticle(String groupCode, ArticleRequest dto, String accessToken) {
        // ✅ accessToken에서 email 추출
        Long memberId = Long.parseLong(tokenProvider.getEmailFromToken(accessToken));


        // ✅ 이메일로 Member 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("회원 정보 없음"));

        // ✅ 학과 그룹 검사
        String userGroupCode = String.valueOf(member.getDepartment().getDepartmentGroup().getCode());
        if (!userGroupCode.equals(groupCode)) {
            throw new RuntimeException("접근 권한 없음: 게시판 접근 불가");
        }
        System.out.println("usergroupcode:"+userGroupCode + " gc:"+groupCode);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!"+dto.getCategoryId());


        // ✅ 카테고리 조회
        Integer code = Integer.valueOf(groupCode);
        Category category = categoryRepository.findByDepartmentGroup_Code(code)
                .orElseThrow(() -> new RuntimeException("카테고리를 찾을 수 없습니다."));

        // ✅ 게시글 저장
        Article article = Article.builder()
                .category(category)
                .member(member)
                .title(dto.getTitle())
                .content(dto.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        articleRepository.save(article);

        return new ArticleResponse(article.getId(), article.getTitle(), article.getContent());
    }


    @Transactional
    public ArticleResponse getArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return new ArticleResponse(article.getId(), article.getTitle(), article.getContent());
    }

    @Transactional
    public ArticleResponse updateArticle(Long id, ArticleRequest dto, String accessToken) {
        String email = tokenProvider.getEmailFromToken(accessToken);

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        if (!article.getMember().getEmail().equals(email)) {
            throw new RuntimeException("작성자만 수정할 수 있습니다.");
        }

        article.update(dto.getTitle(), dto.getContent());
        return new ArticleResponse(article.getId(), article.getTitle(), article.getContent());
    }

    @Transactional
    public void deleteArticle(Long id, String accessToken) {
        String email = tokenProvider.getEmailFromToken(accessToken);

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        if (!article.getMember().getEmail().equals(email)) {
            throw new RuntimeException("작성자만 삭제할 수 있습니다.");
        }

        articleRepository.delete(article);
    }
    public List<ArticleListItemDto> getArticlesByGroupCode(String groupCode) {
        List<Article> articles = articleRepository.findByGroupCode(groupCode);
        return articles.stream()
                .map(article -> new ArticleListItemDto(
                        article.getId(),
                        article.getTitle(),
                        article.getCreatedAt()
                ))
                .collect(Collectors.toList());
    }


}
