package com.crbcph.attendance.members.gateway;

import com.crbcph.attendance.component.config.ApiConfig;
import com.crbcph.attendance.component.http.UsheringClient;
import com.crbcph.attendance.members.model.domain.Member;
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
                keyValues.get("search")
        );
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        throw new RuntimeException("Unable to connect to API.");
    }
}
