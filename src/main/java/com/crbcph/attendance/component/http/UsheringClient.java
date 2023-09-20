package com.crbcph.attendance.component.http;

import com.crbcph.attendance.members.model.AttendanceRequest;
import com.crbcph.attendance.members.model.AttendanceResponseDto;
import com.crbcph.attendance.members.model.domain.AttendanceReport;
import com.crbcph.attendance.members.model.domain.Member;
import com.crbcph.attendance.members.model.domain.MemberAttendance;
import com.crbcph.attendance.members.model.domain.MemberPageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/members/reports/{memberId}")
    ResponseEntity<AttendanceReport> getMemberAttendance(
            @RequestHeader("X-Client-ID") String clientId,
            @RequestHeader("X-Client-Secret") String clientSecret,
            @PathVariable Long memberId,
            @RequestParam("access_code") String code,
            @RequestParam("access_key") String key,
            @RequestParam("email") String email,
            @RequestParam("year") String year,
            @RequestParam("date") String date
    );

    @PostMapping("/attendance")
    ResponseEntity<AttendanceResponseDto> submit(
            @RequestHeader("X-Client-ID") String clientId,
            @RequestHeader("X-Client-Secret") String clientSecret,
            @RequestBody AttendanceRequest request
    );
}
