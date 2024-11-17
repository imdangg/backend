package com.project.imdang.insight.service.domain.handler.insight;

public class UpdateInsightCommandHandler {
    // 교환 완료 시점의 인사이트 내용 유지
    // 교환 후 해당 인사이트가 수정되어도 수정사항 반영 X
    // insight가 수정되면 copy & new Insight
    // insightId - originalId + version                 isLatest        deleted         createdBy
    //      1    -      1           1       (원본)          f              O                user1  <-> user2
    //      2    -      1           2       (수정)          f              O                user1
    //      3    -      1           3       (수정)          t              O                user1
    //      4    -      4           1       (원본)          t              X
    // TODO - CHECK : 가장 최신 버전임을 어떻게 아는가? - isLatest 컬럼 추가

    // exchange
    // ExchangeRequest 엔티티
    // accept/reject

}
