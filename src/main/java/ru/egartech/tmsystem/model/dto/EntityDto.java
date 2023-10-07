package ru.egartech.tmsystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class EntityDto {

    private Long id;
    //Наименование дто
    private String name;

    public EntityDto(String name) {
        this.name = name;
    }
}
