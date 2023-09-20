package com.crbcph.attendance.members.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceReport {
    private boolean sundaySchool;
    private boolean morningService;
    private boolean afternoonService;
    private boolean lordsSupper;
    private MemberAttendance report;
}
