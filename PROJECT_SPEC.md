# 프로젝트 사양서 (PROJECT_SPEC.md)

이 문서는 `vibe-coding-fullstack` (VibeApp) 프로젝트의 최신 기술 스택, 설정 및 의존성을 설명합니다.

## 1. 프로젝트 개요
- **프로젝트 명**: VibeApp (vibeapp)
- **그룹 (Group)**: vibeapp
- **버전**: 0.0.1-SNAPSHOT
- **패키지 구조**: `src/main/java/vibeapp` (단일 패키지 구조)

## 2. 기술 스택
- **언어**: Java 25
- **프레임워크**: Spring Boot 4.0.1, Thymeleaf (뷰 엔진), Bootstrap 5 (CSS 프레임워크)
- **빌드 도구**: Gradle 9.1
- **의존성 관리**: io.spring.dependency-management 1.1.7

## 3. 프로젝트 설정
### 3.1 Gradle 설정 (`build.gradle`)
- **Java 툴체인**: languageVersion = 25
- **저장소**: MavenCentral
- **환경**: Gradle 9.1 Wrapper 기반

### 3.2 애플리케이션 설정 (`src/main/resources/application.yml`)
- **애플리케이션 이름**: vibeapp
- **서버 포트**: 8080

## 4. 구현된 기능 (API)
### 4.1 Hello API
- **엔드포인트**: `GET /api/hello`
- **기능**: "Hello, Vibe!" 문자열 반환
- **테스트**: `src/test/java/vibeapp/VibeAppTest.java`에서 검증됨

### 4.2 Home Page
- **엔드포인트**: `GET /`
- **컨트롤러**: `vibeapp.HomeController`
- **뷰 템플릿**: `src/main/resources/templates/home.html` (삼성 스타일 리뉴얼 디자인)

### 4.3 Post List (In-Memory)
- **엔드포인트**: `GET /posts`
- **컨트롤러**: `vibeapp.PostController`
- **서비스**: `vibeapp.PostService`
- **리포지토리**: `vibeapp.PostRepository` (ArrayList 기반)
- **뷰 템플릿**: `src/main/resources/templates/posts.html`
- **항목**: 번호, 제목, 생성일, 조회수

## 5. 주요 의존성
- `spring-boot-starter-web`: 웹 애플리케이션 개발을 위한 스타터
- `spring-boot-starter-thymeleaf`: Thymeleaf 템플릿 엔진 스타터
- `spring-boot-starter-test`: 테스트를 위한 스타터 (JUnit 5 포함)
- `junit-platform-launcher`: JUnit 플랫폼 런처
- **Bootstrap 5 (CDN)**: `https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/...` (CSS/JS)

## 6. 개발 환경 설정
1. **JDK 25** 설치 확인
2. Gradle을 사용하여 프로젝트 빌드/테스트: `./gradlew build`
3. 애플리케이션 실행: `./gradlew bootRun`

## 7. 프로젝트 규칙
- **Git 커밋 형식**: `git-message-format.md` 파일의 Conventional Commits 규준 지침을 따름
- **언어 규칙**: 모든 응답 및 기술 문서는 한국어를 기본으로 함
