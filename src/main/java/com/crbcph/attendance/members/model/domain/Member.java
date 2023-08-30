package com.crbcph.attendance.members.model.domain;

import com.crbcph.attendance.component.constant.MemberStatus;
import com.crbcph.attendance.component.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    private Long id;
    private String name;
    private String nickName;
    private String profilePicture;
    private String remarks;
    private String lastSeen;
    private int attendedServices;
    private int totalServices;
    private MemberStatus memberStatus;
    private Status status;
}

