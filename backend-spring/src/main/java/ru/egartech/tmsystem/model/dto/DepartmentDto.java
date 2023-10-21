package ru.egartech.tmsystem.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = {"positions"})
@EqualsAndHashCode(callSuper = true)
public class DepartmentDto extends EntityDto {

    @JsonIgnore
    //Должности, входящие в состав отдела
    private List<PositionDto> positions = new ArrayList<>();

    public DepartmentDto(String name) {
        super(name);
    }
}
