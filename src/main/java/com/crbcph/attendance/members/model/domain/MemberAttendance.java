package com.crbcph.attendance.members.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberAttendance {
    private String name;
    private String nickName;
    private String picture;
    private String remarks;
    private String membershipDate;
    private String membershipDateText;
    private ServiceAttendance sundaySchool;
    private ServiceAttendance morningService;
    private ServiceAttendance afternoonService;
    private ServiceAttendance lordSupper;
    private ServiceAttendance lordsDay;
    private ServiceAttendance service;
    private HeatMap heatMap;
    private String systemCreationDate;


    public boolean showNickName() {
        return !StringUtils.isEmpty(nickName);
    }

    public boolean showRemarks() {
        return !StringUtils.isEmpty(remarks);
    }

    public String attendancePercentage() {
        double percentage = ((double) lordsDay.getAttended() / (double) lordsDay.getTotal()) * 100d;
        return String.format("%.2f", percentage) + "%";
    }

    public String getProfilePicture() {
        if (StringUtils.isEmpty(picture)) {
            return "http://143.198.206.157:8092/crbc-ushering/images/members/anonymous.jpeg";
        }
        return "http://143.198.206.157:8092/crbc-ushering/images/members/" + picture;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ServiceAttendance {
    private int total;
    private int attended;

    public double percent() {
        return ((double) attended / (double) total) * 100;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class HeatMap {
    private List<Integer> years;
    private List<DateAttendance> dates;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class DateAttendance {
    private String date;
    private boolean sundaySchool;
    private boolean morningService;
    private boolean afternoonService;
    private boolean lordSupper;
    private int count;
    private boolean lordSupperDate;

    public String getCssClass() {
        switch(count) {
            case 0:
                return "gray";
            case 1:
                return "very-light-green";
            case 2:
                return "light-green";
            case 3:
                return "green";
            default:
                return "dark-green";
        }
    }
}