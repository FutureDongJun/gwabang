# 🎓 학과방 (Gwabang)

## 대학생을 위한 학과 기반 커뮤니티 플랫폼

전국의 동일 학과 대학생들이 함께 소통하고 협력할 수 있는 커뮤니티 플랫폼입니다.
학교 인증을 통해 학과별 게시판에 접근할 수 있어, 같은 전공을 가진 학생들끼리 깊이 있는 정보 공유와 네트워킹이 가능합니다.

기존의 ‘학교 내 커뮤니티’와 달리, 이 플랫폼은 전국 단위의 학과 중심 커뮤니티를 지향하며 다음과 같은 활동이 가능합니다:

- 공모전/대외활동 정보 공유 및 팀원 모집

- 다인원 협업 프로젝트 진행

- 인턴십 및 진로 관련 정보 교류

- 대학원 진학 및 학과 공부 관련 자료 공유

같은 길을 걷는 동료들과 함께, 더 넓은 학과 커뮤니티를 경험해보세요!

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

| 구성     | 사용 기술                                           |
| -------- | --------------------------------------------------- |
| Frontend | React 19, React Router v7, Axios, Tailwind CSS      |
| Backend  | Spring Boot 3.4.5, Spring Security, JPA, JWT, MySQL |
| 기타     | Vite, ESLint, dotenv, Postman (API 테스트)          |

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

| 기능             | Method | Endpoint                        |
| ---------------- | ------ | ------------------------------- |
| 회원가입         | POST   | `/api/user/signup`              |
| 로그인           | POST   | `/api/auth/login`               |
| 토큰 재발급      | POST   | `/api/token`                    |
| 내 정보 조회     | GET    | `/api/user/me`                  |
| 게시글 작성      | POST   | `/api/article/{groupCode}`      |
| 게시글 목록 조회 | GET    | `/api/article/{groupCode}`      |
| 게시글 상세 조회 | GET    | `/api/article/{groupCode}/{id}` |

---

## 📷 화면 예시

> 로그인 화면, 사이드바 레이아웃, 학과 게시판, 글 상세 페이지 등은 첨부된 캡처 이미지를 참고하세요.

---

## 💡 기여 및 협업 방식

- 커밋 메시지 컨벤션: `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`
- 브랜치 전략: `main`, `dev`, `feature/*`

---
