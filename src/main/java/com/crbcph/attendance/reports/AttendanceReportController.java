package com.crbcph.attendance.reports;

import com.crbcph.attendance.members.gateway.MemberGateway;
import com.crbcph.attendance.members.model.domain.Member;
import com.crbcph.attendance.members.model.domain.MemberAttendance;
import com.crbcph.attendance.members.model.domain.MemberPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/reports")
@RequiredArgsConstructor
public class AttendanceReportController {
    private final MemberGateway memberGateway;

    @GetMapping("/members")
    public String viewReports(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "email", defaultValue = "") String email,
            @RequestParam(name = "key") String key,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            Model model
    ) {
        Map<String, String> keyValues = new HashMap<>();
        keyValues.put("code", code);
        keyValues.put("key", key);
        keyValues.put("search", search);
        keyValues.put("email", email);
        keyValues.put("size", Integer.toString(size));
        keyValues.put("page", Integer.toString(page));
        MemberPageDto<Member> data = memberGateway.findAll(keyValues);
        model.addAttribute("page", data);
        model.addAttribute("search", search);
        model.addAttribute("currentPage", data.getCurrentPage());
        model.addAttribute("totalPages", data.getTotalPages());
        model.addAttribute("code", code);
        model.addAttribute("key", key);
        model.addAttribute("email", email);
        keyValues.put("email", email);
        model.addAttribute("displayPages", data.getDisplayPages());
        return "members";
    }

    @GetMapping("/members/{memberId}")
    public String viewReports(
            @PathVariable String memberId,
            @RequestParam(name = "code") String code,
            @RequestParam(name = "search", defaultValue = "") String search,
            @RequestParam(name = "email", defaultValue = "") String email,
            @RequestParam(name = "key") String key,
            @RequestParam(name = "year", defaultValue = "2023") int selectedYear,
            Model model
    ) {
        Map<String, String> keyValues = new HashMap<>();
        keyValues.put("memberId", memberId);
        keyValues.put("code", code);
        keyValues.put("key", key);
        keyValues.put("search", search);
        keyValues.put("email", email);
        MemberAttendance data = memberGateway.findDetails(keyValues);
        model.addAttribute("code", code);
        model.addAttribute("key", key);
        model.addAttribute("email", email);
        model.addAttribute("data", data);
        model.addAttribute("selectedYear", selectedYear);
        keyValues.put("email", email);
        return "member_attendance";
    }
}