package com.project.imdang.feign;


import com.project.imdang.feign.configuration.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "memberFeignClient", url = "http://localhost:8080/members", configuration = FeignConfiguration.class)
public interface MemberFeignClient {

    @GetMapping("/info")
    ResponseEntity<MemberInfoResponse> getMemberInfo(@RequestParam UUID memberId);
    @GetMapping
    ResponseEntity<List<MemberInfoResponse>> listMemberInfo(@RequestParam List<UUID> memberIds);
}
