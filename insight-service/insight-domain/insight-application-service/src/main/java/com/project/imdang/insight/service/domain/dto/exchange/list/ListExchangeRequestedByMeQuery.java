package com.project.imdang.insight.service.domain.dto.exchange.list;

import com.project.imdang.insight.service.domain.valueobject.ExchangeRequestStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;


@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ListExchangeRequestedByMeQuery {
    // TODO - PagingQuery 클래스 이동
//        extends PagingQuery {

    @Setter
    @Schema(description = "요청한 사용자 ID")
    private UUID requestMemberId;
    // 대기, 거절, 완료
    @Schema(description = "교환 요청 상태")
    @NotNull
    private ExchangeRequestStatus exchangeRequestStatus;

    private Integer pageNumber;
    private Integer pageSize;
    private String direction;
    private String[] properties;
}
