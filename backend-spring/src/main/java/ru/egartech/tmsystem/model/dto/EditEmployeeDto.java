package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.egartech.tmsystem.model.entity.Position;

@Setter
@Getter
@NoArgsConstructor
public class EditEmployeeDto {

    private Long id;
    //Имя сотрудник
    private String name;
    //Возраст
    private int age;
    //Список привилегий в виде числа
    private Long privilegesNumber;
    //Должность
    private Position position;
    private boolean isLateIncreased;
    private boolean isEarlyLeavingIncreased;
    private boolean isAbsenceIncreased;
    private boolean isSkipIncreased;
    private boolean isRestTimeIncreased;
    private boolean isLunchTimeIncreased;
    private boolean isDistractionTimeIncreased;
}
