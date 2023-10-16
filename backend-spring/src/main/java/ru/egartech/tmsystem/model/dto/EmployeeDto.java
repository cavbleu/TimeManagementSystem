package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.egartech.tmsystem.model.entity.Distraction;
import ru.egartech.tmsystem.model.entity.Position;
import ru.egartech.tmsystem.model.entity.Rest;
import ru.egartech.tmsystem.model.entity.TimeSheet;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeDto extends EntityDto {

    //Возраст
    private int age;
    //Список привилегий в виде числа
    private Long privilegesNumber;
    //Список привилегий
    private String privileges;
    //Должность
    private Position position;
    //Список табелей рабочего времен
    private List<TimeSheet> timeSheets = new ArrayList<>();
    private List<Rest> rests = new ArrayList<>();
    private List<Distraction> distractions = new ArrayList<>();

}
