package ru.egartech.tmsystem.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = {"timeSheets", "rests", "distractions"})
@EqualsAndHashCode(exclude = {"timeSheets", "rests", "distractions"}, callSuper = true)
public class EmployeeDto extends EntityDto {

    //Возраст
    private int age;
    //Список привилегий в виде числа
    private Long privilegesNumber;
    //Список привилегий
    private String privileges;
    //Должность
    private PositionDto position;
    //Список табелей рабочего времен
    private List<TimeSheetDto> timeSheets = new ArrayList<>();
    private List<RestDto> rests = new ArrayList<>();
    private List<DistractionDto> distractions = new ArrayList<>();

    public EmployeeDto(String name, int age, PositionDto position) {
        super(name);
        this.age = age;
        this.position = position;
    }

    public EmployeeDto(String name, int age) {
        super(name);
        this.age = age;
    }
}
