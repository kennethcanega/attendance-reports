package com.crbcph.attendance.component.constant;

public enum MemberStatus {
    ACTIVE,
    INACTIVE,
    SUSPENDED,
    TRANSFERRED,
    DISMISSED,
    DIED,
    RESIGNED,
    EXCOMMUNICATED;

    public static MemberStatus convertToEnum(String stringValue) {
        try {
            return MemberStatus.valueOf(stringValue);
        } catch (IllegalArgumentException e) {
            return MemberStatus.INACTIVE;
        }
    }
}
