package com.crbcph.attendance.members.gateway;

import com.crbcph.attendance.members.model.AttendanceRequest;
import com.crbcph.attendance.members.model.domain.AttendanceReport;
import com.crbcph.attendance.members.model.domain.Member;
import com.crbcph.attendance.members.model.domain.MemberPageDto;

import java.util.Map;

public interface MemberGateway {
    MemberPageDto<Member> findAll(
            Map<String, String> keyValues
    );

    AttendanceReport findDetails(
            Map<String, String> keyValues
    );

    void submit(
            AttendanceRequest request
    );
}
