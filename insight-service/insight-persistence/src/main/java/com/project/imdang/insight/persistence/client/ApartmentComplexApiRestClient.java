package com.project.imdang.insight.persistence.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange
@Component
public interface ApartmentComplexApiRestClient {

    @GetExchange("/AptIdInfoSvc/v1/getAptInfo")
    ApartmentComplexApiResult getApartmentInfoList(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(name = "perPage", defaultValue = "10") Integer perPage,
                                                   @RequestParam(name = "serviceKey") String serviceKey,
                                                   @RequestParam(name = "cond[ADRES::LIKE]") String address);
}
