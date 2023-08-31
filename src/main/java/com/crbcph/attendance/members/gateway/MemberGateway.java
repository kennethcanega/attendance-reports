package com.crbcph.attendance.members.gateway;

import com.crbcph.attendance.members.model.domain.Member;
import com.crbcph.attendance.members.model.domain.MemberAttendance;
import com.crbcph.attendance.members.model.domain.MemberPageDto;

import java.util.Map;

public interface MemberGateway {
    MemberPageDto<Member> findAll(
            Map<String, String> keyValues
    );

    MemberAttendance findDetails(
            Map<String, String> keyValues
    );
}
