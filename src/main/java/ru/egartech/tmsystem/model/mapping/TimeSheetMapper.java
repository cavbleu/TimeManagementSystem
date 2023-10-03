package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.utils.BaseMapper;
import ru.egartech.tmsystem.model.dto.TimeSheetDto;
import ru.egartech.tmsystem.model.entity.TimeSheet;

@Mapper(componentModel = "spring")
public interface TimeSheetMapper extends BaseMapper<TimeSheetDto, TimeSheet> {
}
