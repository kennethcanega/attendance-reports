package com.crbcph.attendance.reports;

import com.crbcph.attendance.members.gateway.MemberGateway;
import com.crbcph.attendance.members.model.AttendanceRequest;
import com.crbcph.attendance.members.model.domain.AttendanceReport;
import com.crbcph.attendance.members.model.domain.Member;
import com.crbcph.attendance.members.model.domain.MemberAttendance;
import com.crbcph.attendance.members.model.domain.MemberPageDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
            @RequestParam(name = "date", defaultValue = "") String date,
            @RequestParam(name = "action", defaultValue = "") String action,
            @RequestParam(name = "code") String code,
            @RequestParam(name = "email", defaultValue = "") String email,
            @RequestParam(name = "key") String key,
            @RequestParam(name = "year", defaultValue = "2023") int selectedYear,
            @RequestParam(name = "show_success", defaultValue = "NO") String showSuccess,
            Model model
    ) {
        Map<String, String> keyValues = new HashMap<>();
        keyValues.put("memberId", memberId);
        keyValues.put("code", code);
        keyValues.put("key", key);
        keyValues.put("email", email);
        keyValues.put("date", convertDate(date));
        keyValues.put("year", Integer.toString(selectedYear));
        AttendanceReport report = memberGateway.findDetails(keyValues);
        model.addAttribute("code", code);
        model.addAttribute("key", key);
        model.addAttribute("email", email);
        model.addAttribute("data", report.getReport());
        model.addAttribute("ss", report.isSundaySchool());
        model.addAttribute("am", report.isMorningService());
        model.addAttribute("pm", report.isAfternoonService());
        model.addAttribute("ls", report.isLordsSupper());
        model.addAttribute("date", getDateText(date));
        model.addAttribute("plainDate", date);
        model.addAttribute("selectedYear", selectedYear);
        model.addAttribute("showModal", shouldShowModal(date, action));
        model.addAttribute("isLS", isFirstSunday(date));
        model.addAttribute("memberId", memberId);
        model.addAttribute("showSuccess", getBoolean(showSuccess));
        keyValues.put("email", email);
        return "member_attendance";
    }


    private boolean getBoolean(String value) {
        return StringUtils.equalsIgnoreCase(value, "yes");
    }

    private String convertDate(String inputDate) {
        if (!isValidDate(inputDate)) {
            return "";
        }
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate date = LocalDate.parse(inputDate, inputFormatter);
        return date.format(outputFormatter);
    }

    @PostMapping("/members/attendance")
    public ModelAndView submitAttendance(
            @RequestParam Long memberId,
            @RequestParam String date,
            @RequestParam(required = false, defaultValue = "") String sundaySchoolCheck,
            @RequestParam(required = false, defaultValue = "") String morningServiceCheck,
            @RequestParam(required = false, defaultValue = "") String afternoonServiceCheck,
            @RequestParam(required = false, defaultValue = "") String lordSupperCheck,
            @RequestParam(name = "action", defaultValue = "") String action,
            @RequestParam(name = "code") String code,
            @RequestParam(name = "email", defaultValue = "") String email,
            @RequestParam(name = "key") String key,
            @RequestParam(name = "year", defaultValue = "2023") int selectedYear,
            @RequestParam(name = "show_success", defaultValue = "NO") String showSuccess,
            Model model
    ) {
        model.addAttribute("email", email);
        model.addAttribute("memberId", memberId);
        model.addAttribute("showModal", shouldShowModal(date, action));
        model.addAttribute("isLS", isFirstSunday(date));
        model.addAttribute("selectedYear", selectedYear);
        model.addAttribute("code", code);
        model.addAttribute("key", key);
        model.addAttribute("showSuccess", StringUtils.equals(showSuccess, "YES"));


        memberGateway.submit(
                new AttendanceRequest(
                        memberId,
                        convertDate(date),
                        email,
                        getBoolean(sundaySchoolCheck),
                        getBoolean(morningServiceCheck),
                        getBoolean(afternoonServiceCheck),
                        getBoolean(lordSupperCheck)
                )
        );

        return new ModelAndView(String.format(
                "redirect:/reports/members/%d?key=%s&code=%s&email=%s&action=ATTENDANCE&date=%s&show_success=YES",
                memberId,
                key,
                code,
                email,
                date
        ));
    }

    private boolean shouldShowModal(
            String date,
            String action
    ) {
        return isValidDate(date) && StringUtils.equals(action, "ATTENDANCE");
    }

    private boolean isValidDate(String dateStr) {
        try {
            // Check format
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateStr, dateTimeFormatter);

            // Check if it's Sunday
            return date.getDayOfWeek() == DayOfWeek.SUNDAY;
        } catch (DateTimeParseException e) {
            return false; // Invalid date or format
        }
    }

    private String getDateText(String dateStr) {
        if (isValidDate(dateStr)) {
            LocalDate date = LocalDate.parse(dateStr);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
            return date.format(formatter);
        }
        return "the last Lord's Day?";
    }

    public static boolean isFirstSunday(String dateStr) {
        try {
            // Check format
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateStr, dateTimeFormatter);

            // Check if it's Sunday and the first day of the month
            return date.getDayOfWeek() == DayOfWeek.SUNDAY && date.getDayOfMonth() <= 7;
        } catch (DateTimeParseException e) {
            return false;  // Invalid date or format
        }
    }
}