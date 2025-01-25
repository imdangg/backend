package com.project.imdang.insight.service.domain.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "insightFeignClient", url = "http://localhost:8080/members")
public interface MemberFeignClient {

    @GetMapping
    MemberInfoResponse getMemberInfo(@RequestParam UUID memberId);
}
