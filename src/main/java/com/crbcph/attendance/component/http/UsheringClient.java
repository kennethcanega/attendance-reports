package com.crbcph.attendance.component.http;

import com.crbcph.attendance.members.model.domain.Member;
import com.crbcph.attendance.members.model.domain.MemberPageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ushering", url = "http://143.198.206.157:8092/crbc-ushering")
public interface UsheringClient {
    @GetMapping("/members/reports")
    ResponseEntity<MemberPageDto<Member>> getMemberReports(
            @RequestHeader("X-Client-ID") String clientId,
            @RequestHeader("X-Client-Secret") String clientSecret,
            @RequestParam("page") int page,
            @RequestParam("access_code") String code,
            @RequestParam("access_key") String key,
            @RequestParam("search") String search,
            @RequestParam("email") String email,
            @RequestParam("size") int size
    );
}
