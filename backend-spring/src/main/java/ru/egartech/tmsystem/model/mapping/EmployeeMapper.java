package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.*;
import ru.egartech.tmsystem.model.entity.*;
import ru.egartech.tmsystem.utils.BitsConverter;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {
    public Employee toEntity(EditEmployeeDto dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setAge(dto.getAge());
        employee.setPosition(positionDtoToPosition(dto.getPosition()));
        employee.setPrivilegesNumber(BitsConverter.getEmployeePrivilegesNumber(BitsConverter.getEmployeePrivilegesList(dto)));
        return employee;
    }

    public EmployeeDto toDto(EditEmployeeDto dto) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(dto.getId());
        employeeDto.setName(dto.getName());
        employeeDto.setAge(dto.getAge());
        employeeDto.setPosition(dto.getPosition());
        employeeDto.setPrivilegesNumber(BitsConverter.getEmployeePrivilegesNumber(BitsConverter.getEmployeePrivilegesList(dto)));
        return employeeDto;
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

        return employeeDto;
    }

    protected Department departmentDtoToDepartment(DepartmentDto departmentDto) {
        if (departmentDto == null) {
            return null;
        }

        Department department = new Department();
        department.setId(departmentDto.getId());
        department.setName(departmentDto.getName());

        return department;
    }

    protected Position positionDtoToPosition(PositionDto positionDto) {
        if (positionDto == null) {
            return null;
        }

        Position position = new Position();
        position.setId(positionDto.getId());
        position.setName(positionDto.getName());
        position.setDepartment(departmentDtoToDepartment(positionDto.getDepartment()));

        return position;
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

    protected DepartmentDto departmentToDepartmentDto(Department department) {
        if (department == null) {
            return null;
        }

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(department.getId());
        departmentDto.setName(department.getName());

        return departmentDto;
    }

    protected PositionDto positionToPositionDto(Position position) {
        if (position == null) {
            return null;
        }

        PositionDto positionDto = new PositionDto();

        positionDto.setId(position.getId());
        positionDto.setName(position.getName());
        positionDto.setDepartment(departmentToDepartmentDto(position.getDepartment()));

        return positionDto;
    }

    protected TimeSheetDto timeSheetToTimeSheetDto(TimeSheet timeSheet) {
        TimeSheetDto timeSheetDto = new TimeSheetDto();

        timeSheetDto.setId(timeSheet.getId());
        timeSheetDto.setDate(timeSheet.getDate());
        timeSheetDto.setAbsenceReason(timeSheet.getAbsenceReason());
        timeSheetDto.setStartWork(timeSheet.getStartWork());
        timeSheetDto.setEndWork(timeSheet.getEndWork());
        if (timeSheet.getStartWork() == null || timeSheet.getEndWork() == null) {
            timeSheetDto.setWorkTime(0L);
        } else {
            timeSheetDto.setWorkTime(timeSheet.getWorkTime());
        }
        return timeSheetDto;
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

    protected RestDto restToRestDto(Rest rest) {
        RestDto restDto = new RestDto();

        restDto.setId(rest.getId());
        restDto.setDate(rest.getDate());
        restDto.setStartRest(rest.getStartRest());
        restDto.setEndRest(rest.getEndRest());
        if (rest.getStartRest() == null || rest.getEndRest() == null) {
            restDto.setRestTime(0L);
        } else {
            restDto.setRestTime(rest.getRestTime());
        }

        return restDto;
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

    protected DistractionDto distractionToDistractionDto(Distraction distraction) {
        DistractionDto distractionDto = new DistractionDto();

        distractionDto.setId(distraction.getId());
        distractionDto.setDate(distraction.getDate());
        distractionDto.setStartDistraction(distraction.getStartDistraction());
        distractionDto.setEndDistraction(distraction.getEndDistraction());
        if (distraction.getStartDistraction() == null || distraction.getEndDistraction() == null) {
            distractionDto.setDistractionTime(0L);
        } else {
            distractionDto.setDistractionTime(distraction.getDistractionTime());
        }

        return distractionDto;
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
