package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.*;
import ru.egartech.tmsystem.model.entity.Distraction;
import ru.egartech.tmsystem.model.entity.Employee;
import ru.egartech.tmsystem.model.entity.Rest;
import ru.egartech.tmsystem.model.entity.TimeSheet;
import ru.egartech.tmsystem.utils.BitsConverter;
import ru.egartech.tmsystem.utils.SummaryFormatter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper extends MapHelper {

    public Employee toEntity(EditEmployeeDto dto) {
        if (dto == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setAge(dto.getAge());
        employee.setPosition(positionDtoToPosition(dto.getPosition()));
        employee.setPrivilegesNumber(BitsConverter.getEmployeePrivilegesNumber(BitsConverter.getEmployeePrivilegesList(dto)));
        return employee;
    }

    public EmployeeDto toDto(EditEmployeeDto dto) {
        if (dto == null) {
            return null;
        }
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(dto.getId());
        employeeDto.setName(dto.getName());
        employeeDto.setAge(dto.getAge());
        employeeDto.setPosition(dto.getPosition());
        employeeDto.setPrivilegesNumber(BitsConverter.getEmployeePrivilegesNumber(BitsConverter.getEmployeePrivilegesList(dto)));
        return employeeDto;
    }

    public EmployeeSummaryDto toEmployeeSummaryDto(EmployeeDto dto, SettingsDto settingsDto, LocalDate startDate, LocalDate endDate,
                                                   Long workTime, Long distractionTime, Long restTime) {
        if (dto == null) {
            return null;
        }
        EmployeeSummaryDto employeeSummaryDto = new EmployeeSummaryDto();
        employeeSummaryDto.setId(dto.getId());
        employeeSummaryDto.setAge(dto.getAge());
        SummaryFormatter.toSummaryDto(workTime, distractionTime, restTime,
                employeeSummaryDto, dto, startDate, endDate, settingsDto);
        employeeSummaryDto.setEmployeeName(dto.getName());
        employeeSummaryDto.setPositionName(dto.getPosition().getName());
        employeeSummaryDto.setDepartmentName(dto.getPosition().getDepartment().getName());
        employeeSummaryDto.setPrivileges(String.join("; ", dto.getPrivileges()));
        return employeeSummaryDto;
    }


    public Employee toEntity(EmployeeDto dto) {
        if (dto == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setAge(dto.getAge());
        employee.setPrivilegesNumber(dto.getPrivilegesNumber());
        employee.setPosition(positionDtoToPosition(dto.getPosition()));
        employee.setTimeSheets(timeSheetDtoListToTimeSheetList(dto.getTimeSheets()));
        employee.setRests(restDtoListToRestList(dto.getRests()));
        employee.setDistractions(distractionDtoListToDistractionList(dto.getDistractions()));
        return employee;
    }

    public EmployeeDto toDto(Employee entity) {
        if (entity == null) {
            return null;
        }
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(entity.getId());
        employeeDto.setName(entity.getName());
        employeeDto.setAge(entity.getAge());
        employeeDto.setPrivilegesNumber(entity.getPrivilegesNumber());
        employeeDto.setPosition(positionToPositionDto(entity.getPosition()));
        employeeDto.setTimeSheets(timeSheetListToTimeSheetDtoList(entity.getTimeSheets()));
        employeeDto.setRests(restListToRestDtoList(entity.getRests()));
        employeeDto.setDistractions(distractionListToDistractionDtoList(entity.getDistractions()));
        if (entity.getPrivilegesNumber() != null) {
            employeeDto.setPrivileges(String.join("; ", BitsConverter.getEmployeePrivileges(entity.getPrivilegesNumber())));
        }
        return employeeDto;
    }

    public EmployeeDto toEmployeeDtoByPeriod(EmployeeDto dto,
                                             List<TimeSheetDto> timeSheetDtoList,
                                             List<RestDto> restDtoList,
                                             List<DistractionDto> distractionDtoList) {
        if (dto == null) {
            return null;
        }
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(dto.getId());
        employeeDto.setName(dto.getName());
        employeeDto.setAge(dto.getAge());
        employeeDto.setPosition(dto.getPosition());
        employeeDto.setPrivilegesNumber(dto.getPrivilegesNumber());
        employeeDto.setPosition(dto.getPosition());
        employeeDto.setRests(restDtoList);
        employeeDto.setDistractions(distractionDtoList);
        employeeDto.setTimeSheets(timeSheetDtoList);
        return employeeDto;
    }

    public EditEmployeeDto toEditEmployeeDto(EmployeeDto dto) {
        if (dto == null) {
            return null;
        }
        EditEmployeeDto editEmployeeDto = new EditEmployeeDto();
        editEmployeeDto.setId(dto.getId());
        editEmployeeDto.setName(dto.getName());
        editEmployeeDto.setAge(dto.getAge());
        editEmployeeDto.setPosition(dto.getPosition());
        editEmployeeDto.setPrivilegesNumber(dto.getPrivilegesNumber());
        BitsConverter.setEmployeePrivileges(editEmployeeDto);
        return editEmployeeDto;
    }

    protected TimeSheet timeSheetDtoToTimeSheet(TimeSheetDto timeSheetDto) {

        TimeSheet timeSheet = new TimeSheet();

        timeSheet.setId(timeSheetDto.getId());
        timeSheet.setDate(timeSheetDto.getDate());
        timeSheet.setAbsenceReason(timeSheetDto.getAbsenceReason());
        timeSheet.setStartWork(timeSheetDto.getStartWork());
        timeSheet.setEndWork(timeSheetDto.getEndWork());
        timeSheet.setWorkTime(timeSheetDto.getWorkTime());

        return timeSheet;
    }

    protected List<TimeSheet> timeSheetDtoListToTimeSheetList(List<TimeSheetDto> list) {
        if (list == null) {
            return null;
        }

        List<TimeSheet> list1 = new ArrayList<>(list.size());
        for (TimeSheetDto timeSheetDto : list) {
            if (timeSheetDto != null) {
                list1.add(timeSheetDtoToTimeSheet(timeSheetDto));
            }
        }
        return list1;
    }

    protected Rest restDtoToRest(RestDto restDto) {
        Rest rest = new Rest();
        rest.setId(restDto.getId());
        rest.setDate(restDto.getDate());
        rest.setStartRest(restDto.getStartRest());
        rest.setEndRest(restDto.getEndRest());
        rest.setRestTime(restDto.getRestTime());
        return rest;
    }

    protected List<Rest> restDtoListToRestList(List<RestDto> list) {
        if (list == null) {
            return null;
        }
        List<Rest> list1 = new ArrayList<>(list.size());
        for (RestDto restDto : list) {
            if (restDto != null) {
                list1.add(restDtoToRest(restDto));
            }
        }
        return list1;
    }

    protected Distraction distractionDtoToDistraction(DistractionDto distractionDto) {

        Distraction distraction = new Distraction();
        distraction.setId(distractionDto.getId());
        distraction.setDate(distractionDto.getDate());
        distraction.setStartDistraction(distractionDto.getStartDistraction());
        distraction.setEndDistraction(distractionDto.getEndDistraction());
        distraction.setDistractionTime(distractionDto.getDistractionTime());
        return distraction;
    }

    protected List<Distraction> distractionDtoListToDistractionList(List<DistractionDto> list) {
        if (list == null) {
            return null;
        }
        List<Distraction> list1 = new ArrayList<>(list.size());
        for (DistractionDto distractionDto : list) {
            if (distractionDto != null) {
                list1.add(distractionDtoToDistraction(distractionDto));
            }
        }
        return list1;
    }

    protected List<TimeSheetDto> timeSheetListToTimeSheetDtoList(List<TimeSheet> list) {
        if (list == null) {
            return null;
        }
        List<TimeSheetDto> list1 = new ArrayList<>(list.size());
        for (TimeSheet timeSheet : list) {
            if (timeSheet != null) {
                list1.add(timeSheetToTimeSheetDto(timeSheet));
            }
        }
        return list1;
    }

    protected List<RestDto> restListToRestDtoList(List<Rest> list) {
        if (list == null) {
            return null;
        }
        List<RestDto> list1 = new ArrayList<>(list.size());
        for (Rest rest : list) {
            if (rest != null) {
                list1.add(restToRestDto(rest));
            }
        }
        return list1;
    }

    protected List<DistractionDto> distractionListToDistractionDtoList(List<Distraction> list) {
        if (list == null) {
            return null;
        }
        List<DistractionDto> list1 = new ArrayList<>(list.size());
        for (Distraction distraction : list) {
            if (distraction != null) {
                list1.add(distractionToDistractionDto(distraction));
            }
        }
        return list1;
    }
}
