package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.DistractionDto;
import ru.egartech.tmsystem.model.entity.Distraction;
import ru.egartech.tmsystem.utils.BaseMapper;
import ru.egartech.tmsystem.model.dto.TimeSheetDto;
import ru.egartech.tmsystem.model.entity.TimeSheet;

import java.time.Duration;

@Mapper(componentModel = "spring")
public abstract class TimeSheetMapper {

    public abstract TimeSheetDto toDto(TimeSheet timeSheet);

    public TimeSheet toEntity(TimeSheetDto dto) {
        TimeSheet timeSheet = new TimeSheet();
        timeSheet.setDate(dto.getDate());
        timeSheet.setAbsenceReason(dto.getAbsenceReason());
        timeSheet.setStartWork(dto.getStartWork());
        timeSheet.setEndWork(dto.getEndWork());
        timeSheet.setWorkTime(Duration.between(dto.getStartWork(), dto.getEndWork()).toMinutes());

        return timeSheet;
    }
}
