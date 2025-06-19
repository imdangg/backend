# 아파트임당(Imdang)

## 1. 프로젝트 개요 (Overview)
- 임장 후기 공유 플랫폼
[Link]

### # 주요 기능
- 인사이트 등록/수정
- 인사이트 교환(교환 요청/수락/거절)

## 2. 시스템 아키텍처 (System Architecture)
### # 개요
- Hexagonal Architecture 기반 설계 (참고: https://github.com/agelenler/food-ordering-system)
  - 레이어 모듈화
    ```text
    |-- insight-application-service
    |-- insight-application-messaging
    |-- insight-domain
    |- infra
    ```
    - Inbound Adapter (외부 → 시스템)
      - Application Layer
        - API 요청이 들어오는 레이어
        - `@RestController`
      - Messaging Layer
        - 메시지 브로커나 이벤트 시스템과의 연동
        - `@KafkaListener`
        - `@EventListener`
        - `@Scheduled`
    - Application Service : usecase 수행
      - Domain Application Layer
        - Application Service Layer : Use Case 정의, 도메인 로직 호출
        - Domain Core Layer : 도메인 로직을 담당
    - Outbound Adapter (시스템 → 외부)
      - Persistence Layer : 외부 데이터베이스 등과 통신 (Output)
        - `FeignClient`
        - `RestTemplate`
        - `KafkaTemplate`
        - `JpaRepository`
    - dependency : Application/Persistence → Domain Application → Domain Core
  - 역참조 방지
- 주요 기술 스택
  - Backend: Spring Boot, Java 17, JPA, ~~Kafka, Redis,~~ Spring Batch
  - Database: MySQL
  - CI/CD: Github Actions, ~~Docker~~
  - Frontend: AOS, IOS
  - Infrastructure: AWS(EC2, RDS, S3, CodeDeploy)
  - ~~Load Test: Locust~~
    - 오픈 소스 부하 테스트 도구
    - 사용자가 Python으로 시나리오를 작성하여 웹 애플리케이션의 성능을 측정
  - ~~Monitoring~~

## 3. 모듈 구조 및 도메인 모델링
### # backend(rootProject)

### # insight-service
- 인사이트 관련 서비스
- `Insight`
### # member-service
- `Member`
### # setting-service
- 알림 관련 서비스
- `Notification`
### ~~# admin-service~~
### # common
- 각 모듈에서 공통적으로 사용하는 클래스 모음
### # batch
### # infrastructure

## 4. 데이터베이스 설계 (Database Design, ERD)

## 5. 기능 정의 (Feature Specification)

## 6. CI/CD
- 빌드, 서버 배포 자동화
- `.github/workflows/main.yml`
  - `develop` 혹은 `release` 브랜치에 코드 push
  - 프로젝트 빌드
  - AWS S3에 zip으로 업로드
  - AWS CodeDeploy를 통해 EC2에 배포
