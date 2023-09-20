package com.crbcph.attendance.members.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceRequest {
    @JsonProperty("member_id")
    private Long memberId;
    private String date;
    @JsonProperty("requester_email")
    private String requesterEmail;
    @JsonProperty("sunday_school")
    private boolean sundaySchool;
    @JsonProperty("morning_service")
    private boolean morningService;
    @JsonProperty("afternoon_service")
    private boolean afternoonService;
    @JsonProperty("lord_supper")
    private boolean lordSupper;
}
