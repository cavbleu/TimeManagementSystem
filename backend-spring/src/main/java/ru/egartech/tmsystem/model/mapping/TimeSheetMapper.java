package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.TimeSheetDto;
import ru.egartech.tmsystem.model.entity.TimeSheet;

import java.time.Duration;

@Mapper(componentModel = "spring")
public abstract class TimeSheetMapper extends MapHelper {

    public TimeSheetDto toDto(TimeSheet timeSheet) {
        TimeSheetDto timeSheetDto = timeSheetToTimeSheetDto(timeSheet);
        timeSheetDto.setEmployee(employeeEntityToDto(timeSheet.getEmployee()));
        return timeSheetDto;
    }

    public TimeSheet toEntity(TimeSheetDto dto) {
        TimeSheet timeSheet = new TimeSheet();
        timeSheet.setId(dto.getId());
        timeSheet.setDate(dto.getDate());
        timeSheet.setAbsenceReason(dto.getAbsenceReason());
        timeSheet.setStartWork(dto.getStartWork());
        timeSheet.setEndWork(dto.getEndWork());
        timeSheet.setEmployee(employeeDtoToEntity(dto.getEmployee()));

        if (dto.getStartWork() == null || dto.getEndWork() == null) {
            timeSheet.setWorkTime(0L);
        } else {
            timeSheet.setWorkTime(Duration.between(dto.getStartWork(), dto.getEndWork()).toMinutes());
        }

        return timeSheet;
    }
}