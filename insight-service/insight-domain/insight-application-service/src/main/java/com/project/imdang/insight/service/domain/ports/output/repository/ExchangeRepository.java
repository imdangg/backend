package com.project.imdang.insight.service.domain.ports.output.repository;

import com.project.imdang.domain.valueobject.ExchangeId;
import com.project.imdang.insight.service.domain.entity.Exchange;

public interface ExchangeRepository {

    Exchange save(Exchange exchange);
//    Exchange update(Exchange exchange);
    // TODO - CHECK : 교환요청 삭제
    void deleteById(ExchangeId exchangeId);

//    Optional<Exchange> findById(ExchangeId exchangeId);
//    List<Exchange> findAll();
}
