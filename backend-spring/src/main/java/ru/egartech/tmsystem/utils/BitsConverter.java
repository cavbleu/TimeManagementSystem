package ru.egartech.tmsystem.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.BooleanUtils;
import ru.egartech.tmsystem.model.dto.EditEmployeeDto;
import ru.egartech.tmsystem.model.enumeration.EmployeePrivilegesEnum;

import java.util.ArrayList;
import java.util.List;

import static ru.egartech.tmsystem.model.enumeration.EmployeePrivilegesEnum.*;
import static ru.egartech.tmsystem.utils.EmployeePrivilegesBits.*;

@UtilityClass
public class BitsConverter {

    public static List<String> getEmployeePrivileges(long privilegesBit) {
        List<String> employeePrivileges = new ArrayList<>();
        if ((privilegesBit & BIT_LATE_COUNT) != 0) {
            employeePrivileges.add(LATE_COUNT.getName());
        }
        if ((privilegesBit & BIT_EARLY_LIVING_COUNT) != 0) {
            employeePrivileges.add(EARLY_LIVING_COUNT.getName());
        }
        if ((privilegesBit & BIT_ABSENCE) != 0) {
            employeePrivileges.add(ABSENCE.getName());
        }
        if ((privilegesBit & BIT_SKIP) != 0) {
            employeePrivileges.add(SKIP.getName());
        }
        if ((privilegesBit & BIT_REST_TIME) != 0) {
            employeePrivileges.add(REST_TIME.getName());
        }
        if ((privilegesBit & BIT_DISTRACTION_TIME) != 0) {
            employeePrivileges.add(DISTRACTION_TIME.getName());
        }

        return employeePrivileges;
    }

    public static long getEmployeePrivilegesNumber(List<String> employeePrivileges) {
        if (employeePrivileges == null) {
            return 0;
        }
        if (employeePrivileges.isEmpty()) {
            return 0;
        }
        StringBuilder privilegesNumber = new StringBuilder();
        for (EmployeePrivilegesEnum value : values()) {
            boolean isPresent = employeePrivileges.contains(value.getName());
            privilegesNumber.insert(0, BooleanUtils.toInteger(isPresent));
        }
        return Integer.parseInt(privilegesNumber.toString(), Character.MIN_RADIX);
    }

    public static void setEmployeePrivileges(EditEmployeeDto editEmployeeDto) {
        Long privilegesBit = editEmployeeDto.getPrivilegesNumber();
        if ((privilegesBit & BIT_LATE_COUNT) != 0) {
            editEmployeeDto.setLateIncreased(true);
        }
        if ((privilegesBit & BIT_EARLY_LIVING_COUNT) != 0) {
            editEmployeeDto.setEarlyLeavingIncreased(true);
        }
        if ((privilegesBit & BIT_ABSENCE) != 0) {
            editEmployeeDto.setAbsenceIncreased(true);
        }
        if ((privilegesBit & BIT_SKIP) != 0) {
            editEmployeeDto.setSkipIncreased(true);
        }
        if ((privilegesBit & BIT_REST_TIME) != 0) {
            editEmployeeDto.setRestTimeIncreased(true);
        }
        if ((privilegesBit & BIT_DISTRACTION_TIME) != 0) {
            editEmployeeDto.setDistractionTimeIncreased(true);
        }
    }

    public static List<String> getEmployeePrivilegesList(EditEmployeeDto editEmployeeDto) {
        List<String> privileges = new ArrayList<>();
        if(editEmployeeDto.isLateIncreased()){
            privileges.add(LATE_COUNT.getName());
        }
        if(editEmployeeDto.isEarlyLeavingIncreased()){
            privileges.add(EARLY_LIVING_COUNT.getName());
        }
        if(editEmployeeDto.isAbsenceIncreased()){
            privileges.add(ABSENCE.getName());
        }
        if(editEmployeeDto.isSkipIncreased()){
            privileges.add(SKIP.getName());
        }
        if(editEmployeeDto.isRestTimeIncreased()){
            privileges.add(REST_TIME.getName());
        }
        if(editEmployeeDto.isDistractionTimeIncreased()){
            privileges.add(DISTRACTION_TIME.getName());
        }
        return privileges;
    }

}
