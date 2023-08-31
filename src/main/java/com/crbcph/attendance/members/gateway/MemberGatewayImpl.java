package com.crbcph.attendance.members.gateway;

import com.crbcph.attendance.component.config.ApiConfig;
import com.crbcph.attendance.component.http.UsheringClient;
import com.crbcph.attendance.members.model.domain.Member;
import com.crbcph.attendance.members.model.domain.MemberAttendance;
import com.crbcph.attendance.members.model.domain.MemberPageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberGatewayImpl implements MemberGateway {
    private final ApiConfig apiConfig;
    private final UsheringClient usheringClient;

    @Override
    public MemberPageDto<Member> findAll(Map<String, String> keyValues) {
        ResponseEntity<MemberPageDto<Member>> response = usheringClient.getMemberReports(
                apiConfig.getClientId(),
                apiConfig.getClientSecret(),
                Integer.parseInt(keyValues.get("page")),
                keyValues.get("code"),
                keyValues.get("key"),
                keyValues.get("search"),
                keyValues.get("email"),
                Integer.parseInt(keyValues.get("size"))
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        throw new RuntimeException("Unable to connect to API.");
    }

    @Override
    public MemberAttendance findDetails(Map<String, String> keyValues) {
        ResponseEntity<MemberAttendance> response = usheringClient.getMemberAttendance(
                apiConfig.getClientId(),
                apiConfig.getClientSecret(),
                Long.parseLong(keyValues.get("memberId")),
                keyValues.get("code"),
                keyValues.get("key"),
                keyValues.get("email")
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        throw new RuntimeException("Unable to connect to API.");
    }
}
