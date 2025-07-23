## 일정 관리 어플

---

[1. 개발환경](#1-개발환경)<br>
[2. 디렉터리 구조](#2-디렉터리-구조)<br>
[3. API 명세서](#3-api-명세서)<br>
[4. ERD](#4-ERD)<br>
[5. 아키텍쳐](#5-아키텍쳐)<br>
[6. 관련 내용 정리](#6-관련-내용-정리)<br>
[7. 주요 commit](#7-주요-commit)


### 1. 개발환경

+ JDK Amazon Corretto 17.0.13
+ Spring Framework Boot 3.5.3
+ Gradle 8.14.2
+ Database: H2(개발), MySQL(운영)
+ Spring Boot Starter Web 3.4.22
+ Spring Boot DATA JPA 3.4.22
+ Project Lombok 1.18.32
+ Slf4j 2.0.16
+ Apache Tomcat Embed Socket 10.1.42
+ H2 Database h2:2.3.2322
+ MySQL Connector-j 8.3.0
+ Spring AI 1.0.0

### 2. 디렉터리 구조

![디렉터리 이미지](https://github.com/user-attachments/assets/abfe8dcf-32f0-425b-b866-1ee3a8f43514)

### 3. API 명세서

[API 명세서 노션 링크](https://www.notion.so/API-22a890e2b4ff8089b64ece7b76516f4d?source=copy_link)

### 4. ERD

![ERD]("https://github.com/user-attachments/assets/a67671b6-4077-455c-ac80-7af2fcbc2710")

### 5. 아키텍쳐

### 6. 관련 내용 정리

### 7. 주요 commit

+ [사용자 로그인, 회원가입 기능 구현](https://github.com/Hokirby/dashboard/commit/5158863e42c07977d6c7c700349170fcdc5c4a1a0)<br>
+ [메모 기록, 조회, 수정 삭제 기능 구현](https://github.com/Hokirby/dashboard/commit/1c85dd82071a1fe6abce1ced26c150a345e02c7c)<br>
+ [요약 생성 및 저장, 조회, 재생성(수정), 삭제 기능 구현](https://github.com/Hokirby/dashboard/commit/726dd5c4a4544aa034cd4596fc7dde92c0df3afb)<br>