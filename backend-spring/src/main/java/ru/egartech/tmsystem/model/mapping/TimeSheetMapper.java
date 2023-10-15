package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import ru.egartech.tmsystem.model.dto.TimeSheetDto;
import ru.egartech.tmsystem.model.entity.TimeSheet;

import java.time.Duration;

@Mapper(componentModel = "spring")
public abstract class TimeSheetMapper {

    @Autowired
    private EmployeeMapper employeeMapper;
    public TimeSheetDto toDto(TimeSheet timeSheet) {
        if ( timeSheet == null ) {
            return null;
        }

        TimeSheetDto timeSheetDto = new TimeSheetDto();
        timeSheetDto.setId( timeSheet.getId() );
        timeSheetDto.setDate( timeSheet.getDate() );
        timeSheetDto.setAbsenceReason( timeSheet.getAbsenceReason() );
        timeSheetDto.setStartWork( timeSheet.getStartWork() );
        timeSheetDto.setEndWork( timeSheet.getEndWork() );
        if ( timeSheet.getStartWork() == null || timeSheet.getEndWork() == null) {
            timeSheetDto.setWorkTime(0);
        } else {
            timeSheetDto.setWorkTime(timeSheet.getWorkTime() );
        }
        timeSheetDto.setEmployee( employeeMapper.toDto(timeSheet.getEmployee()));

        return timeSheetDto;
    }

    public TimeSheet toEntity(TimeSheetDto dto) {
        TimeSheet timeSheet = new TimeSheet();
        timeSheet.setId(dto.getId());
        timeSheet.setDate(dto.getDate());
        timeSheet.setAbsenceReason(dto.getAbsenceReason());
        timeSheet.setStartWork(dto.getStartWork());
        timeSheet.setEndWork(dto.getEndWork());
        timeSheet.setEmployee(employeeMapper.toEntity(dto.getEmployee()));
        if ( dto.getStartWork() == null || dto.getEndWork() == null) {
            timeSheet.setWorkTime(0L);
        } else {
            timeSheet.setWorkTime(Duration.between(dto.getStartWork(), dto.getEndWork()).toMinutes());
        }

        return timeSheet;
    }
}
