# 프로젝트 사양서 (PROJECT_SPEC.md)

이 문서는 `vibe-coding-fullstack` (VibeApp) 프로젝트의 최신 기술 스택, 설정 및 의존성을 설명합니다.

## 1. 프로젝트 개요
- **프로젝트 명**: VibeApp (vibeapp)
- **그룹 (Group)**: com.example
- **버전**: 0.0.1-SNAPSHOT
- **패키지 구조**: 기능 중심의 계층형 구조 (Feature-based Package Structure)
    - `com.example.vibeapp` : 메인 애플리케이션 클래스
    - `com.example.vibeapp.home` : 홈 화면 관련 로직
    - `com.example.vibeapp.post` : 게시판 기능(엔티티, 서비스, 리포지토리, 컨트롤러)

## 2. 기술 스택
- **언어**: Java 25
- **프레임워크**: Spring Boot 4.0.1, Thymeleaf (뷰 엔진)
- **스타일링**: Tailwind CSS (주요 디자인), Bootstrap 5 (보조) - **Samsung Galaxy Aesthetic** 적용
- **빌드 도구**: Gradle 9.1
- **의존성 관리**: io.spring.dependency-management 1.1.7

## 3. 프로젝트 설정
### 3.1 Gradle 설정 (`build.gradle`)
- **Java 툴체인**: languageVersion = 25
- **저장소**: MavenCentral

### 3.2 애플리케이션 설정 (`src/main/resources/application.yml`)
- **애플리케이션 이름**: vibeapp
- **서버 포트**: 8080

## 4. 구현된 기능 (Board System)
### 4.1 Home Page
- **엔드포인트**: `GET /`
- **컨트롤러**: `com.example.vibeapp.home.HomeController`
- **뷰 템플릿**: `templates/home/home.html`

### 4.2 Post List (Pagination)
- **엔드포인트**: `GET /posts?page={n}`
- **기능**: 게시글 목록 최신순 정렬 및 페이징 처리 (페이지당 5개)
- **컨트롤러**: `getPostList` 메서드
- **뷰 템플릿**: `templates/post/posts.html`

### 4.3 Post Detail
- **엔드포인트**: `GET /posts/{no}`
- **기능**: 게시글 상세 조회 및 조회수 증가
- **컨트롤러**: `getPostDetail` 메서드
- **뷰 템플릿**: `templates/post/post_detail.html`

### 4.4 Post Management (CRUD)
- **등록**: `GET /posts/new` (폼), `POST /posts/add` (처리)
- **수정**: `GET /posts/{no}/edit` (폼), `POST /posts/{no}/save` (처리)
- **삭제**: `GET /posts/{no}/delete`
- **리포지토리**: `PostRepository` (In-Memory ArrayList 기반)

## 5. 주요 의존성
- `spring-boot-starter-web`: 웹 개발 스타터
- `spring-boot-starter-thymeleaf`: Thymeleaf 템플릿 엔진
- `spring-boot-starter-test`: 테스트 스타터
- **Tailwind CSS (CDN)**: 주요 UI 프레임워크

## 6. 개발 환경 및 규칙
- **Java 버전**: 25.0.2 이상 필요한
- **실행**: `./gradlew bootRun`
- **Git 커밋 규준**: Conventional Commits (feat, fix, refactor 등)
- **명명 규칙**: 실무 관계에 따른 명확한 메서드 명명 (`getPostList`, `registerPost` 등)
