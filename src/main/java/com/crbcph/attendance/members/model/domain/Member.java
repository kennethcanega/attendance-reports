package com.crbcph.attendance.members.model.domain;

import com.crbcph.attendance.component.constant.MemberStatus;
import com.crbcph.attendance.component.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

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

    public String getProfilePicture() {
        if (StringUtils.isEmpty(picture)) {
            return "http://143.198.206.157:8092/crbc-ushering/images/members/anonymous.jpeg";
        }
        return "http://143.198.206.157:8092/crbc-ushering/images/members/" + picture;
    }
}

