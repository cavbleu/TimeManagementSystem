package ru.egartech.tmsystem.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EditPositionDto extends PositionDto {

    //Список всех отделов
    private List<DepartmentDto> allDepartments = new ArrayList<>();;
}
