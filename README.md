# 🎓 학과방 (Gwabang)

대학생을 위한 학과 기반 커뮤니티 플랫폼.  
인가를 이용한 학과별 게시판 접근을 통해 같은 학과 학생들과 소통할 수 있는 서비스입니다.

---

## 📌 프로젝트 소개

- 목표: 학과별 정보 공유 및 커뮤니티 활성화
- 주요 기능:
  - 회원가입 / 로그인 (JWT 기반 인증)
  - 학과 선택 및 인증
  - 학과별 커뮤니티 게시판
  - 글 작성 / 조회 / 상세 보기
  - 개인 정보 조회

---

## 🛠️ 기술 스택

| 구성 | 사용 기술 |
|------|-----------|
| Frontend | React 18, React Router v6, Axios, Tailwind CSS |
| Backend | Spring Boot 3.4.5, Spring Security, JPA, JWT, MySQL |
| 기타 | Vite, ESLint, dotenv, Postman (API 테스트) |

---

## 🗂️ 폴더 구조

![image](https://github.com/user-attachments/assets/c3ec2ca0-fa32-4248-ab5b-2c96037ced5a)


---

## 🔐 인증 & 보안

- JWT 기반 인증
- Access / Refresh 토큰 분리
- 토큰 재발급 엔드포인트 `/api/token`
- CORS 정책 적용 완료

---

## 📄 주요 API

| 기능 | Method | Endpoint |
|------|--------|----------|
| 회원가입 | POST | `/api/user/signup` |
| 로그인 | POST | `/api/auth/login` |
| 토큰 재발급 | POST | `/api/token` |
| 내 정보 조회 | GET | `/api/user/me` |
| 게시글 작성 | POST | `/api/article/{groupCode}` |
| 게시글 목록 조회 | GET | `/api/article/{groupCode}` |
| 게시글 상세 조회 | GET | `/api/article/{groupCode}/{id}` |

---

## 📷 화면 예시

> 로그인 화면, 사이드바 레이아웃, 학과 게시판, 글 상세 페이지 등은 첨부된 캡처 이미지를 참고하세요.

---

## 💡 기여 및 협업 방식

- 커밋 메시지 컨벤션: `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`
- 브랜치 전략: `main`, `dev`, `feature/*`

---
