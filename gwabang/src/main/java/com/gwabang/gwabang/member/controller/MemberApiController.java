package com.gwabang.gwabang.member.controller;

import com.gwabang.gwabang.member.dto.AddMemberRequest;
import com.gwabang.gwabang.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class MemberApiController {
    private final MemberService memberService;

    public MemberApiController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/api/user/signup")
    public ResponseEntity<String> signup(@RequestBody AddMemberRequest request) {
        memberService.save(request);
        return ResponseEntity.ok("회원가입 성공");
    }

    @GetMapping("/api/user/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        return ResponseEntity.ok("로그아웃 완료");
    }

    @GetMapping("/api/user/check-email")
    public ResponseEntity<Boolean> checkEmailDuplicate(@RequestParam String email) {

        boolean isDuplicate = memberService.existsByEmail(email);
        // 결과 출력

        return ResponseEntity.ok(isDuplicate);
}
}
