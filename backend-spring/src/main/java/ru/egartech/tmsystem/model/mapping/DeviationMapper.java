package ru.egartech.tmsystem.model.mapping;

import org.mapstruct.Mapper;
import ru.egartech.tmsystem.model.dto.DeviationSummaryDto;
import ru.egartech.tmsystem.model.dto.EmployeeDto;
import ru.egartech.tmsystem.utils.DeviationFormatter;

@Mapper(componentModel = "spring")
public abstract class DeviationMapper {

    public DeviationSummaryDto toDeviationSummaryDto(EmployeeDto employeeDto,
                                                     long lateCount, double latePercent,
                                                     long earlyLeavingCount, double earlyLeavingPercent,
                                                     long absenceCount, double absencePercent,
                                                     long skipCount, double skipPercent,
                                                     long excessDistractionTimeCount, double excessDistractionTimePercent,
                                                     long excessRestTimeCount, double excessRestTimePercent) {
        DeviationSummaryDto deviationSummaryDto = new DeviationSummaryDto();
        String DEFAULT_MAX = "от максимально допустимого значения в месяц";
        long deviationCount = lateCount + earlyLeavingCount + absenceCount + skipCount + excessDistractionTimeCount + excessRestTimeCount;
        deviationSummaryDto.setEmployeeName(employeeDto.getName());
        deviationSummaryDto.setPrivileges(String.join("; ", employeeDto.getPrivileges()));
        deviationSummaryDto.setDeviationCount(DeviationFormatter.format(deviationCount));
        deviationSummaryDto.setLateCount(DeviationFormatter.format(lateCount, latePercent, DEFAULT_MAX));
        deviationSummaryDto.setEarlyLeavingCount(DeviationFormatter.format(earlyLeavingCount, earlyLeavingPercent, DEFAULT_MAX));
        deviationSummaryDto.setAbsenceCount(DeviationFormatter.format(absenceCount, absencePercent, DEFAULT_MAX));
        deviationSummaryDto.setSkipCount(DeviationFormatter.format(skipCount, skipPercent, DEFAULT_MAX));
        deviationSummaryDto.setExcessDistractionTimeCount(DeviationFormatter.format(excessDistractionTimeCount, excessDistractionTimePercent, DEFAULT_MAX));
        deviationSummaryDto.setExcessRestTimeCount(DeviationFormatter.format(excessRestTimeCount, excessRestTimePercent, DEFAULT_MAX));
        return deviationSummaryDto;
    }
}
