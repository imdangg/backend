package com.project.imdang.member.domain.client;


import com.project.imdang.member.domain.client.MemberData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "memberFeignClient", url = "http://localhost:8080/members")
public interface MemberFeignClient {

    @GetMapping("/info")
    ResponseEntity<MemberData> getMemberData(@RequestParam("memberId") UUID memberId);

    @GetMapping
    ResponseEntity<List<MemberData>> listMemberData(@RequestParam("memberIds") List<UUID> memberIds);
}
