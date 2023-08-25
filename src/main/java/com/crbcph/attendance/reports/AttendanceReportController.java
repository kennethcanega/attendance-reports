package com.crbcph.attendance.reports;

import com.crbcph.attendance.members.model.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reports")
@RequiredArgsConstructor
public class AttendanceReportController {
    private final ApiConfig apiConfig;

    @GetMapping("/members")
    public String hello(Model model) {
        System.out.println(apiConfig.getUrl());
        model.addAttribute("message", "Hello, Shiela Claudz!");
        model.addAttribute("members", generateDummyMembers());
        return "members";
    }

    public static List<Member> generateDummyMembers() {
        List<Member> members = new ArrayList<>();

        // Add members to the list
        members.add(new Member("https://dummyimage.com/243x783", "Randall Weiss", "SUSPENDED", "dmcgrath", "Control trip those we measure truth."));
        members.add(new Member("https://www.lorempixel.com/63/277", "Gabriel Roth", "INACTIVE", "ksalinas", "Involve behind exist military top born."));
        members.add(new Member("https://placeimg.com/734/466/any", "Jimmy Valdez", "INACTIVE", "carpenterwendy", "Republican after history international opportunity feeling."));
        members.add(new Member("https://dummyimage.com/647x646", "Zachary Sims", "ACTIVE", "tpacheco", "Somebody media theory green class when drive manager."));
        members.add(new Member("https://dummyimage.com/225x1003", "Lonnie Walker", "INACTIVE", "tmoore", "Seven why down religious writer quality."));
        return members;
    }

}