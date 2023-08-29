package com.crbcph.attendance.reports;

import com.crbcph.attendance.component.config.ApiConfig;
import com.crbcph.attendance.members.gateway.MemberGateway;
import com.crbcph.attendance.members.model.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reports")
@RequiredArgsConstructor
public class AttendanceReportController {
    private final MemberGateway memberGateway;

    @GetMapping("/members")
    public String viewReports(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "key") String key,
            @RequestParam(name = "page") int page,
            Model model
    ) {
        Map<String, String> keyValues = new HashMap<>();
        keyValues.put("code", code);
        keyValues.put("key", key);
        keyValues.put("page", Integer.toString(page));
        model.addAttribute("members", memberGateway.findAll(keyValues));
        return "members";
    }

//    public static List<Member> generateDummyMembers() {
//        List<Member> members = new ArrayList<>();
//
//        // Add members to the list
//        members.add(new Member("https://dummyimage.com/243x783", "Randall Weiss", "SUSPENDED", "dmcgrath", "Control trip those we measure truth."));
//        members.add(new Member("https://www.lorempixel.com/63/277", "Gabriel Roth", "INACTIVE", "ksalinas", "Involve behind exist military top born."));
//        members.add(new Member("https://placeimg.com/734/466/any", "Jimmy Valdez", "INACTIVE", "carpenterwendy", "Republican after history international opportunity feeling."));
//        members.add(new Member("https://dummyimage.com/647x646", "Zachary Sims", "ACTIVE", "tpacheco", "Somebody media theory green class when drive manager."));
//        members.add(new Member("https://dummyimage.com/225x1003", "Lonnie Walker", "INACTIVE", "tmoore", "Seven why down religious writer quality."));
//        return members;
//    }

}