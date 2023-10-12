package ru.egartech.tmsystem.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EmployeePrivilegesBits {
    public int BIT_LATE_COUNT = 0x1;
    public int BIT_EARLY_LIVING_COUNT = 0x2;
    public int BIT_ABSENCE = 0x4;
    public int BIT_SKIP = 0x8;
    public int BIT_REST_TIME = 0x10;
    public int  BIT_DISTRACTION_TIME = 0x20;
}
