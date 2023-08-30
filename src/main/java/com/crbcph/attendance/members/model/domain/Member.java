package com.crbcph.attendance.members.model.domain;

import com.crbcph.attendance.component.constant.MemberStatus;
import com.crbcph.attendance.component.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Long id;
    private String name;
    private String nickName;
    private String picture;
    private String remarks;
    private MemberStatus memberStatus;
    private Status status;
    private MemberReport report;

    public String getAttendedServices() {
        return report.getService().getAttended() + " / " + report.getService().getTotal();
    }

    public String getProfilePicture() {
        if (StringUtils.isEmpty(picture)) {
            return "http://143.198.206.157:8092/crbc-ushering/images/members/anonymous.jpeg";
        }
        return "http://143.198.206.157:8092/crbc-ushering/images/members/" + picture;
    }

    public String getBadgeColor() {
        switch (memberStatus.toString()) {
            case "ACTIVE":
                return "badge-success";
            case "SUSPENDED":
                return "badge-warning";
            case "EXCOMMUNICATED":
                return "badge-danger";
        }
        return "badge-secondary";
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class MemberReport {
        private String membershipDate;
        private String membershipDateText;
        private ReportSummary sundaySchool;
        private ReportSummary morningService;
        private ReportSummary afternoonService;
        private ReportSummary lordSupper;
        private ReportSummary lordsDay;
        private ReportSummary service;
        private HeatMapDto heatMap;
        private String systemCreationDate;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class ReportSummary {
            private int total;
            private int attended;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class HeatMapDto {
            private List<Integer> years;
            private List<HeatMapDateDto> dates;
        }

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public static class HeatMapDateDto {
            private String date;
            private boolean sundaySchool;
            private boolean morningService;
            private boolean afternoonService;
            private boolean lordSupper;
            private int count;
            private boolean isLordSupperDate;
        }
    }

}

