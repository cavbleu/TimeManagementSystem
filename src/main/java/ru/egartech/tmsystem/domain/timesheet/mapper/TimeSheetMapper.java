package ru.egartech.tmsystem.domain.timesheet.mapper;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.domain.helper.BaseMapper;
import ru.egartech.tmsystem.domain.timesheet.dto.TimeSheetDto;
import ru.egartech.tmsystem.domain.timesheet.entity.TimeSheet;

@Mapper(componentModel = "spring")
public interface TimeSheetMapper extends BaseMapper<TimeSheetDto, TimeSheet> {
}
