# 성능 테스트

### 성능 지표
#### 1. 처리량(Throughput)
- 시스템 측면의 성능을 평가하는 KPI

##### # TPS(Transactions per Seconds)
- 단위 시간당 대상 시스템에서 처리되고 있는 요청 건수
- 초당 발생하는 Business Transaction

#### 2. 응답시간(Response Time)
- 사용자 측면에서의 성능을 평가하는 KPI
- 사용자가 해당 업무를 요청한 시점부터 서버로부터 그 결과에 대한 응답을 받아서 사용자 화면에 디스플레이할 때까지 소요된 총 시간
  ```text
  Response Time = Client Time + Network Time + Server Processing + Sending Time
  ```
### 처리량과 응답시간의 상관 관계
- 부하가 증가할수록 (사용자의 요청이 증가할수록) 어느 수준까지는 처리량이 선형적으로 증가한다. 
- 그러나, 어느 시점에 이르면 처리량이 더 이상 증가하지 않고 일정한 수준을 유지하게 되는데, 이 변곡점을 포화점/임계점이라고 한다. 

```text
spring:
    datasource:
        hikari:
            minimum-idle: 10
            maximum-pool-size: 10
            idle-timeout: 30000
            connection-timeout: 20000
```
* minimum-idle
  - 최소 유휴 커넥션 수
  - 초기 설정 시 최소한의 커넥션만 유지
  - TPS가 낮을 때 리소스 절약 가능

* maximum-pool-size
  - 커넥션 풀의 최대 크기
  - TPS가 높아질 때 최대 N개의 커넥션까지 생성해서 요청을 처리

* idle-timeout
  - 지정된 시간(ms)동안 유휴 상태인 커넥션이 있을 경우 풀에서 제거
  - 트래픽이 낮아질 때 자동으로 풀 크기를 줄이는 데 기여

* connection-timeout
  - 커넥션을 얻기 위해 대기하는 최대 시간
  - 해당 시간 내에 커넥션을 확보하지 못하면 예외가 발생


  - 
