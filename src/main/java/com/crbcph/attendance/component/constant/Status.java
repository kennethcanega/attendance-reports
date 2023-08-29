package com.crbcph.attendance.component.constant;

public enum Status {
    ACTIVE,
    IN_PROGRESS,
    APPROVED,
    DISAPPROVED,
    INACTIVE;

    public static Status getStatusByText(String value) {
        return Status.valueOf(value);
    }
}
