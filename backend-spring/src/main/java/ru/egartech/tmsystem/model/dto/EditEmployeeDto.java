package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
public class EditEmployeeDto {

    private Long id;
    //Имя сотрудник
    private String name;
    //Возраст
    private int age;
    //Список привилегий в виде числа
    private Long privilegesNumber;
    //Список всех должностей
//    List<PositionDto> allPositions = new ArrayList<>();
    //Должность
    private PositionDto position;
    private boolean isLateIncreased;
    private boolean isEarlyLeavingIncreased;
    private boolean isAbsenceIncreased;
    private boolean isSkipIncreased;
    private boolean isRestTimeIncreased;
    private boolean isLunchTimeIncreased;
    private boolean isDistractionTimeIncreased;
}
